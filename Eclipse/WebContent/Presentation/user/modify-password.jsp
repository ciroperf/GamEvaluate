<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="gamevaluate.bean.*, java.util.*"%>
    
    <%
    
    GeneralUser user = (GeneralUser)session.getAttribute("user");
	String message = (String)session.getAttribute("message");
	
	if (user == null) {
		response.sendRedirect("/GamEvaluate/presentation/home.jsp");
		return;
	} else {
		String password = user.getPassword();
	}
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica password</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"	href="/GamEvaluate/style/home.css">
<link rel="stylesheet" type="text/css"	href="/GamEvaluate/style/header-nosearch.css">
<link rel="stylesheet" type="text/css"	href="/GamEvaluate/style/footer.css">
<link rel="stylesheet" type="text/css"	href="/GamEvaluate/style/modify-pass.css">


	<%
		if (message != null) { 
	%>
  				<script type="text/javascript">
     				 alert('<%=message%>');
  				</script>
  	<%
  			session.removeAttribute("message");
		}
	%>
</head>
<body>

<div class="site-container">
	<%@include file="/presentation/header-nosearch.jsp" %>
	<div class="content-container"></div>
	<div id="content">
	
	<form class="form-group pass-container" method="post" action="/GamEvaluate/ModifyPass">
	<p>La password deve essere di almeno 5 caratteri</p>
	<input type = "hidden" value = <%= user.getUsername() %> name = username>
	<div class="form-group">
    	<label for="pwd">Password:</label>
    	<input type="password" class="form-control" pattern=".{5,}" name = "password1" id = "password1">
  	</div>
  	<div class="form-group">
    	<label for="pwd">Conferma password:</label>
    	<input type="password" class="form-control" pattern=".{5,}" name = "password2" id = "password2">
  	</div>
  	<div class="checkbox">
   	 	<label><input type="checkbox" onclick="showPassword()">Show password</label>
  	</div>
  	<button type="submit" class="btn btn-default submit">Modifica pass</button>

	</form>
		
	</div>
	</div>
	<%@ include file="/presentation/footer.jsp" %>
	
	<script>
		function showPassword() {
			var x = document.getElementById("password1");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
			
			x = document.getElementById("password2");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
	</script>
	
	

</body>
</html>