$(document).ready(function ($) {
    jbf.combo.loadClass('#classCombo', 'insClass/load');
    var courseSemesterMap = new Map();
    var courseBatchFee = null;
    studentDatatable();
    getAllCourse();
    loadCourseFee();

    $("#savebtn").prop("disabled", true);

    $('#search_field').select2({
        placeholder: "Select Field For Search"
    });

    $("#search_value").on('input', function () {
        if ($("#search_field").val() !== "") {
            studentDatatable();
        }
    });

    $("#paying_fee").on('change', function () {
        if ($("#paying_fee").val() == "" || $("#paying_fee").val() == 0) {
            $("#savebtn").prop("disabled", true);
        } else {
            $("#savebtn").prop("disabled", false);
        }
    });

    $("#paying_fee").on('input', function () {
        if ($("#paying_fee").val() !== "" && $("#paying_fee").val() !== 0 && $("#paying_fee").val() > Number($("#remaining_fee").val())) {
            error("Currently Paying Fee can not be greater than Remaining Fee");
            $("#paying_fee").val("0");
            return false;
        }
    });

    $('input[type=radio][name=payment_mode]').change(function () {
        if (this.value === 'cheque') {
            $("#cheque_number").val("");
            $("#cheque_number").prop("disabled", false);
        } else if (this.value === 'cash') {
            $("#cheque_number").val("");
            $("#cheque_number").prop("disabled", true);
        }
    });

    printPage = function () {
        var studentName = $("#studentName").val();
        var batchName = $("#batchName").val();
        var courseName = $("#courseName").val();
        var enrollmentNumber = $("#enrollmentNumber").val();
        var studentDetail = "<b>" + studentName + "</b> (" + enrollmentNumber + ")<br/>" + courseName + "  " + batchName;
        var todayDate = new Date().toISOString().slice(0, 10);

//        alert(toWords(20000)); 

        $("#r1c1").html(studentDetail);
        $("#r1c2").html($("#paying_fee").val());
        $("#r1c3").html($("input[name='payment_mode']:checked").val());
        $("#r1c4").html(todayDate);

        var divToPrint = document.getElementById('printModal');
        newWin = window.open();
        newWin.document.write(divToPrint.innerHTML);
        newWin.location.reload();
        newWin.focus();
        newWin.print();
        newWin.close();
    };

    $(document).on("click", "#feebtn", function () {
        var myDataJSON = $(this).data('id');
        var batchId = myDataJSON.b;
        var studentId = myDataJSON.id;
        var courseId = myDataJSON.c;

        $("#total_fee").val("0");
        $("#remaining_fee").val("0");
        $("#discount").val("0");
        $("#total_fee_paid").val("0");
        $("#paying_fee").val("0");
        $("#cheque_number").prop("disabled", true);
        $("#studentName").val("");
        $("#batchName").val("");
        $("#courseName").val("");
        $("#enrollmentNumber").val("");

        $("#courseId").val(courseId);
        $("#batchId").val(batchId);
        $("#studentId").val(studentId);

        getRemainingFeeForStudent(studentId, courseId, batchId);

    });


    $(document).on("click", "#feehistorybtn", function () {
        var myDataJSON = $(this).data('id');
        var studentId = myDataJSON.id;
        studentFeeHistoryDatatable(studentId);

    });
    
    $(document).on("click", "#feedeletebtn", function () {
        var myDataJSON = $(this).data('id');
        var feePaymentId = myDataJSON.id;
        var studentId = myDataJSON.studentId;
        deleteStudentFeeRecord(feePaymentId,studentId);
    });

    function loadCourseFee() {
        var url = 'courseFee/loadcoursefee';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data = response.data;
                courseBatchFee = data;
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Course Fee Load failed");
            }
        });
    }
    ;

    function getAllCourse() {

        var url = 'student/loadallcourse';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data = response.data;
                for (var i = 0; i < data.length; i++) {
                    courseSemesterMap.set(Number(data[i].id), Number(data[i].totalSemester));
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Course Load failed");
            }
        });
    }

    function studentDatatable(param) {

        var tbl = $('#studentTable').dataTable({
            "processing": true,
            "destroy": true,
            "responsive": true,
//            "serverSide": true,
            "ajax": {
                "url": "student/loadallstudents",
                "type": "POST",
                "data": function (d) {
                    d.searchType = $("#search_field").val();
                    d.searchText = $("#search_value").val();
                }
            },
            "columns": [
                {
                    title: 'Enrollment No',
                    data: 'enrollmentNumber'
                }, {
                    title: 'Course',
                    data: 'courseName'
                }, {
                    title: 'Batch',
                    data: 'batchName'
                }, {
                    title: 'Student Name',
                    data: 'firstName',
                    render: function (data, type, row) {
                        return row.firstName + ' ' + row.lastName;
                    }
                }, {
                    title: 'Gender',
                    data: 'gender'
                }, {
                    title: 'Mobile',
                    data: 'mobileNumber'
                }, {
                    title: 'Email',
                    data: 'emailId'
                }, {
                    title: 'City',
                    data: 'city'
                }, {
                    title: '',
                    data: null,
                    render: function (data, type, row) {
                        var dataToSend = {};
                        dataToSend["id"] = row.id;
                        dataToSend["c"] = row.courseId;
                        dataToSend["b"] = row.batchId;
//                        console.log("JSON :" + JSON.stringify(dataToSend));
                        return "<button class='btn-primary' id='feebtn' data-id=" + JSON.stringify(dataToSend) + " data-toggle='modal' data-target='#feePayModal' data-whatever=''>Pay Fee</button>";
                    }
                }, {
                    title: '',
                    data: null,
                    render: function (data, type, row) {
                        var dataToSend = {};
                        dataToSend["id"] = row.id;
                        return "<button class='btn-primary' id='feehistorybtn' data-id=" + JSON.stringify(dataToSend) + " data-toggle='modal' data-target='#feepaymenthistory' data-whatever=''>View Fee History</button>";
                    }
                }
            ],
            "dom": 'T<"clear">lfrtip',
//            scrollCollapse: true,
            "columnDefs": [
                {
                    "targets": 4,
                    "createdCell": function (td, cellData, rowData, row, col) {
                        if (rowData.gender === 'female') {
                            $(td).css('color', 'red')
                        } else if (rowData.gender === 'male') {
                            $(td).css('color', 'green')
                        }
                    }
                },
//                {
//                    "targets": 9,
//                    "data": null,
//                    "defaultContent": "<button>Click!</button>"
//                }
            ]

        });

    }
    ;


    function getRemainingFeeForStudent(studentId, courseId, batchId) {
        var data = {};
        data["studentId"] = studentId;
        data["courseId"] = courseId;
        data["batchId"] = batchId;

        var url = "student/getremainingfeeforstudent";

        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.success === true) {
                    var data = response.data;
                    $("#total_fee").val(data.totalFee);
                    $("#discount").val(data.discount);
                    $("#remaining_fee").val(data.remainingFee);
                    $("#total_fee_paid").val(data.amountPaid);

                    $("#studentName").val(data.studentName);
                    $("#batchName").val(data.batchName);
                    $("#courseName").val(data.courseName);
                    $("#enrollmentNumber").val(data.enrollmentNumber);

                    if (data.remainingFee == 0 || data.remainingFee == "0") {
                        $("#savebtn").prop("disabled", true);
                        $("#paying_fee").prop("disabled", true);
                    } else {
                        $("#paying_fee").prop("disabled", false);
                    }

                } else {
                    error(response.message);
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Remaining fee load failed");
            }
        });
    }

    $("#savebtn").on('click', function () {
        var data = {};
        data["studentId"] = $("#studentId").val();
        data["courseId"] = $("#courseId").val();
        data["batchId"] = $("#batchId").val();
        data["amountPaid"] = $("#paying_fee").val();
        data["chequeNumber"] = $("#cheque_number").val();
        data["paymentMode"] = $("input[name='payment_mode']:checked").val();

        var isPartial = ($("#remaining_fee").val() - $("#paying_fee").val()) > 0 ? "Partial" : "Full";
        var url = "student/paystudentfee";

        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.success === true) {
                    success(response.message);
                    var feePaymentId = response.payment_id;
                    $("#print_id").html(feePaymentId);
                    $("#print_date").html(new Date().toISOString().slice(0, 10));
                    $("#print_student_name").html($("#studentName").val());
                    $("#print_paid_fee_in_words").html(toWords($("#paying_fee").val()));
                    $("#print_paying_fee").html($("#paying_fee").val());
                    $("#print_payment_mode").html($("input[name='payment_mode']:checked").val());
                    $("#print_partial_full_payment").html(isPartial);
                    $("#print_total_fee").html($("#total_fee").val());

                    $("#modalclosebtn").trigger("click");
                    $("#printbtn").trigger("click");
                    $("#savebtn").prop("disabled", true);

                    document.getElementById("feepaymentform").reset();
                } else {
                    error("Fee Payment failed");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Something went wrong");
            }
        });
    });


    function studentFeeHistoryDatatable(studentId) {

        var tbl1 = $('#studentFeeHistory').dataTable({
            "processing": true,
            "destroy": true,
            "responsive": true,
            "ajax": {
                "url": "student/getstudentfeehistory",
                "type": "POST",
                "data": function (d) {
                    d.studentId = studentId;
                }
            },
            "columns": [
                { 
                    data: null,
                    sortable: false, 
                    render: function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    }  
                },
                {
                    title: 'Mode of Payment',
                    data: 'paymentMode'
                }, {
                    title: 'Cheque Number',
                    data: 'chequeNumber'
                }, {
                    title: 'Amount',
                    data: 'amountPaid'
                },{
                    title: 'Paid On',
                    data: 'paymentDate'
                },{
                    title: '',
                    data: null,
                    render: function (data, type, row) {
                        var dataToSend = {};
                        dataToSend["id"] = row.id;
                        dataToSend["studentId"] = row.studentId;
                        return "<button class='btn-primary' id='feedeletebtn' data-id=" + JSON.stringify(dataToSend) + " data-toggle='modal' data-target='#' data-whatever=''>Undo</button>";
                    }
                }
            ],
            "dom": 'T<"clear">lfrtip'
        });
    };

    function deleteStudentFeeRecord(feePaymentId,studentId) {
        var url = "student/deletefeerecord";
        var data = {};
        data["feePaymentId"] = feePaymentId.toString();
        
        $.ajax({
            type: "GET",
            url: url,
            data: data,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.success === true) {
                    success(response.message);
                    studentFeeHistoryDatatable(studentId);
                } else {
                    error(response.message);
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Something went wrong");
            }
        });
   };


    var th = ['', 'Thousand', 'Million', 'Billion', 'Trillion'];
    var dg = ['Zero', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine'];
    var tn = ['Ten', 'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen', 'Sixteen', 'Seventeen', 'Eighteen', 'Nineteen'];
    var tw = ['Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety'];

    function toWords(s) {
        s = s.toString();
        s = s.replace(/[\, ]/g, '');
        if (s != parseFloat(s))
            return 'not a number';
        var x = s.indexOf('.');
        if (x == -1)
            x = s.length;
        if (x > 15)
            return 'too big';
        var n = s.split('');
        var str = '';
        var sk = 0;
        for (var i = 0; i < x; i++) {
            if ((x - i) % 3 == 2) {
                if (n[i] == '1') {
                    str += tn[Number(n[i + 1])] + ' ';
                    i++;
                    sk = 1;
                } else if (n[i] != 0) {
                    str += tw[n[i] - 2] + ' ';
                    sk = 1;
                }
            } else if (n[i] != 0) {
                str += dg[n[i]] + ' ';
                if ((x - i) % 3 == 0)
                    str += 'Hundred ';
                sk = 1;
            }
            if ((x - i) % 3 == 1) {
                if (sk)
                    str += th[(x - i - 1) / 3] + ' ';
                sk = 0;
            }
        }
        if (x != s.length) {
            var y = s.length;
            str += 'point ';
            for (var i = x + 1; i < y; i++)
                str += dg[n[i]] + ' ';
        }
        return str.replace(/\s+/g, ' ');
    }
});