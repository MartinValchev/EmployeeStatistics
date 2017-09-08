/**

 * 
 */
var user_cookie =getCookie("logged_user");
	var username = user_cookie.logged_user;
	$("#logged_user").html(username);
function injectHtmlContent(){
	inject_to = $("#container_html"); // the div to load the html into
	load_from = "http://localhost:8080/EmployeeStatsWeb/pages/AllEmployees.html"; // the url to the page to load html from
	data = ""; // optional data to send to the other page when loading it
	
	$.ajax({
		type : 'GET',
		url : load_from,
		statusCode : {
			200 : function(result) {
				 inject_to.html(result);
				 document.activeElement = $("#allEmployee");
				},
			
			401 : function(response) {
				console.log('error executing the request')
			}

		},
		error : function(е) {
			console.log('Error all employees request: ' + e)
		}

	});
	
}
function setUserMenuBar(){
	var user_cookie =getCookie("logged_user");
	var username = user_cookie.logged_user;
	$("#logged_user").html(username);
	
}
function clearContent(){
	inject_to = $("#container_html");
	var empty ="";
	inject_to.html(empty);
}
function getEmployeeContent(){
	inject_to = $("#container_html"); // the div to load the html into
	load_from = "http://localhost:8080/EmployeeStatsWeb/pages/test/contentPage.html"; // the url to the page to load html from
	data = ""; // optional data to send to the other page when loading it
	$("#container_html").load( "login.html #welcome" ); 
	
}
	
