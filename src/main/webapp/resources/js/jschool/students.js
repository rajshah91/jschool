$(document).ready(function ($) {
    jbf.combo.loadClass('#classCombo', 'insClass/load');
    var courseSemesterMap = new Map();
    var courseBatchFee = null;
    studentDatatable();
    getAllCourse();
    loadCourseFee();
    
     $('#search_field').select2({
        placeholder: "Select Field For Search"
    });
    
    $("#search_value").on('input', function () {
        if ($("#search_field").val() !== "") {
            studentDatatable();
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
       var studentName= $("#studentName").val();
       var batchName= $("#batchName").val();
       var courseName= $("#courseName").val();
       var enrollmentNumber= $("#enrollmentNumber").val();
       var studentDetail="<b>"+studentName+"</b> ("+enrollmentNumber+")<br/>"+courseName+"  "+batchName;
       var todayDate = new Date().toISOString().slice(0,10);
  

        
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
                    title: 'Button',
                    data: null,
                    render: function (data, type, row) {
                        var dataToSend = {};
                        dataToSend["id"] = row.id;
                        dataToSend["c"] = row.courseId;
                        dataToSend["b"] = row.batchId;
//                        console.log("JSON :" + JSON.stringify(dataToSend));
                        return "<button class='btn-primary' id='feebtn' data-id=" + JSON.stringify(dataToSend) + " data-toggle='modal' data-target='#feePayModal' data-whatever=''>Pay Fee</button>";
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

    };


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
                }else{
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
        data["studentId"] =  $("#studentId").val();
        data["courseId"] = $("#courseId").val();
        data["batchId"] = $("#batchId").val();
        data["amountPaid"] = $("#paying_fee").val();
        data["chequeNumber"] = $("#cheque_number").val();
        data["paymentMode"] = $("input[name='payment_mode']:checked").val();

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
                    document.getElementById("feepaymentform").reset();
                    $( "#modalclosebtn" ).trigger( "click" );
                }else{
                     error("Fee Payment failed");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Something went wrong");
            }
        });
    });

    
});