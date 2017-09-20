var url = "http://localhost:8080/EmployeeStatsWeb/pages/AllEmployees.html";
var d = new Date();
d.setTime(d.getTime() + (15 * 60 * 1000));
var expires = "expires=" + d.toUTCString();
document.cookie = "requested_page=" + url + ";" + expires + ";path=/";
var pageElementsLimit = 4;
var urlParticle = 'http://localhost:8080/EmployeeStatsWeb/webapi/secured/employees';
var user_cookie = getCookie("logged_user");
var userElement = $("#logged_user");
$('#logged_user').text('Welcome: '+ user_cookie);
var employeeContainer = $('#employeesList');
		$.ajax({
			type : 'GET',
			url : urlParticle+'?start=0&size='+ pageElementsLimit,
			statusCode : {
				200 : function(result) {
					var employeeCount = getCookie("employee_count");
					var listElement = $('#empl_pages_list');
					generatePageElements(employeeCount, pageElementsLimit,listElement);
					$.each(result, function(i, employee) {
						renderEmployeeRecord(employee,employeeContainer);
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
					renderEmployeeRecord(employee,employeeContainer);
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
