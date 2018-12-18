
/*
 * DataTable function add Student Page
 */

$(function () {
    $("#studentTable").dataTable();
});




/*
 * registration function with jquery ajax 
 */
$(document).ready(function ($) {

    loadCourses();

    $("#addClassForm").submit(function (event) {

        // form redirect stop
        event.preventDefault();

        //call form validation code
        var status = jbf.form.validate('#addClassForm');
        if (!status) {
            return;
        }
        // get form data
        var data = {}
        data["courseName"] = $("#courseName").val(),
                data["semester"] = $("#semester").val(),
                data["fees"] = $("#fees").val(),
                data["subjectId"] = $("#subjectCombo").val(),
                data["subjectNames"] = null,
                data["commaSeparatedSubjectNames"] = null,
                url = "course/add";

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

                classDatatable();
                document.getElementById("addClassForm").reset()
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Add falied");


            }
        });

    });


    function loadCourses() {
        var url = 'course/subjectload';
        $.ajax({
            type: "POST",
            url: url,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data = response.data;
                for (var i = 0; i < data.length; i++) {
                    $("#subjectCombo").append("<option value='" + data[i].subId + "'>" + data[i].subTitle + "</option>");
                }
//                $("#subjectCombo").trigger("change");
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Load falied");
            }
        });
    }
    ;

});