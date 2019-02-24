<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="content-wrapper" style="margin: 0px;">
	<section class="content-header">
		<h1>Bank Branch List</h1>
		<ul class="top-links">
			<li><a class="btn btn-block btn-primary btn-xs"
				href="${pageContext.request.contextPath}/addBankBranch"><i
					class="fa fa-plus"></i>Add New Bank Branch</a></li>
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
									<th class="text-center">Branch Name</th>
									<th class="text-center">Branch Address</th>
									<th class="text-center">Bank Name</th>
									<th class="text-center">Created By</th>
									<th class="text-center">Created Date</th>
									<th class="text-center">Modified By</th>
									<th class="text-center">Modified Date</th>
									<th class="text-center">Status</th>
																													
									<th class="text-center">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${branchBankList}" var="branchBank" varStatus="tr">
									<tr class="row_no_${tr.count}">

										<td class="text-center" title="Show Fiscal Year">
										${tr.count}											
										</td>
										<td class="text-center">${branchBank.name}</td>
										<td class="text-center">${branchBank.address}</td>
										<td class="text-center">${branchBank.bank.name}</td>
										
										<td class="text-center">${branchBank.createdBy.firstName} ${branchBank.createdBy.lastName}</td>
										<td class="text-center"><fmt:formatDate pattern="dd-MM-yyyy hh:mm:ss a"
																value="${branchBank.createdDate}" /></td>
																
										<td class="text-center">${branchBank.modifiedBy.firstName} ${branchBank.modifiedBy.lastName}</td>
										<td class="text-center"><fmt:formatDate pattern="dd-MM-yyyy hh:mm:ss a"
																value="${branchBank.modifiedDate}" /></td>						
										
										<td class="text-center">${branchBank.status=='1'?'<span style="color:green">Active</span>':'<span style="color:red">Inactive</span>'}</td>
										
										<td class="text-center"><a
											href="editBankBranch/${branchBank.id}"><i
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