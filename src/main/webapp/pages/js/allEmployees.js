var url = "http://localhost:8080/EmployeeStatsWeb/pages/AllEmployees.html";
var d = new Date();
d.setTime(d.getTime() + (15 * 60 * 1000));
var expires = "expires=" + d.toUTCString();
document.cookie = "requested_page=" + url + ";" + expires + ";path=/";

		$.ajax({
			type : 'GET',
			url : 'http://localhost:8080/EmployeeStatsWeb/webapi/secured/employees',
			statusCode : {
				200 : function(result) {
					$.each(result, function(i, employee) {
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
						var $employeeContainer = $('#employeesList');
						var employeeTemplate = $('#employees-template').html();
						$employeeContainer.append(Mustache.render(
								employeeTemplate, empl));
					});
				},
				401 : function(response) {
					window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
				}

			},
			error : function(error) {
				console.log('Error all employees request: ' + error);
			}

		});
