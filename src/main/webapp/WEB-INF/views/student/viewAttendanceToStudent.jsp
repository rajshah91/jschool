<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
    <jsp:attribute name="header">
        <!-- DATA TABES SCRIPT -->
        <script src=<c:url value="/resources/js/jschool/viewAttendanceToStudent.js"/> type="text/javascript"></script>

        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js" type="text/javascript"></script>

        <link href='https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css' rel="stylesheet" type="text/css" />
        <link href='https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css' rel="stylesheet" type="text/css" />

        <style>
            .dataTables_scrollHeadInner {
                width:100% !important;
                padding: 0 !important;
            }
        </style>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-default" data-collapsed="0">
                    <div class="box-header with-border">
                        <div class="box-title">
                            <span><i class="fa fa-check-circle"></i>
                                My Attendance Detail</span>            	
                        </div>
                    </div>
                   

                    <!-- =========================== Search Datatable Start ========================  studentId=${user.username}-->
                    <input type="hidden" name="studentId" id="studentId" value="${studentId}"/>
                    <div class="box-body">
                        <div class="box-body table-responsive">
                            <table id="studentAttendanceTable" class="table table-striped table-bordered cell-border display"  style="width:100%">
                            </table>
                        </div><!-- /.box-body -->
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:dashboard>
