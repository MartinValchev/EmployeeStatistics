$('form.loginForm').on('submit', function() {
	
	var that =$(this),
	url = that.attr('action'),
	type=  that.attr('method'),
	data ={};
	var imported = document.createElement('script');
	imported.src = 'js/userTemplate.js';
	document.head.appendChild(imported);
	that.find('[name]').each(function(index,value){
		var that =$(this);
		name = that.attr('name');
		value = that.val();
		data[name] = value;
		
	});
	var username = data.username;
	var password = data.password;
	var hashPassword = CryptoJS.MD5(password).toString();
	var user = new User(username,password);
	var requestedPage = "http://localhost:8080/EmployeeStatsWeb/pages/index.html";
	setCookie('requested-page', requestedPage, 15);
	
	function setCookie(cookieName, cookieValue, exminutes) {
		var d = new Date();
		d.setTime(d.getTime() + (exminutes * 60 * 1000));
		var expires = "expires=" + d.toUTCString();
		document.cookie = cookieName + "=" + cookieValue + ";" + expires + ";path=/";

	};
	$.ajax({	
		type:'POST',
		contentType: 'application/json; charset=utf-8',
		dataType: 'text',
		url:url,
		data:JSON.stringify({
			username:username,
			password:hashPassword,
		}),
		success: function(response){
			var requestUrl = this.url;
			alert(requestUrl);
			var isLoginValid = response;
			//console.log(response); 
			if(isLoginValid){
				console.log(response);
				console.log('Login Successfult');
				
				document.location.href=requestUrl;
			}
		},
		error: function(){
			console.log('Login request not processed correctly');
		}
	});
	
	return false;
});
