/**
 * 
 * 
 */
function logout(){
		var url = "http://localhost:8080/EmployeeStatsWeb/pages/AllEmployees.html";
		var d = new Date(0);
		var expires = "expires=" + d.toUTCString();
		document.cookie = "requested_page=deleted;" + expires + ";path=/";
		document.cookie = "logged_user=deleted;" + expires + ";path=/";


		$.ajax({
			type : 'GET',
			url : 'http://localhost:8080/EmployeeStatsWeb/webapi/login/exit',
			statusCode: {
				200:function(result) {
					window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
				},			

			},
			error : function(error) {
				console.log('Error in logout request: ' + error);
				window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
			}

		});
}