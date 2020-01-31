<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header noSearch</title>
</head>
<body>
	<div class="header-container">
		<div class = 'alert'>
			<%!String nota;%>
			<%
				if (session.getAttribute("message") != null) {
					nota = (String) session.getAttribute("message");
			%>
			<script type="text/javascript">
     				 alert('<%=nota%>');
  				</script>
			<%
					session.removeAttribute("message"); }
			%>
			</div>
		<a class ="header-img" href="/GamEvaluate/presentation/home.jsp"><img alt="File not found" src="/GamEvaluate/images/logo-lungo.png"></a>
	</div>
</body>
</html>