function setCookie(cookieName, cookieValue, exminutes) {
	var d = new Date();
	d.setTime(d.getTime() + (exminutes * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cookieName + "=" + cookieValue + ";" + expires + ";path=/";

};
function getCookie(cName) {
	var name = cName + "=";
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}
function checkCookie(cookieName,url) {
	var token = getCookie(cookieName);
	if(token !=""){
		alert("Token Found");
	}else{
		window.location = url;
	}
}