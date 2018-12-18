<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
    <jsp:attribute name="header">
        <script type="text/javascript" src="<c:url value='/resources/js/jschool/institution/courseFee.js' />"></script> 
    </jsp:attribute>
    <jsp:attribute name="contentHeader">

    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-default" data-collapsed="0">
                    <div class="box-header with-border">
                        <div class="box-title">
                            <span><i class="fa fa-plus"></i>
                                Course Entry</span>                
                        </div>
                    </div>
                    <div class="box-body">
                        <form name="addCourseFeeForm" action="#" method="post" class="form-horizontal" id="addCourseFeeForm" enctype="multipart/form-data">
                            <div class="form-group">
                                <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                                <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                                <!-- Get User information like userid or user name -->
                                <input type="hidden" id="userId" value="${user.userId}"/>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="courseCombo">Select Course<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                    <select name="courseCombo" class="form-control select2 text-input validate[required]" id="courseCombo" style="width: 100%" >
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="batchCombo">Select Batch<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                    <select name="batchCombo" class="form-control select2 text-input validate[required]" id="batchCombo" style="width: 100%" >
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>
                            
                            
                            <div class="clearfix"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="semesterCombo">Select Semester<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                    <select name="semesterCombo" class="form-control select2 validate[required]" id="semesterCombo" style="width: 100%" >
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="fees">Fees<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                    <input id="fees" class="form-control validate[required]" type="number" value="" name="fees">
                                </div>
                            </div>

                            <!--        <div class="form-group">
                                        <label class="col-sm-2 control-label" for="subjectCombo">Select Subjects <span class="require-field">*</span></label>
                                        <div class="col-sm-8">
                                            <select name="subjectCombo" class="form-control select2 text-input validate[required]" id="subjectCombo" style="width: 100%" multiple="multiple">
                                                <option value=""></option>
                                            </select>
                                        </div>
                                    </div>-->

                            <div class="col-sm-offset-2 col-sm-8">

                                <input type="submit" value="Save" name="save" class="btn btn-success">
                            </div>
                        </form>
                    </div>

                    <!-- =========================== Search Datatable Start ======================== -->
                    <div class="box-body">
                        <div class="box-body table-responsive">
                            <table id="courseFeeTable" class="table table-bordered table-striped">
                            </table>
                        </div><!-- /.box-body -->
                    </div>
                </div>
            </div>
        </div>

    </jsp:body>
</t:dashboard>
