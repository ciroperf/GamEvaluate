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
<title>Gestione generi</title>

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
<link rel="stylesheet" type="text/css"	href="/GamEvaluate/style/generi.css">


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

<script type="text/javascript">
	var genere;
	function fillSelectGenre() {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 200) {
				document.getElementById("select-genre").innerHTML = xhr.responseText;
			}
			};
			xhr.open("get","/GamEvaluate/selectfiller?target=genre&for_filters=0",true);
			xhr.send(); 
		}
	
	function genereChanged(genre) {
      	genere = genre;
      	document.getElementById("selected-genre").innerHTML = "Genere selezioato: " + genere;
	}
	
	function deleteGenre() {
		
		location.href = "/GamEvaluate/DelGenre?nome=" + genere;
	}
		
</script>

<div class="site-container">
	<%@include file="/presentation/header-nosearch.jsp" %>
	<div class="content-container"></div>
	<div id="content">
	
	<div id = "add">
	<form class="form-group genre-container" method="post" action="/GamEvaluate/AddGenre">
	<p>Aggiungi genere</p>
	<div class="form-group">
	  <label for="nome">Nome:</label>
	  <input type="text" class="form-control" id="nome" name = "nome" required="required">
	</div>
  	<div class="form-group">
	  <label for="testo">Testo:</label>
	  <textarea class="form-control" rows="5" id="testo" name = "descrizione" required="required"></textarea>
	</div>
  	<button type="submit" class="btn btn-default submit">Aggiungi genere</button>
	</form>
	</div>
	
	<div id = "delete">
	
	</div>
	
	<div id = "buttons">

		<p style="font-size: 20px; font-weight: bold">Gestione Generi:</p>
		<button type="button" class="btn btn-default submit buttons-content" onclick= "onAggiungiClicked()">Aggiungi Genere</button><br><br>
		<button type="button" class="btn btn-default submit buttons-content" onclick="onEliminaClicked()">Elimina Genere</button>

	</div>
	</div>
	</div>
	<%@ include file="/presentation/footer.jsp" %>
	

	<script type="text/javascript">

		function onEliminaClicked() {
			
			fillSelectGenre();
			var text =
				"<div id=\'delete-content\''>"
				+"<div>Elimina Genere</div>"
				+"<div class=\'dropdown-container\'>"
				+"<div class=\'dropdown drop-genere\'>"
  				+"<button class=\"genre-button btn btn-secondary dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"
    			+"Genere"
  				+"</button>"
  				+"<div id=\"select-genre\" class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\"></div>"
				+"</div>"
				+"</div>"
				+"<div id =\"selected-genre\"></div>"
				+"<div><button class=\"btn submit\" onclick = \"deleteGenre()\" id = \"delete-button\">Elimina</button></div>"
				+"</div>";
			document.getElementById('add').innerHTML = "";
			document.getElementById('delete').style.visibility='visible';
			document.getElementById('delete').innerHTML= text;
			
		}
		
		function onAggiungiClicked() {
			
			var text = 
			"<div id = \'add\'>"
			+"<form class=\"form-group genre-container\" method=\"post\" action=\"/GamEvaluate/AddGenre\">"
			+"<p>Aggiungi genere</p>"
			+"<div class=\"form-group\">"
			+"<label for=\"nome\">Nome:</label>"
			+"<input type=\"text\" class=\"form-control\" id=\"nome\" name = \"nome\" required=\"required\">"
			+"</div>"
		  	+"<div class=\"form-group\">"
			+"<label for=\"testo\">Testo:</label>"
			+"<textarea class=\"form-control\" rows=\"5\" id=\"testo\" name = \"descrizione\" required=\"required\"></textarea>"
			+"</div>"
		  	+"<button type=\"submit\" class=\"btn btn-default submit\">Aggiungi genere</button>"
			+"</form>"
			+"</div>";

			document.getElementById('add').innerHTML = text;
			document.getElementById('delete').style.visibility='hidden';
			document.getElementById('delete').innerHTML= "";
			
		}
	
	</script>
	
	

</body>
</html>