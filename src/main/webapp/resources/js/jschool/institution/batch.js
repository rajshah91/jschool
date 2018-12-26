
/*
 * registration function with jquery ajax 
 */
$(document).ready(function($) {
	//call class initialized
	
//	datatable load at page load
	batchDatatable();
	
	$("#addBatchForm").submit(function(event) {
		
		event.preventDefault();
		var status =jbf.form.validate('#addBatchForm');
		if (!status) {
			return;
		}
		// get form data
		var data = {}
		data["batch"]    = $("#batch").val();
		url = "batch/add";
                
		$.ajax({
			type 	 : "POST",
			url      : url,
			data 	 : JSON.stringify(data),
			dataType : 'json',
			contentType: "application/json; charset=utf-8",
			success  : function(response) {
                            var message = response.message;
                                if(response.success == true){
                                    success(message);
                                    batchDatatable();
                                    document.getElementById("addBatchForm").reset();
                                }else if(response.success == false && response.error==true){
                                    error(message);
                                }
				
			},
			error 	 : function(e) {
				console.log("ERROR: ",e);
				error("Add falied");
			}
		});
		
	});
	

	
	function batchDatatable(param) {
		var url = 'batch/load';
		$('#batchTable').dataTable({
		destroy	: true,
	        data	: jbf.ajax.load(url, param),
	        columns	: [     {
					title	: 'Batch',
					data	: 'batch'
				}
	        ],
	        columnDefs	: [
               {"Subject Title": "dt-center", "targets": "_all"}
            ]
	    });
	};
	
});