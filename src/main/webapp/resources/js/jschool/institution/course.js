
/*
 * registration function with jquery ajax 
 */
$(document).ready(function($) {
	
	courseDatatable();
//	loadSubjects();
        
        $("#courseName").keyup(function () {
             searchCourseName();
        });
        
	$("#addClassForm").submit(function(event) {
		
		event.preventDefault();
		var status =jbf.form.validate('#addClassForm');
		if (!status) {
			return;
		}
		// get form data
		var data = {}
		data["courseName"]      = $("#courseName").val(),
		data["totalSemester"]   = $("#semester").val(),
		url = "course/add";
		
		/*
		 * this part for csrf token now closed but dont removed from code
		 * apply future in code 
		 */ 
		
		/*var token = $('#csrfToken').val();
		var header = $('#csrfHeader').val();*/
		/*	
		 * if in spring application csrf enable
		 * send csrf parameter in header otherwise 405 error
		 */
		$.ajax({
			type 	 : "POST",
			url      : url,
			data 	 : JSON.stringify(data),
			dataType : 'json',
			contentType: "application/json; charset=utf-8",
			/*beforeSend: function(xhr) {
		        xhr.setRequestHeader("Accept", "application/json");
		        xhr.setRequestHeader("Content-Type", "application/json");
		        xhr.setRequestHeader(header, token);
		    },*/
			success  : function(resonse) {
				var message = resonse.message;
				//success notification
				success(message);
				
				courseDatatable();
				document.getElementById("addClassForm").reset()
			},
			error 	 : function(e) {
				console.log("ERROR: ",e);
				error("Add failed");
				
				
			}
		});
		
	});
	
	
    function courseDatatable(param) {
        var url = 'course/loadallcourse';
        $('#classTable').dataTable({
            destroy: true,
            data: jbf.ajax.load(url, param),
            columns: [{
                    title: 'Course Name',
                    data: 'courseName'
                }, {
                    title: 'Total Semester',
                    data: 'totalSemester'
                }
            ],
            columnDefs: [
                {"className": "dt-center", "targets": "_all"}
            ]
        });
    };
        
    function searchCourseName(){
     
        var url = 'course/searchcoursebyname';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            data: {
                'searchCourseName': $("#courseName").val()
            },
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data1=response.data;
                $("#courseHelp").html(data1).fadeIn("slow").fadeOut(90000);
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Load failed");
            }
        });
    }
    
});