var indexPageCookie = getCookie("homePage");
var user_cookie = getCookie("logged_user");
var userElement = $("#logged_user");
$('#container_html').html(user_cookie);

	var indexUrl = 'http://localhost:8080/EmployeeStatsWeb/webapi/login/secured/index';


function setRequestPageCookie(location) {
	var url = "http://localhost:8080/EmployeeStatsWeb/pages/" + location;
	var d = new Date();
	d.setTime(d.getTime() + (15 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = "requested_page=" + url + ";" + expires + ";path=/";
	$
			.ajax({
				type : 'GET',
				url : url,
				statusCode : {
					200 : function(result) {
						window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/"
								+ location;
					},
					401 : function(response) {
						window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
					}

				},
				error : function() {
					//
				}
			});
}
