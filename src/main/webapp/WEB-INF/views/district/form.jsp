
<div class="form-group">
	<label for="name" class="col-sm-2 control-label"> <strong>
			District Name : </strong>
	</label>
	<div class="col-sm-6">
		<input id="name" name="name" value="${district.name}" class="form-control" />
	</div>
</div>

<div class="form-group">
	<label for="status" class="col-sm-2 control-label"> <strong>
			Status : </strong>
	</label>
	<div class="col-sm-6">
		
		<select id="status" name="status" class="form-control">
		<option value="">Select</option>
		<option value="1" ${district.status eq '1'?'selected':''}>Active</option>
		<option value="0" ${district.status eq '0'?'selected':''}>Inactive</option>
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
			status:"required",
			
			
		},

		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form) {
			form.submit();
		}
	});
});

</script>