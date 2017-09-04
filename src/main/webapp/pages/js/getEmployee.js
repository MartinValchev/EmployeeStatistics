 var url ="http://localhost:8080/EmployeeStatsWeb/pages/Employee.html";
		  var d = new Date();
		    d.setTime(d.getTime() + (15*60*1000));
		    var expires = "expires="+ d.toUTCString();
		    document.cookie = "requested_page=" + url + ";" + expires + ";path=/";
		    
		    var testUrl = 'http://localhost:8080/EmployeeStatsWeb/webapi/login/tokenCheck';
		    
		    function getEmployeeRecord(){
							var $idField = document.getElementById("employeeId").value;
							var resultURL = 'http://localhost:8080/EmployeeStatsWeb/webapi/secured/employees/' + $idField
							
							$.ajax({
								type : 'GET',
								url : resultURL,
								success : function(result) {
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
								
								error : function() {
									alert("error loading the particular employee");
								}
							});
				
		    };
		    
		    $.ajax({
				type : 'GET',
				url : testUrl,
				statusCode: {
					200: function(result) {
						$(function() {
							$('#get-Employee').on('click',getEmployeeRecord());
							
							$('#resetField').on('click',function(){
								document.getElementById("employeeId").value="";
							});
						});
					},
					401: function(response){
						window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
					}
					
				},
				error : function() {
					alert("error loading the requested page");
				}
			});   
		 


