
var url ="http://localhost:8080/EmployeeStatsWeb/pages/AddEmployee.html";
		  var d = new Date();
		    d.setTime(d.getTime() + (15*60*1000));
		    var expires = "expires="+ d.toUTCString();
		    document.cookie = "requested_page=" + url + ";" + expires + ";path=/";
			var user_cookie = getCookie("logged_user");
			var userElement = $("#logged_user");
			$('#logged_user').text('Welcome: '+ user_cookie);
			$('#employee-added').css('visibility','hidden');
		function resetForm(){
			var $firstname =  document.getElementById("employeeFirstName").value='';
			var $lastName = document.getElementById("employeeLastName").value='';	 
			var $age = document.getElementById("age").value='';	
			var $lengthOfService = document.getElementById("lengthOfService").value='';
		}	
		function checkInput() {
				var regex = {
					employeeFirstName : /^[A-Z]{1}[a-z]+$/,
					employeeLastName : /^[A-Z]{1}[a-z]+$/,
					age : /(^18$)|(^19$)|(^[2-9]{1,2}$)/,
					lengthOfService : /^[0-9]{1,2}\.?[0-9]{0,2}$/,

				};
				var emplFirstName = $('#employeeFirstName').val();
				var emplLastName = $('#employeeLastName').val();
				var age = $('#age').val();
				var lengthOfService = $('#lengthOfService').val();
				var errorStr ='';
					if (!regex['employeeFirstName'].test(emplFirstName)) {
						errorStr+="Error input employee first name \n";
					}
					if (!regex['employeeLastName'].test(emplLastName)) {
						errorStr+="Error  input employee last name \n";
					}
					if (!regex['age'].test(age)) {
						errorStr+="Error input employee age \n";
					}
					if (!regex['lengthOfService'].test(lengthOfService)) {
						errorStr+="Error input employee length of service \n";
					}
					
					return errorStr;
			}
		  function addEmployee (){
			  var errorString= checkInput();
			  if(errorString.length >0){
					alert(errorString);
					resetForm();
				}else{
					var $outputContainer =$('#employee-list');
					var $firstname =  document.getElementById("employeeFirstName").value;
					var $lastName = document.getElementById("employeeLastName").value;		 
					var $age = document.getElementById("age").value;		
					var $lengthOfService = document.getElementById("lengthOfService").value;
					var emplSubmit = {
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
						url:'http://localhost:8080/EmployeeStatsWeb/webapi/secured/employees/add',
						data:JSON.stringify({
							employeeFirstName:$firstname,
						 	employeeLastName: $lastName,
						 	age: $age,
						 	lengthOfService: $lengthOfService,
						}),
						statusCode: {
							200:function(result) {
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
								$('#employee-added').css('visibility','visible');
								$outputContainer.append(Mustache.render(employeeTemplate, employee));
							}
							
						},
						error:function(jqXHR, textStatus, errorThrown){
							if(jqXHR.status == 401){
								window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
							}else{
								console.log('error saving employee: ' + errorThrown);
							}
						}
					});
				}
			};
