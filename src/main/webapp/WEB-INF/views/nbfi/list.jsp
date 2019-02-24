<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="content-wrapper" style="margin: 0px;">
	<section class="content-header">
		<h1>Nbfi List</h1>
		<ul class="top-links">
			<li><a class="btn btn-block btn-primary btn-xs"
				href="${pageContext.request.contextPath}/addNbfi"><i
					class="fa fa-plus"></i>Add New Nbfi</a></li>
		</ul>
	</section>

	<!-- Main content -->
	<section class="content">

		

		<div class="row">
			<div class="col-xs-12">
				<div class="box box-primary">
					<div class="box-body table-responsive no-padding">					
						<!-- ----------Start table ----------- -->
						<table class="table table-striped display" id="table_id">
							<thead>
								<tr style="background-color: #428bca;">
									<th class="text-center">Sl NO.</th>
									<th class="text-center">Nbfi Name</th>
									<th class="text-center">Created By</th>
									<th class="text-center">Created Date</th>
									<th class="text-center">Modified By</th>
									<th class="text-center">Modified Date</th>
									<th class="text-center">Status</th>
																													
									<th class="text-center">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${nbfiList}" var="nbfi" varStatus="tr">
									<tr class="row_no_${tr.count}">

										<td class="text-center" title="Show Fiscal Year">
										${tr.count}											
										</td>
										<td class="text-center">${nbfi.name}</td>
										
										<td class="text-center">${nbfi.createdBy.firstName} ${nbfi.createdBy.lastName}</td>
										<td class="text-center"><fmt:formatDate pattern="dd-MM-yyyy hh:mm:ss a"
																value="${nbfi.createdDate}" /></td>
																
										<td class="text-center">${nbfi.modifiedBy.firstName} ${nbfi.modifiedBy.lastName}</td>
										<td class="text-center"><fmt:formatDate pattern="dd-MM-yyyy hh:mm:ss a"
																value="${nbfi.modifiedDate}" /></td>						
										
										<td class="text-center">${nbfi.status=='1'?'<span style="color:green">Active</span>':'<span style="color:red">Inactive</span>'}</td>
										
										<td class="text-center"><a
											href="editNbfi/${nbfi.id}"><i
												class="fa fa-pencil-square-o" aria-hidden="true"
												title="Edit"></i></a>| 
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<script type="text/javascript">
							$(document).ready(function() {
								$('#table_id').DataTable({
									"aoColumnDefs": [
									      { "sClass": "my_class", "aTargets": [0]}
									 ]
								});
							});

							$(document).ready(function() {
								$("#collapseBtn").click(function() {
									$('#collapseIcn').toggleClass('fa-plus fa-minus');
									$(this).toggleClass('btn-info btn-warning');
								});
							});
						</script>
						<!-- ----------End Table ------------- -->
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
		</div>

	</section>
	<!-- /.content -->
</div>