<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
    <jsp:attribute name="header">
        <!-- DATA TABES SCRIPT -->
        <script src=<c:url value="/resources/js/jschool/students.js"/> type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript"></script>
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
                            <table id="studentTable" class="table table-striped table-bordered cell-border display nowrap"  style="width:100%">
                            </table>
                        </div><!-- /.box-body -->
                    </div>

                </div>



                <!---------------------------------------------------Modal div --------------------------------------------------->            

                <!--                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#feePayModal" data-whatever="">Pay Fee</button>-->

                <div class="modal fade" id="feePayModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalLabel">Fee Payment</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form method="post" id="feepaymentform" action="" class="" style="height: auto">


                                    <div class="form-group">
                                        <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                                        <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                                        <!-- Get User information like userid or user name -->
                                        <input type="hidden" id="userId" value="${user.userId}"/>
                                    </div>

                                    <!--                                    <div class="form-group">
                                                                            <label class="col-sm-2 control-label" for="enrollmentNumber">Enrollment Number<span class="require-field">*</span></label>
                                                                            <div class="col-sm-8">
                                                                                <input id="enrollmentNumber" class="form-control validate[required]" type="text" value="" name="enrollmentNumber" placeholder="Enrollment Number">
                                                                            </div>
                                                                        </div>-->
                                    <div class="form-group">
                                        <input type="hidden" id="courseId" value=""/>
                                        <input type="hidden" id="batchId" value=""/>
                                        <input type="hidden" id="studentId" value=""/>
                                        <label for="semesterId" class="col-form-label">Semester<span class="require-field">*</span></label>
                                        <select name="semesterId" class="form-control select2 validate[required]" id="semesterCombo" style="width: 100%" placeholder="Select Semester">
                                            <option value=""></option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Total Fee</label>
                                        <input type="text" disabled="disabled" class="form-control" id="total_fee" value="">
                                    </div>


                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Remaining Fee</label>
                                        <input type="text" disabled="disabled" class="form-control" id="remaining_fee" value="">
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Discount(in Rupees)</label>
                                        <input type="number"  class="form-control" id="discount" value="0">
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Currently Paying Fee</label>
                                        <input type="number"  class="form-control" id="paying_fee" value="">
                                    </div>
                                    
                                    <div class="form-group">
                                         <label class="col-form-label" for="payment_type">Payment Mode<span class="require-field">*</span></label>
                                         <div class="form-group">
                                            <label class="radio-inline col-sm-1">
                                                <input type="radio" value="cash" class="tog validate[required]" name="payment_mode" checked="checked">Cash
                                            </label>
                                            <label class="radio-inline col-sm-1">
                                                <input type="radio" value="cheque" class="tog validate[required]" name="payment_mode">Cheque
                                                 
                                            </label>
                                            <label class="radio-inline col-sm-6">
                                                <input type="text" value="" disabled="disabled" id="cheque_number" class="form-control" name="cheque_number" placeholder="Enter Cheque Number">
                                            </label>
                                        </div>
                                    </div>

                                    <div class="clearfix"></div>

                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="savebtn">Save</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="modalclosebtn">Close</button>
                                
                                
                            </div>
                        </div>
                    </div>
                </div>



                <!---------------------------------------------------Modal div End--------------------------------------------------->            





            </div>
        </div>
    </jsp:body>
</t:dashboard>
