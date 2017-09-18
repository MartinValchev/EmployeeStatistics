	var homeResourceUrl = 'http://localhost:8080/EmployeeStatsWeb/webapi/login/secured/home';
	var homeUrl = "home.html";
	var loginURL ="login.html";

	$.ajax({
		type : 'GET',
		url : homeResourceUrl,
		statusCode : {
			200 : function(result) {
				var indexPageCookie = getCookie("homePage");
				var user_cookie = getCookie("logged_user");
				var userElement = $("#logged_user");
				$('#logged_user').text('Welcome: '+ user_cookie);
				
			},
			401 : function(response) {
				window.location.href = loginURL;
			}

		},
		error : function() {
			console.log("error during accessing home resource");
		}
	});	


function setRequestPageCookie(location) {
	var url = "http://localhost:8080/EmployeeStatsWeb/pages/" + location;
	var d = new Date();
	d.setTime(d.getTime() + (15 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = "requested_page=" + url + ";" + expires + ";path=/";
	
			$.ajax({
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
