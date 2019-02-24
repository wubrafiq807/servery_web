<div class="content-wrapper" style="margin: 0px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Add Bank</h1>
		<ul class="top-links">
			<li><a class="btn btn-primary btn-xs"
				href="${pageContext.request.contextPath}/bankList"> <i
					class="fa fa-reorder"></i>Bank List
			</a></li>

			<c:if test="${!empty role.id}">
				<li><a class="btn btn-block btn-primary btn-xs"
					href="${pageContext.request.contextPath}/addBank"><i
						class="fa fa-plus"></i>Add new Bank</a></li>
			</c:if>

		</ul>
	</section>

	<!-- Main content -->
	<section class="content">

		<div class="row">
			<div class="col-xs-12">

				<!-- Horizontal Form -->
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title center">Add Bank Form</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->
					<form name="form" action="${pageContext.request.contextPath}/saveBank"
						method="post" class="formValMenu form-horizontal">
						<div class="box-body">
							
							<%@include file="../bank/form.jsp"%>							

						</div>
						<!-- /.box-body -->
						<div class="box-footer">
							<button type="reset" class="btn btn-default">
								<i class="fa fa-hand-paper-o"></i>Cancel
							</button>
							<button type="submit" class="btn btn-primary pull-right">
								<i class="fa fa-save"></i>Save
							</button>
						</div>
						<!-- /.box-footer -->
					</form>
				</div>
				<!-- /.box -->
			</div>
		</div>

	</section>
	<!-- /.content -->
</div>