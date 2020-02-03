<%@page import="java.awt.Window"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/GamEvaluate/style/login.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<title>Sign in</title>
</head>
<body>
	<script>
		function showPassword1() {
			var x = document.getElementById("password1");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
		function showPassword2() {
			var x = document.getElementById("password2");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
	</script>
	<div id="outer">
		<div id="middle">
			<div id="loginform">
				<div id="miniheader">
					<a href="/GamEvaluate/presentation/home.jsp"><img align="center"
						id="logoheader" border="0"
						src="/GamEvaluate/images/logo-lungo.png" align="left"
						style="clear: both"></a> <br>
					<hr>
				</div>
				<div class="formwrapper">
				<form action="/GamEvaluate/signin"	method="post">
					<div class="form-group">
						<p>Inserisci i tuoi dati</p>
					</div>
					<div class="form-group">
    					<label for="username">Username:</label>
    					<input type="text" class="form-control" id="user" name="user" required>
  					</div>
  					<div class="form-group">
    					<label for="email">Email address:</label>
    					<input type="email" class="form-control" id="email" name="email" required>
  					</div>
  					<div class="form-group">
    					<label for="password1">Password:</label>
    					<input type="password" class="form-control" id="password1" name="password1" required>
    					<div class="checkbox">
							<label><input type="checkbox" onclick="showPassword1()"> Mostra Password</label>
						</div>
  					</div>
  					<div class="form-group">
    					<label for="password2">Conferma Password:</label>
    					<input type="password" class="form-control" id="password2" name="password2" required>
    					<div class="checkbox">
							<label><input type="checkbox" onclick="showPassword2()"> Mostra Password</label>
						</div>
  					</div>
  					<button type="submit" class="btn btn-default button">Registrati</button>
				</form>
				</div>
			</div>
			<div class="alert">
				<%!String nota = "";%>
				<%
					if (session.getAttribute("message") != null) {
				%>
				<%
					nota = (String) session.getAttribute("message");
						session.removeAttribute("message");
				%>
				<div class="alert alert-danger">
					<strong><%=nota %></strong>
				</div>
				<% } %>
			</div>
		</div>
	</div>
</body>
</html>