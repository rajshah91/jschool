<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
    <jsp:attribute name="header">
        <!-- DATA TABES SCRIPT -->
        <script src=<c:url value="/resources/js/jschool/students.js"/> type="text/javascript"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-default" data-collapsed="0">
                    <div class="box-header with-border">
                        <div class="box-title">
                            <span><i class="fa fa-plus"></i>
                                Student Detail
                            </span>            	
                        </div>
                    </div>

                    <div class="box-body"> 
                        <form method="post">  

                            <!--<div class="input-group">-->

                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <select name="search_field" class="form-control select2" id="search_field" style="width: 100%"  >
                                            <option value="">Select Field For Search</option>
                                            <option value="enrollmentNumber">Enrollment Number</option>
                                            <option value="firstName">First Name</option>
                                            <option value="mobileNumber">Mobile Number</option>
                                            <option value="emailId">Email</option>
                                            <option value="courseId.courseName">Course</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-4">
                                        <input id="search_value" class="form-control text-input" type="text" name="search_value" value=""  placeholder="Search Here...">
                                    </div>
                                </div>
                            <!--</div>-->
                        </form>
                    </div>


                    <!-- =========================== Search Datatable Start ======================== -->
                    <div class="box-body">
                        <div class="box-body table-responsive">
                            <table id="studentTable" class="table table-bordered table-striped">
                            </table>
                        </div><!-- /.box-body -->
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:dashboard>
