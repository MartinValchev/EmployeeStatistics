var url = "http://localhost:8080/EmployeeStatsWeb/pages/Employee.html";
var d = new Date();
d.setTime(d.getTime() + (15 * 60 * 1000));
var expires = "expires=" + d.toUTCString();
document.cookie = "requested_page=" + url + ";" + expires + ";path=/";

var testUrl = 'http://localhost:8080/EmployeeStatsWeb/webapi/login/tokenCheck';
var user_cookie = getCookie("logged_user");
var userElement = $("#logged_user");
$('#logged_user').text('Welcome: ' + user_cookie);
var pageElementsLimit = 3;
var elements;
var employeeList =$('#employeesList');
var imported = document.createElement('script');
imported.src = 'js/employeeTemplate.js';
document.head.appendChild(imported);

function getEmployee() {
	var criteriaField = $('#criteria').val();
	var paramField = $('#param_field').val();
	var resultURL = 'http://localhost:8080/EmployeeStatsWeb/webapi/secured/employees/'
			+ criteriaField + '=' + paramField;
	var employeeContainer = $('#employee-info');
	employeeContainer.empty();
	
			$.ajax({
				type : 'GET',
				url : resultURL,
				statusCode : {
					200 : function(result) {
						
						$('#pagination_list').empty();

						if (result.constructor != Array) {
							var temp = result;
							result = [];
							result[0] = temp;
						}
						elements = result;
						var employeeTemplate = $('#employees-template');
						console.log(employeeTemplate);
						employeeTemplate = employeeTemplate.html();
						for (item = 0; item < pageElementsLimit; item++) {
							renderEmployeeRecord(result[item],
									employeeContainer);
						}
						if (result.length > pageElementsLimit) {
							var employeeCount = result.length;
							var listElement = $('#pagination_list');
							$('#empl_pages_div').css('display','initial');
							generatePageElements(employeeCount, pageElementsLimit,
									listElement);
							$('#page_btn_1').css('background-color','#7FE0FF');
						}
					},

					401 : function(response) {
						window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
					},
					404 : function(response) {
						$('#pagination_list').empty();
						employeeContainer
								.append('No employees found matching the provided criteria');
					}
				},
				error : function(e) {
					console.log("error loading the particular employee: " + e);
				}

			});
};

function getSpecificEmployeePage(pageNum) {
	$('.btn-default').css('background-color','white');
	var pageElementsLimit = 3;
	$('#employee-info').empty();
	var offset = (pageNum - 1) * pageElementsLimit;
	var employeeContainer = $('#employee-info');

	for(index=offset; index<(offset+pageElementsLimit);index++){
		if(index<elements.length){
			renderEmployeeRecord(elements[index], employeeContainer);
		}
	}
	var btnId = '#page_btn_' + pageNum
	$(btnId).css('background-color','#7FE0FF');
		
}
function renderEmployee(employee,containerElement){
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
	var emplTemplate = $('#employees-template').html();
	containerElement.append(Mustache.render(
			emplTemplate, empl));
}
