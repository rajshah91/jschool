
/*
 * registration function with jquery ajax 
 */
$(document).ready(function($) {
	
	classDatatable();
	loadSubjects();
	
	
	$("#addClassForm").submit(function(event) {
		
		// form redirect stop
		event.preventDefault();
		
		//call form validation code
		var status =jbf.form.validate('#addClassForm');
		if (!status) {
			return;
		}
		// get form data
		var data = {}
		data["courseName"]      = $("#courseName").val(),
		data["semester"]        = $("#semester").val(),
		data["fees"]        = $("#fees").val(),
		data["subject"]        = $("#subjectCombo").val(),
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
				
				classDatatable();
				document.getElementById("addClassForm").reset()
			},
			error 	 : function(e) {
				console.log("ERROR: ",e);
				error("Add falied");
				
				
			}
		});
		
	});
	
	
    function classDatatable(param) {
        var url = 'course/load';
        $('#classTable').dataTable({
            destroy: true,
            data: jbf.ajax.load(url, param),
            columns: [{
                    title: 'Course Name',
                    data: 'courseName'
                }, {
                    title: 'Semester',
                    data: 'semester'
                }, {
                    title: 'Fees',
                    data: 'fees'
                }, {
                    title: 'Subject',
                    data: 'subject'
                }
            ],
            columnDefs: [
                {"className": "dt-center", "targets": "_all"}
            ]
        });
    }
    ;
        
        function loadSubjects() {
        var url = 'course/subjectload';
        $.ajax({
            type: "POST",
            url: url,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data=response.data;
                for(var i=0;i<data.length;i++){
                    $("#subjectCombo").append("<option value='"+data[i].subId+"'>"+data[i].subTitle+"</option>"); 
                }
//                $("#subjectCombo").trigger("change");
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Load falied");
            }
        });
    };
    
});