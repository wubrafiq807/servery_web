
<div class="form-group">
	<label for="name" class="col-sm-2 control-label"> <strong>
			Branch Name : </strong>
	</label>
	<div class="col-sm-6">
		<input id="name" name="name" value="${branchBank.name}"
			class="form-control" />
	</div>
</div>

<div class="form-group">
	<label for="address" class="col-sm-2 control-label"> <strong>
			Address: </strong>
	</label>
	<div class="col-sm-6">
		<textarea id="address" name="address" rows="5" cols=""
			class="form-control">${branchBank.address}</textarea>
	</div>
</div>
<div class="form-group">
	<label for="status" class="col-sm-2 control-label"> <strong>
			Bank : </strong>
	</label>
	<div class="col-sm-6">

		<select id="bankId" name="bankId" class="form-control">
			<option value="">Select</option>
			<c:forEach items="${bankList}" var="bank" varStatus="tr">
			<option ${bank.id eq branchBank.bank.id?'selected':'' }  value="${bank.id}">${bank.name}</option>
			

			</c:forEach>
		</select>
	</div>
</div>
<div class="form-group">
	<label for="status" class="col-sm-2 control-label"> <strong>
			Status : </strong>
	</label>
	<div class="col-sm-6">

		<select id="status" name="status" class="form-control">
			<option value="">Select</option>
			<option value="1" ${branchBank.status eq '1'?'selected':''}>Active</option>
			<option value="0" ${branchBank.status eq '0'?'selected':''}>Inactive</option>
		</select>
	</div>
</div>
<script>
	$(function() {

		// Initialize form validation on the registration form.
		// It has the name attribute "registration"
		$("form[name='form']").validate({
			// Specify validation rules
			rules : {
				// The key name on the left side is the name attribute
				// of an input field. Validation rules are defined
				// on the right side
				name : "required",
				status : "required",
				address : "required",
				bankId : "required"

			},

			// Make sure the form is submitted to the destination defined
			// in the "action" attribute of the form when valid
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>