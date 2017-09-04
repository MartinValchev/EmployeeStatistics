   

function setRequestPageCookie(location) {
			var url ="http://localhost:8080/EmployeeStatsWeb/pages/" + location;
			  var d = new Date();
			    d.setTime(d.getTime() + (15*60*1000));
			    var expires = "expires="+ d.toUTCString();
			    document.cookie = "requested_page=" + url + ";" + expires + ";path=/";
			  			   var testUrl = 'http://localhost:8080/EmployeeStatsWeb/webapi/login/tokenCheck';
			    $.ajax({
					type : 'GET',
					url : testUrl,
					statusCode: {
						200: function(result) {
							window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/" + location;
						},
						401: function(response){
							window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
						}
						
					},
					error : function() {
						alert("error loading the requested page");
					}
				});
		}
	
