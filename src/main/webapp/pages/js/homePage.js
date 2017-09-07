var indexPageCookie = getCookie("homePage");
if(indexPageCookie == null){
var indexUrl =   'http://localhost:8080/EmployeeStatsWeb/webapi/login/secured/index';
$.ajax({
	type : 'GET',
	url : indexUrl,
	statusCode: {
		200: function(result) {
			var url = "pages/home.html";
			var d = new Date();
			d.setTime(d.getTime() + (15 * 60 * 1000));
			var expires = "expires=" + d.toUTCString();
			document.cookie = "homePage=" + url + ";" + expires + ";path=/";
			window.location.href = url;
		},
		401: function(response){
			window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
		}
		
	},
	error : function() {
	//
	}
});

}


function setRequestPageCookie(location) {
			var url ="http://localhost:8080/EmployeeStatsWeb/pages/" + location;
			  var d = new Date();
			    d.setTime(d.getTime() + (15*60*1000));
			    var expires = "expires="+ d.toUTCString();
			    document.cookie = "requested_page=" + url + ";" + expires + ";path=/";
			    $.ajax({
					type : 'GET',
					url : url,
					statusCode: {
						200: function(result) {
							window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/" + location;
						},
						401: function(response){
							window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
						}
						
					},
					error : function() {
					//
					}
				});
		}
	
