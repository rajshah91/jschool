/*
 * registration function with jquery ajax 
 */
$(document).ready(function ($) {

    var courseSemesterMap = new Map();
    getAllCourse();
    getAllBatch();
    
    $('#courseCombo').select2({
        placeholder: "Select Course"
    });
    
    $('#batchCombo').select2({
        placeholder: "Select Batch"
    });

//    var url = 'getfeeforcourse';
//            $.ajax({
//                url: url,
//                type: "POST",
//                dataType: 'json',
//                data: {
//                'batchId': 1,
//                'semesterId': 2,
//                'courseId': 3
//                },
//                contentType: "application/json; charset=utf-8",
//                success: function (response) {
//                    var data = response.data;
//                    alert(data);
////                for (var i = 0; i < data.length; i++) {
////                    $("#subjectCombo").append("<option value='" + data[i].subId + "'>" + data[i].subTitle + "</option>");
////                }
//                },
//                error: function (e) {
//                    console.log("ERROR: ", e);
//                    error("Fee Load falied");
//                }
//            });


    $("#courseCombo").change(function () {
        populateSemester();
    });

    $("#mobile_number").change(function () {
        $("#username").val($("#mobile_number").val());
    });

    $("#password").val(generateRandomPassword());
    $("#disability_detail").hide();

    $('input[type=radio][name=disability]').change(function () {
        if (this.value === 'yes') {
            $("#disability_detail").show();
        } else if (this.value === 'no') {
            $("#disability_detail").hide();
        }
    });


    $("#student_form").submit(function (event) {

        // form redirect stop
        event.preventDefault();
        var status = jbf.form.validate('#student_form');
        if (!status) {
            return;
        }


        // get form data
        var data = {};
        data["enrollmentNumber"] = $("#enrollmentNumber").val();
        data["courseId"] = $("#courseCombo").val();
        data["batchId"] = $("#batchCombo").val();
        data["semesterId"] = $("#semesterCombo").val();
        data["firstName"] = $("#first_name").val();
        data["middleName"] = $("#middle_name").val();
        data["lastName"] = $("#last_name").val();
        data["gender"] = $("input[name='gender']:checked").val();
        data["birthDate"] = $("#birth_date").val();
        data["enrollmentDate"] = $("#enrollment_date").val();
        data["addressLine1"] = $("#address").val();
        data["city"] = $("#city").val();
        data["state"] = $("#state").val();
        data["country"] = $("#country").val();
        data["pincode"] = $("#pincode").val();
//        data["phonecode"] = $("#").val();
        data["mobileNumber"] = $("#mobile_number").val();
        data["emailId"] = $("#email").val();
        data["guardianFullName"] = $("#guardian_full_name").val();
        data["guardianFullAddress"] = $("#guardian_full_address").val();
//        data["phonecode1"] = $("#").val();
        data["guardianMobileNumber"] = $("#guardian_mobile_number").val();
        data["username"] = $("#username").val();
        data["password"] = $("#password").val();
        data["bloodGroup"] = $("#blood_group").val();
        data["disability"] =$("input[name='disability']:checked").val();
        data["disabilityDetail"] = $("#disability_detail").val();
        
        url = "enrollstudent";

        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (resonse) {
                var message = resonse.message;
                //success notification
                success(message);
                document.getElementById("student_form").reset()
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Add falied");
            }
        });

    });

    function generateRandomPassword() {
        return Math.random().toString(36).slice(-8);
    }

    function getAllCourse() {

        var url = 'loadallcourse';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            data: {
//                'searchCourseName': $("#courseName").val()
            },
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data = response.data;
                for (var i = 0; i < data.length; i++) {
                    courseSemesterMap.set(data[i].courseName, data[i].totalSemester);
                    $("#courseCombo").append("<option value='" + data[i].id + "'>" + data[i].courseName + "</option>");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Course Load falied");
            }
        });
    }

    function getAllBatch() {

        var url = 'loadallbatch';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            data: {
//                'searchCourseName': $("#courseName").val()
            },
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data = response.data;
                for (var i = 0; i < data.length; i++) {
                    $("#batchCombo").append("<option value='" + data[i].id + "'>" + data[i].batch + "</option>");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Batch Load falied");
            }
        });
    }

    function populateSemester() {
        var selectedCourse = $("#courseCombo :selected").text();
        var totalSem = Number(courseSemesterMap.get(selectedCourse));
        $("#semesterCombo").html("");
        for (var i = 1; i <= totalSem; i++) {
            $("#semesterCombo").append("<option value='" + i + "'>" + i + "</option>");
        }
    }

});