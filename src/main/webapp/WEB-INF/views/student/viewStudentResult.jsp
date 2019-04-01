<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
    <jsp:attribute name="header">
        <!-- DATA TABES SCRIPT -->
        <script src=<c:url value="/resources/js/jschool/viewStudentResult.js"/> type="text/javascript"></script>

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
                                Student Result Detail</span>            	
                        </div>
                    </div>
                    <div class="box-body">
                        <form name="student_result_form" action="" method="post" class="form-horizontal" id="student_result_form" enctype="multipart/form-data">
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

                            <!--                            <div class="form-group">
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
                                                        </div>-->

                            <div class="col-sm-offset-2 col-sm-8">
                                <input type="submit" value="View Result" name="view_result" class="btn btn-success">
                            </div>


                        </form>


                    </div>

                    <!-- =========================== Search Datatable Start ======================== -->
                    <div class="box-body">
                        <div class="box-body table-responsive">
                            <!--                            <table id="studentAttendanceTable" class="table table-striped table-bordered cell-border display"  style="width:100%">
                                                        </table>-->
                        </div><!-- /.box-body -->


                        <!--  START OF  DATA TABLE STATIC DATA  -->



                        <table id="example" class="display" style="width:100%" border="1">
                            <thead>
                                <tr>
                                    <th rowspan="5" style="text-align: center">No</th>
                                    <th rowspan="5" style="text-align: center">Enrollment No</th>
                                    <th colspan="5" style="text-align: center">Lodging</th>
                                    <th colspan="5" style="text-align: center">POM</th>
                                    <th colspan="5" style="text-align: center">BFA</th>
                                </tr>
                                <tr>
                                    <th colspan="5" style="text-align: center">Theory</th>
                                    <th colspan="5" style="text-align: center">Theory</th>
                                    <th colspan="5" style="text-align: center">Theory</th>
                                </tr>
                                <tr>
                                    <th colspan="5" style="text-align: center">18-12-17</th>
                                    <th colspan="5" style="text-align: center">19-12-17</th>
                                    <th colspan="5" style="text-align: center">16-12-17</th>
                                </tr>
                                <tr>
                                    <th style="text-align: center">TW</th>
                                    <th style="text-align: center">Th</th>
                                    <th style="text-align: center">Total</th>
                                    <th style="text-align: center">%</th>
                                    <th style="text-align: center">GR</th>
                                    
                                    <th style="text-align: center">TW</th>
                                    <th style="text-align: center">Th</th>
                                    <th style="text-align: center">Total</th>
                                    <th style="text-align: center">%</th>
                                    <th style="text-align: center">GR</th>
                                    
                                    <th style="text-align: center">TW</th>
                                    <th style="text-align: center">Th</th>
                                    <th style="text-align: center">Total</th>
                                    <th style="text-align: center">%</th>
                                    <th style="text-align: center">GR</th>
                                </tr>
                                <tr>
                                    <th style="text-align: center">50</th>
                                    <th style="text-align: center">50</th>
                                    <th style="text-align: center">100</th>
                                    <th style="text-align: center"></th>
                                    <th style="text-align: center"></th>
                                    
                                    <th style="text-align: center">50</th>
                                    <th style="text-align: center">50</th>
                                    <th style="text-align: center">100</th>
                                    <th style="text-align: center"></th>
                                    <th style="text-align: center"></th>
                                    
                                     <th style="text-align: center">50</th>
                                    <th style="text-align: center">50</th>
                                    <th style="text-align: center">100</th>
                                    <th style="text-align: center"></th>
                                    <th style="text-align: center"></th>
                                </tr>
                                
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>472117</td>
                                    <td>43</td>
                                    <td>34</td>
                                    <td>77</td>
                                    <td>77</td>
                                    <td>B</td>
                                    
                                    <td>43</td>
                                    <td>34</td>
                                    <td>77</td>
                                    <td>77</td>
                                    <td>B</td>
                                    
                                    <td>43</td>
                                    <td>34</td>
                                    <td>77</td>
                                    <td>77</td>
                                    <td>B</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>482117</td>
                                    <td>30</td>
                                    <td>AB</td>
                                    <td>30</td>
                                    <td>30</td>
                                    <td>AB</td>
                                    
                                    <td>30</td>
                                    <td>AB</td>
                                    <td>30</td>
                                    <td>30</td>
                                    <td>AB</td>
                                    
                                    <td>30</td>
                                    <td>AB</td>
                                    <td>30</td>
                                    <td>30</td>
                                    <td>AB</td>
                                </tr>
                                
                            </tbody>
                            <!--                            <tfoot>
                                                            <tr>
                                                                <th>Name</th>
                                                                <th>Position</th>
                                                                <th>Office</th>
                                                                <th>Age</th>
                                                                <th>Start date</th>
                                                                <th>Salary</th>
                                                            </tr>
                                                        </tfoot>-->
                        </table>

                        <!--   END OF DATA TABLE STATIC DATA  -->
                        
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:dashboard>
