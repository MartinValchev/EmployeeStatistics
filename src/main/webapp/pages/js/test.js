function checkInput() {
	var regex = {
		employeeFirstName : /^[A-Z]{1}[a-z]+$/,
		employeeLastName : /^[A-Z]{1}[a-z]+$/,
		age : /(^18$)|(^19$)|(^[2-9]{1,2}$)/,
		lengthOfService : /[0-9]{1,2}\.?[0-9]{0,2}$/,

	};
	var emplFirstName = $('#employeeFirstName').val();
	var emplLastName = $('#employeeLastName').val();
	var age = $('#age').val();
	var lengthOfService = $('#lengthOfService').val();
	var elements =$('#add_empl_form fieldset button:not([type="submit"])');
	console.log(elements);
		if (!regex['employeeFirstName'].test(emplFirstName)) {
			alert("Error  employee first name");
		}
		if (!regex['employeeFirstName'].test(emplLastName)) {
			alert("Error  employee last name");
		}
		if (!regex['age'].test(age)) {
			alert("Error  employee age");
		}
		if (!regex['lengthOfService'].test(lengthOfService)) {
			alert("Error  employee length of service");
		}
}