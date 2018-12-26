
/*
 * registration function with jquery ajax 
 */
$(document).ready(function($) {
	
        var courseSemesterMap = new Map();
        getAllCourse();
	getAllBatch();
        courseFeeDatatable();
        
//        $("#courseCombo").change(function () {
//             populateSemester();
//        });
        
	$("#addCourseFeeForm").submit(function(event) {
		
		event.preventDefault();
		var status =jbf.form.validate('#addCourseFeeForm');
		if (!status) {
			return;
		}
		// get form data
		var data = {}
		data["courseId"]      = $("#courseCombo").val(),
		data["batchId"]   = $("#batchCombo").val(),
		data["fees"]        = $("#fees").val(),
		url = "courseFee/add";
	
		$.ajax({
			type 	 : "POST",
			url      : url,
			data 	 : JSON.stringify(data),
			dataType : 'json',
			contentType: "application/json; charset=utf-8",
			success  : function(resonse) {
				var message = resonse.message;
                                if(resonse.success == true){
                                    success(message);
                                    courseFeeDatatable();
                                    document.getElementById("addCourseFeeForm").reset();
                                    $('#courseCombo').val("").trigger('change');
                                    $('#batchCombo').val("").trigger('change');
                                }else{
                                    error(message);
                                }
				
			},
			error 	 : function(e) {
				console.log("ERROR: ",e);
				error("Add failed");
			}
		});
		
	});
	
	
    function courseFeeDatatable(param) {
        var url = 'courseFee/loadcoursefee';
        $('#courseFeeTable').dataTable({
            destroy: true,
            data: jbf.ajax.load(url, param),
            type: "POST",
            columns: [{
                    title: 'Course Name',
                    data: 'courseName'
                }, {
                    title: 'Batch',
                    data: 'batchName'
                }, {
                    title: 'Fees',
                    data: 'fees'
                }
            ],
            columnDefs: [
                {"className": "dt-center", "targets": "_all"}
            ]
        });
    };
    
    function getAllCourse(){
     
        var url = 'course/loadallcourse';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            data: {
//                'searchCourseName': $("#courseName").val()
            },
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data=response.data;
                for(var i=0;i<data.length;i++){
                    courseSemesterMap.set(data[i].courseName, data[i].totalSemester);
                    $("#courseCombo").append("<option value='"+data[i].id+"'>"+data[i].courseName+"</option>"); 
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Course Load failed");
            }
        });
    }
    
    function getAllBatch(){
     
        var url = 'batch/load';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            data: {
//                'searchCourseName': $("#courseName").val()
            },
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data=response.data;
                for(var i=0;i<data.length;i++){
                    $("#batchCombo").append("<option value='"+data[i].id+"'>"+data[i].batch+"</option>"); 
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Batch Load failed");
            }
        });
    }
    
//    function populateSemester(){
//        var selectedCourse= $("#courseCombo :selected").text();
//        var totalSem=Number(courseSemesterMap.get(selectedCourse));
//        $("#semesterCombo").html("");
//        for(var i=1;i<=totalSem;i++){
//            $("#semesterCombo").append("<option value='"+i+"'>"+i+"</option>"); 
//        }
//    }
});