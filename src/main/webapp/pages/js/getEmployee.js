 var url ="http://localhost:8080/EmployeeStatsWeb/pages/Employee.html";
		  var d = new Date();
		    d.setTime(d.getTime() + (15*60*1000));
		    var expires = "expires="+ d.toUTCString();
		    document.cookie = "requested_page=" + url + ";" + expires + ";path=/";
		    
		    var testUrl = 'http://localhost:8080/EmployeeStatsWeb/webapi/login/tokenCheck';
		    var user_cookie = getCookie("logged_user");
		    var userElement = $("#logged_user");
		    $('#logged_user').text('welcome: '+ user_cookie);
		    
		    function getEmployee(){
							var $idField = document.getElementById("employeeId").value;
							var resultURL = 'http://localhost:8080/EmployeeStatsWeb/webapi/secured/employees/' + $idField
							
							$.ajax({
								type : 'GET',
								url : resultURL,
						  statusCode :{
							   200: function(result) {
									var imported = document.createElement('script');
									imported.src = 'js/employeeTemplate.js';
									document.head.appendChild(imported);
									var $employeeContainer = $('#employee');
									var employeeTemplate = $('#employee-template').html();
									var id = result.id;
									var firstName = result.employeeFirstName;
									var lastName = result.employeeLastName;
									var age = result.age;
									var lengthOfService = result.lengthOfService;
									var employee = new Employee(id, firstName, lastName, age,
											lengthOfService);		
									$employeeContainer.append(Mustache.render(employeeTemplate, employee));
									},
								401: function(response){
									window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
									}
							},	
							error : function(e) {
								console.log("error loading the particular employee: " + e);
							}
							});
				
		    };
		    



