$(document).ready(function ($) {
    jbf.combo.loadClass('#classCombo', 'insClass/load');
    //studentTable
    studentDatatable();

   $("#search_value").on('input',function() {
        if ($("#search_field").val() !== "") {
            studentDatatable();
        }
    });


    function studentDatatable(param) {

//        $(document).ready(function() {
//    var table = $('#example').DataTable( {
//        "ajax": "data/arrays.txt",
//        "columnDefs": [ {
//            "targets": -1,
//            "data": null,
//            "defaultContent": "<button>Click!</button>"
//        } ]
//    } );
// 
//    $('#example tbody').on( 'click', 'button', function () {
//        var data = table.row( $(this).parents('tr') ).data();
//        alert( data[0] +"'s salary is: "+ data[ 5 ] );
//    } );
//} );

        $('#studentTable').dataTable({
            "processing": true,
            "destroy": true,
            responsive: true,
//            "serverSide": true,
//            data: jbf.ajax.load("student/loadallstudents", "{search_field:'first_name',search_value:'Raj'}"),
//            "data": {
//                "search_field": $("#search_field").val(),
//                "search_value": $("#search_value").val()
//            },
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
                }
            ],
//            columnDefs: [
//                {"Subject Title": "dt-center", "targets": "_all"}
////                {"targets": 1, "defaultContent": "<button>Click!</button>", "data": null}
//            ],


        });
    }
    ;
});