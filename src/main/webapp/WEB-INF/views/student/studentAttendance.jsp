<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
    <jsp:attribute name="header">
        <!-- DATA TABES SCRIPT -->
        <script src=<c:url value="/resources/js/jschool/studentAttendance.js"/> type="text/javascript"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-default" data-collapsed="0">
                    <div class="box-header with-border">
                        <div class="box-title">
                            <span><i class="fa fa-check-circle"></i>
                                Student Attendance</span>            	
                        </div>
                    </div>
                    <div class="box-body">
                        <form name="student_attendance_form" action="uploadStudentAttendance" method="post" class="form-horizontal" id="student_attendance_form" enctype="multipart/form-data">
                            <div class="form-group">
                                <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                                <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                                <!-- Get User information like userid or user name -->
                                <input type="hidden" id="userId" value="${user.userId}"/>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="course">Course<span class="require-field">*</span></label>
                                <div class="col-sm-3">
                                    <select name="courseId" class="form-control select2 text-input validate[required]" id="courseCombo" style="width: 100%" placeholder="Select Course">
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="batch">Batch<span class="require-field">*</span></label>
                                <div class="col-sm-3">
                                    <select name="batchId" class="form-control select2 text-input validate[required]" id="batchCombo" style="width: 100%" placeholder="Select Batch">
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="semester">Semester<span class="require-field">*</span></label>
                                <div class="col-sm-3">
                                    <select name="semesterId" class="form-control select2 validate[required]" id="semesterCombo" style="width: 100%" placeholder="Select Semester">
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="month">Month<span class="require-field">*</span></label>
                                <div class="col-sm-3">
                                    <select name="month" class="form-control select2 validate[required]" id="month" style="width: 100%" placeholder="Select Month">
                                        <option value=""></option>
                                        <option value="January">January</option>
                                        <option value="February">February</option>
                                        <option value="March">March</option>
                                        <option value="April">April</option>
                                        <option value="May">May</option>
                                        <option value="June">June</option>
                                        <option value="July">July</option>
                                        <option value="August">August</option>
                                        <option value="September">September</option>
                                        <option value="October">October</option>
                                        <option value="November">November</option>
                                        <option value="December">December</option>
                                    </select>

                                </div>
                                <input type="button" value="Download Sample File" name="sample_file_download" id="sample_file_download" class="btn btn-warning">
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="semester">Select File To Upload<span class="require-field">*</span></label>
                                <div class="col-sm-3 ">
                                    <input type="file" name="file"  class="form-control z-depth-1 " id="file" >
                                </div>
                            </div>

                            <div class="col-sm-offset-2 col-sm-8">
                                <input type="submit" value="Upload Attendance" name="save_student" class="btn btn-success">
                            </div>


                        </form>
                            <br><br/>
                            <div>
                                <h3><b>Note for Uploading Sheet :</b></h3>
                                <ul>
                                    <li>Do not change the sheet format</li>
                                    <li>Fill your details in the exported sheet for specified month</li>
                                    <li>Do not add any extra column into sheet</li>
                                    <li>Remove extra date column from Sheet(Ex. 29 from February month Sheet if it is non-leap year.</li>
                                    <li>Do not fill empty values in sheet.Use below values for filling Attendance Sheet.
                                        <ul><b>
                                            <li style="color: red">Absent  : A</li>
                                            <li style="color: green">Present : P</li>
                                            <li style="color: greenyellow">Holiday : H</li>
                                            <li style="color: blue">Leave   : L</li></b>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:dashboard>
