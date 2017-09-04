 var url ="http://localhost:8080/EmployeeStatsWeb/pages/AddEmployee.html";
		  var d = new Date();
		    d.setTime(d.getTime() + (15*60*1000));
		    var expires = "expires="+ d.toUTCString();
		    document.cookie = "requested_page=" + url + ";" + expires + ";path=/";
		    
		    var testUrl = 'http://localhost:8080/EmployeeStatsWeb/webapi/login/tokenCheck';
		    
		    function addEmployee(){
				var $outputContainer =$('#employee-list');
				var emplSubmit;
				var $firstname =  document.getElementById("employeeFirstName").value;
				var $lastName = document.getElementById("employeeLastName").value;		 
				var $age = document.getElementById("age").value;		
				var $lengthOfService = document.getElementById("lengthOfService").value;
				 emplSubmit = {
					employeeFirstName:$firstname,
				 	employeeLastName: $lastName,
				 	age: $age,
				 	lengthOfService: $lengthOfService,
				 };
				 
				 var emplSubmitString = JSON.stringify(emplSubmit);
				 
					$.ajax({
						type:'POST',
						contentType: 'application/json; charset=utf-8',
					    dataType: 'json',
						url:'http://localhost:8080/EmployeeStatsWeb/webapi/employees/secured/add',
						data:JSON.stringify({
							employeeFirstName:$firstname,
						 	employeeLastName: $lastName,
						 	age: $age,
						 	lengthOfService: $lengthOfService,
						}),
						success: {
							 function(result) {
								var imported = document.createElement('script');
								imported.src = 'js/employeeTemplate.js';
								document.head.appendChild(imported);
								var id = result.id;
								var firstName = result.employeeFirstName;
								var lastName = result.employeeLastName;
								var age = result.age;
								var lengthOfService = result.lengthOfService;
								var employeeTemplate = $('#employee-template').html();
								var employee = new Employee(id, firstName, lastName, age,
										lengthOfService);		
								
								$outputContainer.append(Mustache.render(employeeTemplate, employee));
							}
							
						},
						error:function(e){
							alert('error saving employee',$result);
						}
					});
		    };
		    
		    $.ajax({
				type : 'GET',
				url : testUrl,
				statusCode: {
					200: function(result) {
						$(function(){
							
							$('#add-Employee').on('click',addEmployee());
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
		 
