<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gamevaluate.bean.*"%>
    
    
    <%
    	GeneralUser otherUser = null;
    	otherUser = (GeneralUser)session.getAttribute("other-user");
    	GeneralUser user = (GeneralUser)session.getAttribute("user");
    	String message = (String)session.getAttribute("message");
    	ArrayList<Recensione> recensioni = null;
    	ArrayList<String> titoli = null;
    	
    	
    	int role = 0;
    	String sRole = "";
    	boolean yourPage = false;
    	if (otherUser != null) {
    		role = otherUser.getRole();
    		recensioni = (ArrayList<Recensione>)session.getAttribute("recensioni");
    		titoli = (ArrayList<String>)session.getAttribute("titoli-giochi");
    		switch(role) {
    		case 1 :
    			sRole = "Utente";
    			break;
    		case 2 :
    			sRole = "Moderatore";
    			break;
    		case 3 :
    			sRole = "Amministratore";
    			break;
    		}
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
<link rel="stylesheet" type="text/css"	href="/GamEvaluate/style/user-info.css">

</head>
<body>

<div class="site-container">
	<%@include file="/presentation/header-nosearch.jsp" %>
	<div class="content-container"></div>
	<div id="content">
	
	<div class = "utente-container">
		<div class = "utente-data">
		<div class="user-center-container">
		<img class="user-img" alt="Image not found" src="/GamEvaluate/images/user.png">
		<div class="user-name-role">
		<p>Username : <%= otherUser.getUsername()%> </p>
		<p>Role: <%= sRole%></p>
		<p>Email: <%= otherUser.getEmail() %></p>	
		<%
			if (role != 3) {
				String banned = "";
				
				if (otherUser.isBanned())
					banned = "bannato";
				else
					banned = "non bannato";

		%>
			<p>Ban: <%= banned %></p>
		<% 
				
			}
		%>

		</div>
		</div>
		</div>
		
		<div class = "buttons">
		
		<% 
			if (user != null) {
			if (user.getRole() >= 2 && !yourPage && otherUser.getRole()== 1) {
				if (otherUser.isBanned()) {
		%>
					<form method="get" action="/GamEvaluate/moderator/Unban" >
						<input type = "hidden" value= "<%= user.getRole()%>" name = "unbanningUser">
						<input type = "hidden" value= "<%= otherUser.getUsername()%>" name = "username">
						<div class="input-group-btn">
      						<button class="btn btn-default" type="submit">Unban</button>
     					</div>
					</form>
		<%	} else {%>			
					<form method="get" action="/GamEvaluate/moderator/Ban">
						<input type = "hidden" value= "<%= user.getRole()%>" name = "banningUser">
						<input type = "hidden" value= "<%= otherUser.getUsername()%>" name = "username">
						<div class="input-group-btn">
      						<button class="btn" type="submit">Ban</button>
     					</div>
					</form>	
		<%
				}
			}
			
			if (user.getRole() == 3 && !yourPage && otherUser.getRole()!=3) { 
				if (role == 1) {
		%>
					<form method="get" action="/GamEvaluate/admin/Upgrade" >
						<input type = "hidden" value= "<%= otherUser.getUsername()%>" name = "username">
						<div class="input-group-btn">
      						<button class="btn btn-default" type="submit">Upgrade</button>
     					</div>
					</form>
		<%	
				} else if (role == 2) {
		%>
					<form method="get" action="/GamEvaluate/admin/Downgrade" >
						<input type = "hidden" value= "<%= otherUser.getUsername()%>" name = "username">
						<div class="input-group-btn">
      						<button class="btn btn-default" type="submit">Downgrade</button>
     					</div>
					</form>
		<%
				}
			}
			}
		%>
		

		</div>
	
	</div>
	
	<div class = "recensioni-container">
	
	
	<%
		for (int i = 0; i< recensioni.size(); i++) {
			String t = titoli.get(i);
	%>
		
		<div class= "review-container">
		<div class= "review-game-data">
		
		<span class = "titolo"><%=t %></span> <span class = "data"><%= recensioni.get(i).getData() %></span>
		<%
			if (user != null) {
			if ((user.getRole() >= 2 || yourPage) && (otherUser.getRole()!= 3 || yourPage)) {
				
		%>
				<form action="/GamEvaluate/user/DelReview" class = "del-button-container">
					<input type = "hidden" value = "<%=recensioni.get(i).getGioco()%>" name = idGioco>
					<input type = "hidden" value = "<%=recensioni.get(i).getUsername()%>" name = username>
					<input type = "hidden" value = "<%=recensioni.get(i).getData()%>" name = data>
					<input type = "hidden" value = "/GamEvaluate/presentation/user-info.jsp" name = returnTo>
					<input type="image" class="delete-img" alt="Image not found" src="/GamEvaluate/images/delete-button.png" onclick= "return undoDeleteReview();">
				</form>
		<%
			}
			}
		%>
		</div>
		<div class = "review-text">
		<p>Testo:</p>
		<div class = "text">
		<%= recensioni.get(i).getTesto() %>
		</div>
		
		</div>	
		</div>
	
	<%
		}
	%>
	</div>
	
	</div>
	<%@ include file="/presentation/footer.jsp" %>
	
	<script type="text/javascript">
	
		function undoDeleteReview() {
			var richiesta = window.confirm("Sei sicuro di voler eliminare la recensione?");
			
			return richiesta;
		}
	
	</script>

</div>

	<%
		if (session.getAttribute("message") != null) { 
	%>
  				<script type="text/javascript">
     				 alert('<%=message%>');
  				</script>
  	<%
		}
	%>
	
	<% session.removeAttribute("message"); %>


</body>
</html>