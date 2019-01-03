/*
 * registration function with jquery ajax 
 */
$(document).ready(function ($) {
    
    var query = window.location.search.substring(1);
    var myParam = location.search.split('success=')[1] ? location.search.split('success=')[1] : "" ;
    if(myParam != ""){
        if(myParam == "true"){
            success("Data Uploaded Successfully");
        }else if(myParam == "false"){
            error("Data Upload failed.Please try again.");
        }
    }

    var courseSemesterMap = new Map();
    getAllCourse();
    getAllBatch();

    $('#courseCombo').select2({
        placeholder: "Select Course"
    });

    $('#batchCombo').select2({
        placeholder: "Select Batch"
    });

    $('#semesterCombo').select2({
        placeholder: "Select Semester"
    });

    $('#month').select2({
        placeholder: "Select Month"
    });

    $("#courseCombo").change(function () {
        populateSemester();
    });

    $("#sample_file_download").click(function () {
        var month = $("#month").val();
        if (month !== null && month !== "") {
            var url = "downloadsamplefile?month=" + month;
            $.ajax({
                type: 'GET',
                url: url,
                processData: false,
                success: function (data) {
                    window.location = url;
                },
                error: function (xhr) {
                    console.log(' Error:  >>>> ' + JSON.stringify(xhr));
                }
            });
        }

    });


    $("form#student_attendance_form").submit(function (event) {

        
        var status = jbf.form.validate('#student_attendance_form');
        if (!status) {
            return;
            event.preventDefault();
        }else{
            $(this).unbind(event);
        }
         
/*
        // Get form
        var form = $('#student_attendance_form')[0];

        // Create an FormData object 
        var data1 = new FormData(form);

        var fileInput = document.getElementById('file');
        var file = fileInput.files[0];
        var formData = new FormData();
        formData.append('file', file);
        formData.append('courseId', $("#courseCombo").val());
        formData.append('batchId', $("#batchCombo").val());
        formData.append('semesterId', $("#semesterCombo").val());
        formData.append('month', $("#month").val());

        var data1 = {};
        data1["courseId"] = $("#courseCombo").val();
        data1["batchId"] = $("#batchCombo").val();
        data1["semesterId"] = $("#semesterCombo").val();
        data1["month"] = $("#month").val();
        //data["file"] = file;
*/
        /*    var url = "uploadStudentAttendance";
         $.ajax({
         type: "POST",
         url: url,
         data: JSON.stringify(formData),
         //            data: JSON.stringify(data1),
         enctype: 'multipart/form-data',
         processData: false, // Important!
         contentType: false,
         cache: false,
         async: false,
         scriptCharset: "utf-8",
         //            headers: {
         //                'Accept': 'application/json',
         //                'Content-Type': 'application/json'
         //            },
         dataType: 'json', //do not disable this
         //            contentType: "application/json; charset=utf-8", //do not disable this
         //            contentType: "multipart/form-data", //do not disable this
         success: function (response) {
         var message = response.message;
         //success notification
         if (response.success === true) {
         success(message);
         document.getElementById("student_attendance_form").reset();
         $('#courseCombo').val("").trigger('change');
         $('#batchCombo').val("").trigger('change');
         $('#semesterCombo').val("").trigger('change');
         $('#month').val("").trigger('change');
         } else {
         error(message);
         }
         
         },
         error: function (e) {
         console.log("ERROR: ", e);
         error("Something went wrong");
         }
         });  */

/*
       var formData = new FormData(this);
        $.ajax({
            url: "uploadStudentAttendance",
            type: 'POST',
//            scriptCharset: "utf-8",
            enctype: 'multipart/form-data',
            data: formData,
            async: false,
            cache: false,
//            contentType: false,
            processData: false,
            timeout: 600000,
            contentType: "multipart/form-data",
//            contentType: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            success: function (response) {
                if (response.status == "SUCCESS") {
                    console.log("SUCCESS...");
                    $(document).trigger("SUCCESS", [response]);
                } else if (response.status == "FAIL") {
                    console.log("FAIL...");
                    clearErrors(form);


                }
            }
        });
*/
    });

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
                error("Course Load failed");
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
                error("Batch Load failed");
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