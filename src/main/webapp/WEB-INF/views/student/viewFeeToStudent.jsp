<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
    <jsp:attribute name="header">
        <!-- DATA TABES SCRIPT -->
        <script src=<c:url value="/resources/js/jschool/viewFeeToStudent.js"/> type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">



            </div>
           <div class="box-body">
                <form name="student_result_form" action="" method="post" class="form-horizontal" id="student_result_form" enctype="multipart/form-data">
                            <div class="form-group">
                                 <input type="hidden" name="studentId" id="studentId" value="${studentId}"/>
                                <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                                <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                                <!-- Get User information like userid or user name -->
                                <input type="hidden" id="userId" value="${user.userId}"/>
                            </div>
                </form>
                <div class="box-body table-responsive">
                    <table id="studentFeeHistory" class="table table-striped table-bordered cell-border display"  style="width:100%">
                    </table>
                </div><!-- /.box-body -->
            </div>



        </div>
    </div>
</jsp:body>
</t:dashboard>
