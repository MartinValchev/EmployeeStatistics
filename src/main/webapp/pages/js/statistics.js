var url ="http://localhost:8080/EmployeeStatsWeb/pages/Statistics.html";
		  var d = new Date();
		    d.setTime(d.getTime() + (15*60*1000));
		    var expires = "expires="+ d.toUTCString();
		    document.cookie = "requested_page=" + url + ";" + expires + ";path=/";
		    
		    var testUrl = 'http://localhost:8080/EmployeeStatsWeb/webapi/login/tokenCheck';

		    function getStatistics(){
		    	var statisticsTemplate = $('#statistics-template').html();
		    	var $statisticsContainer = $('#statistics');

		    	    $.ajax({
		    		type : 'GET',
		    		url : 'http://localhost:8080/EmployeeStatsWeb/webapi/secured/statistics',
		    		success: {
		    			function(resultStatistics) {
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
		    				$statisticsContainer.append(Mustache.render(statisticsTemplate,
		    						emplStatistics));
		    			}
		    			
		    		},
		    		error : function() {
		    			alert('Statistics not loaded correctly');
		    		}
		    	});

		    }
		    
		    $.ajax({
				type : 'GET',
				url : testUrl,
				statusCode: {
					200: function(result) {
						$(function(){			
							getStatistics();
						});
					},
					401: function(response){
						window.location.href = "http://localhost:8080/EmployeeStatsWeb/pages/login.html";
					}
					
				},
				error : function() {
					alert("error loading the requested page");
				}
			});  

