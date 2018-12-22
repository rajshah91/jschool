$(document).ready(function ($) {
    jbf.combo.loadClass('#classCombo', 'insClass/load');
    var courseSemesterMap = new Map();
    var courseBatchSemFee = null;
    studentDatatable();
    getAllCourse();
    loadCourseFee();

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

    $("#semesterCombo").on('change', function () {
        var semId = $("#semesterCombo").val();
        var courseId = $("#courseId").val();
        var batchId = $("#batchId").val();
        var studentId = $("#studentId").val();
        $("#total_fee").val("0");
        $("#remaining_fee").val("0");
        $("#discount").val("0");
        var valuefound = false;
        for (var i = 0; i < courseBatchSemFee.length; i++) {
            var b = courseBatchSemFee[i].batchId;
            var c = courseBatchSemFee[i].courseId;
            var s = courseBatchSemFee[i].semesterId;
            if (b === batchId && c === courseId && s === semId) {
                $("#total_fee").val(courseBatchSemFee[i].fees);
                valuefound = true;
                break;
            }
        }
        if (!valuefound) {
            error("Fee is not available for given selection");
        } else {
            var totalFee = $("#total_fee").val();
            getRemainingFeeForStudent(studentId, courseId, batchId, semId, totalFee);
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

//    $("input[name='gender']:checked").val();

    $(document).on("click", "#feebtn", function () {
        var myDataJSON = $(this).data('id');
        var batchId = myDataJSON.b;
        var studentId = myDataJSON.id;
        var courseId = myDataJSON.c;

        $("#courseId").val(courseId);
        $("#batchId").val(batchId);
        $("#studentId").val(studentId);

        populateSemester(courseId);

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
//                alert(data);
                courseBatchSemFee = data;
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Course Fee Load falied");
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
                error("Course Load falied");
            }
        });
    }

    function populateSemester(courseId) {
        var totalSem = courseSemesterMap.get(parseInt(courseId));
        $("#semesterCombo").html("");
        $("#semesterCombo").append("<option value=''>Select Semester<option>");
        for (var i = 1; i <= totalSem; i++) {
            $("#semesterCombo").append("<option value='" + i + "'>" + i + "</option>");
        }
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

    }
    ;


    function getRemainingFeeForStudent(studentId, courseId, batchId, semId, totalFee) {
        var data = {};
        data["studentId"] = studentId;
        data["courseId"] = courseId;
        data["batchId"] = batchId;
        data["semesterId"] = semId;
        data["totalFee"] = totalFee;

        var url = "student/getremainingfeeforstudent";

        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (resonse) {
                if (resonse.success === true) {
                    var data = resonse.data;
                    $("#remaining_fee").val(data.remainingFee);
                }

            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Remaining fee load falied");
            }
        });
    }

    $("#savebtn").on('click', function () {
        var data = {};
        data["studentId"] =  $("#studentId").val();
        data["courseId"] = $("#courseId").val();
        data["batchId"] = $("#batchId").val();
        data["semesterId"] = $("#semesterCombo").val();
        data["amountPaid"] = $("#paying_fee").val();
        data["discount"] = $("#discount").val();
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
                     error("Fee Payment falied");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Something went wrong");
            }
        });
    });


});