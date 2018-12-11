<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
	<jsp:attribute name="header">
	
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
					Admission Form</span>            	
				</div>
            </div>
            <div class="box-body">
        <form name="student_form" action="" method="post" class="form-horizontal" id="student_form" enctype="multipart/form-data">
         		<input type="hidden" name="action" value="insert">
		<input type="hidden" name="role" value="student">
		
		<div class="form-group">
			<label class="col-sm-2 control-label" for="class_name">Select Course</label>
			<div class="col-sm-8">
        		<select name="class_section" class="form-control" id="class_section">
                 	<option value="">Select Course </option>
                 	<option value="sectionA">Course A</option>
                	<option value="sectionB">Course B</option>
                </select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="roll_id">Roll Number<span class="require-field">*</span></label>
			<div class="col-sm-8">
				<input id="roll_id" class="form-control validate[required]" type="text" value="" name="roll_id">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="first_name">Student Name<span class="require-field">*</span></label>
			<div class="col-sm-8">
				<input id="name" class="form-control validate[required,custom[onlyLetterSp]] text-input" type="text" value="" name="first_name">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label" for="gender">Gender<span class="require-field">*</span></label>
			<div class="col-sm-8">
							<label class="radio-inline">
			     <input type="radio" value="male" class="tog validate[required]" name="gender" checked="checked">Male			    </label>
			    <label class="radio-inline">
			      <input type="radio" value="female" class="tog validate[required]" name="gender">Female 
			    </label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="birth_date">Date of birth<span class="require-field">*</span></label>
			<div class="col-sm-8">
				<input id="birth_date" class="form-control validate[required] hasDatepicker" type="text" name="birth_date" value="">
			</div>
		</div>		
		<div class="form-group">
			<label class="col-sm-2 control-label" for="address">Address<span class="require-field">*</span></label>
			<div class="col-sm-8">
				<input id="address" class="form-control validate[required]" type="text" name="address" value="">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label" for="mobile_number">Mobile Number</label>
			<div class="col-sm-2">
			
			<input type="text" readonly value="+91" class="form-control text-input" name="phonecode">
			</div>
			<div class="col-sm-6">
				<input id="mobile_number" class="form-control text-input" type="text" name="mobile_number" value="">
			</div>
		</div>
		
		
		<div class="form-group">
			<label class="col-sm-2 control-label" for="username">User Name<span class="require-field">*</span></label>
			<div class="col-sm-8">
				<input id="username" class="form-control validate[required]" type="text" name="username" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="password">Password<span class="require-field">*</span></label>
			<div class="col-sm-8">
				<input id="password" class="form-control validate[required]" type="password" name="password" value="">
			</div>
		</div>

	
		<div class="col-sm-offset-2 col-sm-8">
        	
        	<input type="submit" value="Add Student" name="save_student" class="btn btn-success">
        </div>
          	
        
        </form>
        </div>
        </div>
    </div>
    </div>
	</jsp:body>
</t:dashboard>
