<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="gamevaluate.bean.*, java.util.*"%>

<%
	GeneralUser user = (GeneralUser) session.getAttribute("user");
	String message = (String) session.getAttribute("message");

	if (user == null) {
		response.sendRedirect("/GamEvaluate/presentation/home.jsp");
		return;
	} else {
		String password = user.getPassword();
	}
	
	int id_gioco = Integer.parseInt(request.getParameter("gioco"));

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica gioco</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link
	href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="/GamEvaluate/style/home.css">
<link rel="stylesheet" type="text/css"
	href="/GamEvaluate/style/header-nosearch.css">
<link rel="stylesheet" type="text/css"
	href="/GamEvaluate/style/footer.css">
<link rel="stylesheet" type="text/css"
	href="/GamEvaluate/style/add-game.css">


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

<script type="text/javascript">

	var genere;
	var piattaforma;
	function fillSelectGenre() {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				document.getElementById("select-genre").innerHTML = xhr.responseText;
			}
		};
		xhr.open("get", "/GamEvaluate/selectfiller?target=genre&for_filters=0",
				true);
		xhr.send();
	}

	function genereChanged(genre) {
		genere = genre;
		document.getElementById("selected-genre").innerHTML = "Genere selezioato: "
				+ genere;
		document.getElementById("genre").value=genere; 
	}

	function fillSelectPlatform() {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				document.getElementById("select-platform").innerHTML = xhr.responseText;
			}
		};
		xhr.open("get","/GamEvaluate/selectfiller?target=platform&for_filters=0",true);
		xhr.send();
	}

	function piattaformaChanged(platform) {
		piattaforma = platform;
		document.getElementById("selected-platform").innerHTML = "Piattaforma selezionata: "
				+ piattaforma;
		document.getElementById("platform").value=piattaforma; 
	}
	
	function fillDropdowns() {
		
		fillSelectPlatform();
		fillSelectGenre();
	}

	function fillGameInfo() {
		
		
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var arr = JSON.parse(xhr.responseText);
			var gioco = arr.gioco;
			var text = 
				"<div class=\"add-game\">"
				+"<div class=\"game-data\">"
				+"<form class=\"form-group game-container\" method=\"post\""
					+" action=\"/GamEvaluate/admin/ModifyGame\">"
					+"<p>Modifica Gioco</p>"
					+"<input type = \"hidden\" name = \"id\" value = " + gioco.id + ">"
					+"<div class=\"form-group\">"
					+"<label for=\"nome\">Nome:</label> <input type=\"text\" value='" + gioco.nome
					+"' class=\"form-control\" id=\"nome\" name=\"nome\" required=\"required\" style = \"font-size: 15px;\">"
					+"</div>"
					+"<div class=\"form-group\">"
					+"<label for=\"testo\">Testo:</label>"
					+"<textarea class=\"form-control\" rows=\"5\" id=\"testo\""
					+" name=\"descrizione\" required=\"required\" style = \"font-size: 15px;\">" + gioco.descrizione + "</textarea>"
					+"</div>"
					+"<div class=\"form-group\">"
					+"<label for=\"immagine\">Immagine (URL): </label> <input type=\"text\""
					+" value='"+ gioco.immagine + "' name=\"immagine\" required" 
					+" style=\"width: 250px;\" onchange=\"checkImage(this.value);\">"
					+"<p id=\"imageInfo\">L'icona non può essere più grande di"
					+" 512x350</p>"
					+"<div id=\"imageTest\"><img src = '" + gioco.immagine + "' height = '" + "100" + "' width = '" + "100' ></div>"
					+"</div>"
					+"<div class=\"form-group\">"
					+"<input type = \"hidden\" id = \"genre\" name = \"genere\" value = '" + gioco.genere + "'>"
					+"<div class=\"dropdown-container\">"
					+"<div class=\"dropdown drop-genere\">"
					+"<button class=\"genre-button btn btn-secondary dropdown-toggle\""
					+" type=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\""
					+" aria-expanded=\"false\">Genere:</button>"
					+"<div id='select-genre' class=\"dropdown-menu\ aria-labelledby=\"dropdownMenuButton\"></div>"
					+"</div>"
					+"</div>"
					+"<div id=\"selected-genre\">Genere Selezionato: " + gioco.genere + "</div>"
					+"</div>"
					+"<div class=\"form-group\">"
					+"<input type = \"hidden\" id = \"platform\" name = \"piattaforma\" value = '" + gioco.piattaforma +"'>"
					+"<div class=\"dropdown-container\">"
					+"<div class=\"dropdown drop-piattaforma\">"
					+"<button class=\"platform-button btn btn-secondary dropdown-toggle\""
					+" type=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\""
					+" aria-expanded=\"false\">Piattaforma:</button>"
					+"<div id='select-platform' class=\"dropdown-menu\""
					+" aria-labelledby=\"dropdownMenuButton\"></div>"
					+"</div>"
					+"</div>"
					+"<div id='selected-platform'>Piattaforma Selezionata: " + gioco.piattaforma + "</div>"
					+"</div>"
					+"<button type=\"submit\" id=\"button\" class=\"btn btn-default submit\">Modifica</button>"
					+"</form>"
					+"</div>"
					+"</div>"
		
			document.getElementById("content").innerHTML = text;
			fillDropdowns();
		}
	}
		xhr.open("get", "/GamEvaluate/admin/FillModifyGame?gioco=" +<%=id_gioco%>,true);
		xhr.send();
	};
	
	
</script>
</head>
<body onload="fillGameInfo()">


	<div class="site-container">
		<%@include file="/presentation/header-nosearch.jsp"%>
		<div class="content-container"></div>
		<div id="content">

			
		</div>
	</div>
	<%@ include file="/presentation/footer.jsp"%>

	<script>
		var validImg = false;

		function checkURL(url) {
			return (url.match(/\.(jpeg|jpg|png)$/) != null);
		}

		function checkImage(url) {
			var max_height = 512;
			var max_width = 350;
			var w;
			var h;
			var img = new Image;
			img.src = url;
			img.onload = function() {
				w = this.width;
				h = this.height;
				if (w > max_width || h > max_height) {

					document.getElementById("button").disabled = true;
					document.getElementById("imageInfo").style.color = "red";
					document.getElementById("imageTest").innerHTML = "";
					document.getElementById("imageInfo").innerHTML = "L'icona supera le dimensioni consentite";
				} else {
					document.getElementById("button").disabled = false;
					document.getElementById("imageInfo").style.color = "black";
					document.getElementById("imageInfo").innerHTML = "L'icona non può essere più grande di 512x512";
					document.getElementById("imageTest").innerHTML = "<img src = '" + url + "' height = '" + "100" + "' width = '" + "100' >";
				}
			};
		}
	</script>


</body>
</html>