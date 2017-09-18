var url ="http://localhost:8080/EmployeeStatsWeb/pages/Statistics.html";
		  var d = new Date();
		    d.setTime(d.getTime() + (15*60*1000));
		    var expires = "expires="+ d.toUTCString();
		    document.cookie = "requested_page=" + url + ";" + expires + ";path=/";
		    var user_cookie = getCookie("logged_user");
		    var userElement = $("#logged_user");
		    $('#logged_user').text('Welcome: '+ user_cookie);
		    	    $.ajax({
		    		type : 'GET',
		    		url : 'http://localhost:8080/EmployeeStatsWeb/webapi/secured/statistics',
		    		statusCode: {
		    			200: function(resultStatistics){
		    				var imported = document.createElement('script');
		    				imported.src = 'js/statisticsTemplate.js';
		    				document.head.appendChild(imported);
		    				var employeeAvgAge = resultStatistics.employeeAvgAge;
		    				var mostCommonChars = resultStatistics.mostCommonChars;
		    				var avgLengthOfService = resultStatistics.avgLengthOfService;
		    				var maxLengthOfService = resultStatistics.maxLengthOfService;
		    				var roundedEmployeeAge = employeeAvgAge.toFixed(2);
		    				var roundedAvgLength = avgLengthOfService.toFixed(2);
		    				var roundedMaxLength = maxLengthOfService.toFixed(2);
		    				var emplStatistics = new Statistics(roundedEmployeeAge,
		    						mostCommonChars, roundedAvgLength, roundedMaxLength);
		    				
		    				console.log(emplStatistics);
		    				
		    				var statisticsTemplate = $('#statistics-template').html();
		    		    	var $statisticsContainer = $('#statistics');
		    				$statisticsContainer.append(Mustache.render(statisticsTemplate,
		    						emplStatistics));
		    				},
		    				401:function(response){
								window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
			    			}
		    		},
		    	error : function(e) {
	    			console.log('error statistics:',e);
	    		}
		   });