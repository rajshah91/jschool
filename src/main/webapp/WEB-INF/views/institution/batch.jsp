<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
    <jsp:attribute name="header">
            <script type="text/javascript" src="<c:url value='/resources/js/jschool/institution/batch.js' />"></script> 
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
                    Batch Entry</span>                
                </div>
            </div>
            <div class="box-body">
        <form name="addBatchForm" action="#" method="post" class="form-horizontal" id="addBatchForm" enctype="multipart/form-data">
                <div class="form-group">
                <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                <!-- Get User information like userid or user name -->
                <input type="hidden" id="userId" value="${user.userId}"/>
                 </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="batch">Batch Name<span class="require-field">*</span></label>
            <div class="col-sm-8">
                <input id="batch" class="form-control validate[required]" type="text" value="" name="batch" aria-describedby="batchHelp" >
                <small id="batchHelp" class="form-text text-muted bg-info">
                    
                </small>
            </div>
        </div>
        <div class="clearfix"></div>
        
        <div class="col-sm-offset-2 col-sm-8">
            
            <input type="submit" value="Save" name="save_batch" class="btn btn-success">
        </div>
        </form>
        </div>
        
         <!-- =========================== Search Datatable Start ======================== -->
            <div class="box-body">
                <div class="box-body table-responsive">
                    <table id="batchTable" class="table table-bordered table-striped">
                    </table>
                </div><!-- /.box-body -->
            </div>
        </div>
    </div>
    </div>
   
    </jsp:body>
</t:dashboard>
