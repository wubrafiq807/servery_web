<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="content-wrapper" style="margin: 0px;">
	<section class="content-header">
		<h1>Machinary List</h1>
		<ul class="top-links">
			<%-- <li><a class="btn btn-block btn-primary btn-xs"
				href="${pageContext.request.contextPath}/addNbfi"><i
					class="fa fa-plus"></i>Add New Nbfi</a></li> --%>
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
									<th class="text-center">Machinary Name</th>
									<th class="text-center">Machinary Model</th>
									<th class="text-center">Machinary Specification</th>
									
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${machinaryList}" var="machinary" varStatus="tr">
									<tr class="row_no_${tr.count}">

										<td class="text-center" title="Show Fiscal Year">
										${tr.count}											
										</td>
										 <td class="text-center">${machinary.name}</td>
										
										<td class="text-center">${machinary.model}</td>
										<td class="text-center">${machinary.specification}</td>
										
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