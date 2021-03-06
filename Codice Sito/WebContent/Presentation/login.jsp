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
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
</head>
<body>
	<script>
		function showPassword() {
			var x = document.getElementById("password");
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
					<a href="/GamEvaluate/presentation/home.jsp"><img
						align="center" id="logoheader" border="0"
						src="/GamEvaluate/images/logo-lungo.png" align="left"
						style="clear: both"></a>
					<hr>
				</div>
				<p class="inserisci">Inserisci le tue credenziali</p>
				<form id="inputform" name="login" action="/GamEvaluate/login"
					method="post">
					<label for="username">Username : </label> <input class="insert"
						type="text" name="username" id="username"
						placeholder="Username..."> <label for="password">Password
						: </label> <input class="insert" type="password" name="password"
						id="password" placeholder="Password..."> <input
						type="checkbox" onclick="showPassword()" name="mostra" id="mostra">
					<label id="showlabel" for="mostra">Mostra Password</label>
					<div id="buttons">
						<input class="button" type="reset" value="Reset"> <input
							class="button" type="submit" value="Enter"> <input
							class="button" type="submit" value="Registrati"
							formaction="signin.jsp">
					</div>
				</form>
			</div>
			<div class = 'alert'>
			<%!String nota;%>
			<%
				if (session.getAttribute("message") != null) {
			%>
			<%
				nota = (String) session.getAttribute("message");
					session.removeAttribute("message");
			%>
			<div class='alert alert-danger'>
				<strong><%=nota %></strong>
			</div>
			<% } %>
			</div>
		</div>
	</div>
</body>
</html>