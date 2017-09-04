 var url ="http://localhost:8080/EmployeeStatsWeb/pages/AllEmployees.html";
		  var d = new Date();
		    d.setTime(d.getTime() + (15*60*1000));
		    var expires = "expires="+ d.toUTCString();
		    document.cookie = "requested_page=" + url + ";" + expires + ";path=/";

		    var testUrl = 'http://localhost:8080/EmployeeStatsWeb/webapi/login/tokenCheck';
		    
			    function getAllEmployees(){

				var $employeeContainer=$('#employeesList');
				var employeeTemplate = $('#employees-template').html(); 
				$.ajax({
					type : 'GET',
					url : 'http://localhost:8080/EmployeeStatsWeb/webapi/secured/employees',
					success: {
						function(result) {
							$.each(result,function(i,employee){
								var imported = document.createElement('script');
								imported.src = 'js/employeeTemplate.js';
								document.head.appendChild(imported);
								var id = employee.id;
								var firstName = employee.employeeFirstName;
								var lastName = employee.employeeLastName;
								var age = employee.age;
								var lengthOfService = employee.lengthOfService;
								var empl = new Employee(id, firstName, lastName, age,
										lengthOfService);
								$employeeContainer.append(Mustache.render(employeeTemplate, empl));
							});				
						}
						
					},
					error : function() {
						alert("error loading the particular employee");
					}
				
		    });
		};
		    
		    $.ajax({
				type : 'GET',
				url : testUrl,
				statusCode: {
					200: $(document).ready(function(){ getAllEmployees(); }),
					401: function(response){
						window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
					}
					
				},
				error : function() {
					alert("error loading the requested page");
				}
			});      
		    
