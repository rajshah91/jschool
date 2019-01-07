$(document).ready(function ($) {
   
   studentFeeHistoryDatatable($("#studentId").val());
   
  
    function studentFeeHistoryDatatable(studentId) {

        var tbl1 = $('#studentFeeHistory').dataTable({
            "processing": true,
            "destroy": true,
            "responsive": true,
            "ajax": {
                "url": "getstudentfeehistory",
                "type": "POST",
                "data": function (d) {
                    d.studentId = studentId;
                }
            },
            "columns": [
                { 
                    data: null,
                    sortable: false, 
                    render: function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    }  
                },
                {
                    title: 'Mode of Payment',
                    data: 'paymentMode'
                }, {
                    title: 'Cheque Number',
                    data: 'chequeNumber'
                }, {
                    title: 'Amount',
                    data: 'amountPaid'
                },{
                    title: 'Paid On',
                    data: 'paymentDate'
                }
            ],
            "dom": 'T<"clear">lfrtip'
        });
    };

});