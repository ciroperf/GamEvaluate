<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gamevaluate.bean.GeneralUser"%>
    
    
    <%
    	GeneralUser otherUser = null;
    	otherUser = (GeneralUser)session.getAttribute("other-user");
    	GeneralUser user = (GeneralUser)session.getAttribute("user");
    	int role = 0;
    	boolean yourPage = false;
    	if (otherUser != null ) {
    		role = otherUser.getRole();
    		if (otherUser.equals(user))
    			yourPage = true;
    			
    	}
    	else {
    		response.sendRedirect("/GamEvaluate/presentation/home.jsp");
    		return;
    	}
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User info</title>

<link rel="stylesheet" type="text/css"	href="/GamEvaluate/style/home.css">
<link rel="stylesheet" type="text/css"	href="/GamEvaluate/style/header-search.css">
<link rel="stylesheet" type="text/css"	href="/GamEvaluate/style/footer.css">
<link rel="stylesheet" type="text/css"	href="/GamEvaluate/style/user-info.css">
</head>
<body>

<div class="site-container">
	<%@include file="/presentation/header-no-search.jsp" %>
	<div class="content-container"></div>
	<div id="content">
	
	
	
	
	

	</div>
	<%@include file="/presentation/footer.jsp" %>
</div>

</body>
</html>