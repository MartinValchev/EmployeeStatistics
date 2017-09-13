var url = "http://localhost:8080/EmployeeStatsWeb/pages/AllEmployees.html";
var d = new Date();
d.setTime(d.getTime() + (15 * 60 * 1000));
var expires = "expires=" + d.toUTCString();
document.cookie = "requested_page=" + url + ";" + expires + ";path=/";
var pageElementsLimit = 4;
var urlParticle = 'http://localhost:8080/EmployeeStatsWeb/webapi/secured/employees';
var user_cookie = getCookie("logged_user");
var userElement = $("#logged_user");
$('#logged_user').text('welcome: '+ user_cookie);

		$.ajax({
			type : 'GET',
			url : urlParticle+'?start=0&size='+ pageElementsLimit,
			statusCode : {
				200 : function(result) {
					var employeeCount = getCookie("employee_count");
					generatePageElements(employeeCount, pageElementsLimit);
					$.each(result, function(i, employee) {
						renderEmployeeRecord(employee);
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
function getSpecificEmployeePage(pageNum) {
	var pageElementsLimit = 4;
	var offset = (pageNum-1)*pageElementsLimit;

	var requestURL =urlParticle+ '?start='
	+ offset + '&size=' + pageElementsLimit;
	$.ajax({
		type : 'GET',
		url : requestURL,
		statusCode : {
			200 : function(result) {
				$('#employeesList').empty();
				$.each(result, function(i, employee) {
					renderEmployeeRecord(employee);
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
};
function generatePageElements(employeeCount, pageLimit) {
	var employeeList = $('#empl_pages_list');
	var pagesCount;
	var offsetIncrement =4;
	var offsetCurrent=0;
	var additionalPageCount;
		pagesCount = employeeCount / pageLimit;
	var onClickFunction;	

	for(i=1;i<=pagesCount;i++){
		
		onClickFunction = 'onclick="getSpecificEmployeePage('+i+')"';
		employeeList.append('<li><button type="button" class="btn btn-default" ' + onClickFunction+ '>'+i +'</button></li>');
		offsetCurrent +=offsetIncrement;
		
	};
	if (employeeCount % pageLimit > 0) {
		additionalPageCount = employeeCount % pageLimit;
		onClickFunction = 'onclick="getSpecificEmployeePage('+i+')"';
		listElement ='<li><button class="btn btn-default" type="button" ' + onClickFunction+ '>'+i +'</button></li>';
		
	employeeList.append(listElement);
	}
};
function renderEmployeeRecord(employee){
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
}
