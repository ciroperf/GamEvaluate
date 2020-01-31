<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
<link rel="stylesheet" type="text/css"	href="/GamEvaluate/style/info-game.css">
<meta charset="ISO-8859-1">
<title>GamEvaluate</title>
<script>
	var voti = {'Gameplay' : '6','Trama' : '6','Grafica' : '6','Creativita' : '6','Innovazione' : '6','Coinvolgimento' : '6','Realismo' : '6','Rigiocabilita' : '6','Difficolta' : '6'};
</script>
</head>
<body onload="fillGameInfo()">
	
	<%@ page import="gamevaluate.bean.GeneralUser" %>
	
	<%
	int id_gioco = Integer.parseInt(request.getParameter("gioco"));
	GeneralUser user = (GeneralUser) session.getAttribute("user");
	String username;
	int role;
	if(user == null) {
		username = "null";
		role = 0;
	} else {
		username = user.getUsername();
		role = user.getRole();
	}
	%>

	<script>
		function fillGameInfo() {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 200) {
				var arr = JSON.parse(xhr.responseText);
				var gioco = arr.gioco;
				var consenti_valutazione = arr.consenti_valutazione;
				var valutazione = arr.valutazione;
				if(gioco.valutazione == '0')
					gioco.valutazione = '-';
				if(valutazione.gameplay == '0')
					valutazione.gameplay = '-';
				if(valutazione.trama == '0')
					valutazione.trama = '-';
				if(valutazione.grafica == '0')
					valutazione.grafica = '-';
				if(valutazione.creativita == '0')
					valutazione.creativita = '-';
				if(valutazione.innovazione == '0')
					valutazione.innovazione = '-';
				if(valutazione.coinvolgimento == '0')
					valutazione.coinvolgimento = '-';
				if(valutazione.realismo == '0')
					valutazione.realismo = '-';
				if(valutazione.rigiocabilita == '0')
					valutazione.rigiocabilita = '-';
				if(valutazione.difficolta == '0')
					valutazione.difficolta = '-';
				var recensioni = arr.recensioni;
				var text = "<div class='header-info-game'>"
						+ "<div class='img-container'>"
						+ "<img class='game-img' alt='Image not found' src='"+gioco.immagine+"'>"
						+ "</div>"
						+ "<div class='main-info'>"
						+ "<p>Titolo : "+gioco.nome+"</p>"
						+ "<p>Genere : "+gioco.genere+"</p>"
						+ "<p>Piattaforma : "+gioco.piattaforma+"</p>"
						+ "<div class='vote-div'>"
						+ "<div class='vote-container'>"
						+ "<p>Voto : "+gioco.valutazione+"</p>"
						+ "</div>"
						+ "</div>"
						+ "<div class='votazioni-attuali'>"
						+ "<p>Num. Voti : "+valutazione.counter+"</p>"
						+ "<br>"
						+ "<p>Gameplay : "+valutazione.gameplay+"</p>"
						+ "<p>Trama : "+valutazione.trama+"</p>"
						+ "<p>Grafica : "+valutazione.grafica+"</p>"
						+ "<p>Creativita' : "+valutazione.creativita+"</p>"
						+ "<p>Innovazione : "+valutazione.innovazione+"</p>"
						+ "<p>Coinvolgimento : "+valutazione.coinvolgimento+"</p>"
						+ "<p>Realismo : "+valutazione.realismo+"</p>"
						+ "<p>Rigiocabilita' : "+valutazione.rigiocabilita+"</p>"
						+ "<p>Difficolta' : "+valutazione.difficolta+"</p>"
						+ "</div>"
						+ "</div>"
						+ "</div>"
						+ "<div class='description-container'>"
						+ "<p class='description-header'>Descrizione :</p>"
						+ "<p class='description'>"+gioco.descrizione+"</p>"
						+ "</div>";
						
				if (consenti_valutazione) {
					text += "<div class='votation-container'>"
								+ "<p id='votation-header'>Valutazione</p>"
								+ "<p id='lab-gameplay'>Gameplay : 6</p>"
								+ "<div class='slidecontainer'>"
								+ "<span>1</span>"
								+ "<input oninput='sliderChanged(\"slid-gameplay\",\"lab-gameplay\",\"Gameplay\")' type='range' min='1' max='10' value='6' class='slider' id='slid-gameplay'>"
								+ "<span>10</span>"
								+ "</div>"
								+ "<p id='lab-trama'>Trama : 6</p>"
								+ "<div class='slidecontainer'>"
								+ "<span>1</span>"
								+ "<input oninput='sliderChanged(\"slid-trama\",\"lab-trama\",\"Trama\")' type='range' min='1' max='10' value='6' class='slider' id='slid-trama'>"
								+ "<span>10</span>"
								+ "</div>"
								+ "<p id='lab-grafica'>Grafica : 6</p>"
								+ "<div class='slidecontainer'>"
								+ "<span>1</span>"
								+ "<input oninput='sliderChanged(\"slid-grafica\",\"lab-grafica\",\"Grafica\")' type='range' min='1' max='10' value='6' class='slider' id='slid-grafica'>"
								+ "<span>10</span>"
								+ "</div>"
								+ "<p id='lab-creativita'>Creativita : 6</p>"
								+ "<div class='slidecontainer'>"
								+ "<span>1</span>"
								+ "<input oninput='sliderChanged(\"slid-creativita\",\"lab-creativita\",\"Creativita\")' type='range' min='1' max='10' value='6' class='slider' id='slid-creativita'>"
								+ "<span>10</span>"
								+ "</div>"
								+ "<p id='lab-innovazione'>Innovazione : 6</p>"
								+ "<div class='slidecontainer'>"
								+ "<span>1</span>"
								+ "<input oninput='sliderChanged(\"slid-innovazione\",\"lab-innovazione\",\"Innovazione\")' type='range' min='1' max='10' value='6' class='slider' id='slid-innovazione'>"
								+ "<span>10</span>"
								+ "</div>"
								+ "<p id='lab-coinvolgimento'>Coinvolgimento : 6</p>"
								+ "<div class='slidecontainer'>"
								+ "<span>1</span>"
								+ "<input oninput='sliderChanged(\"slid-coinvolgimento\",\"lab-coinvolgimento\",\"Coinvolgimento\")' type='range' min='1' max='10' value='6' class='slider' id='slid-coinvolgimento'>"
								+ "<span>10</span>"
								+ "</div>"
								+ "<p id='lab-realismo'>Realismo : 6</p>"
								+ "<div class='slidecontainer'>"
								+ "<span>1</span>"
								+ "<input oninput='sliderChanged(\"slid-realismo\",\"lab-realismo\",\"Realismo\")' type='range' min='1' max='10' value='6' class='slider' id='slid-realismo'>"
								+ "<span>10</span>"
								+ "</div>"
								+ "<p id='lab-rigiocabilita'>Rigiocabilita' : 6</p>"
								+ "<div class='slidecontainer'>"
								+ "<span>1</span>"
								+ "<input oninput='sliderChanged(\"slid-rigiocabilita\",\"lab-rigiocabilita\",\"Rigiocabilita\")' type='range' min='1' max='10' value='6' class='slider' id='slid-rigiocabilita'>"
								+ "<span>10</span>"
								+ "</div>"
								+ "<p id='lab-difficolta'>Difficolta : 6</p>"
								+ "<div class='slidecontainer'>"
								+ "<span>1</span>"
								+ "<input oninput='sliderChanged(\"slid-difficolta\",\"lab-difficolta\",\"Difficolta\")' type='range' min='1' max='10' value='6' class='slider' id='slid-difficolta'>"
								+ "<span>10</span>" + "</div>" 
								+ "<a class='btn-vote' href='#' onclick='votaClicked()'>Vota gioco</a>"
								+ "</div>";
				}
				
				
				text += "<div id='review-container' class='review-container'>"
				+ "<div class='header-review'>"
				+ "<div class='review-header-container'>"
				+ "<p>Recensioni</p>"
				+ "</div>"
				+ "<div class='review-btn-container'>"
				+ "<button onclick='addReviewClicked()'>AggiungiRecensione</button>"
				+ "</div>"
				+ "</div>";				
				var i;
				for(i = 0; i < recensioni.length; i++) {
					text += "<div class='single-review-container'>"
					+ "<div class='review-info'>"
					+"<div class='review-above'>"
					+"<p>Gioco : "+gioco.nome+"</p>"
					+"<p>Data : "+recensioni[i].data+"</p>";
						if(recensioni[i].username == '<%=username%>' || <%=role%> == 2 || <%=role%> == 3)
							text += "<form action=\"/GamEvaluate/DelReview\" class = \"del-button-container\">"
						  +	"<input type = \"hidden\" value = '"+recensioni[i].gioco+"' name = idGioco>"
						  +	"<input type = \"hidden\" value = '"+recensioni[i].username+"' name = username>"
						  +	"<input type = \"hidden\" value = '"+recensioni[i].data+"' name = data>"
					      +	"<input type = \"hidden\" value = \"/GamEvaluate/presentation/info-game.jsp?gioco="+gioco.id+"\" name = returnTo>"
						  +	"<input type=\"image\" class=\"delete-img\" alt=\"Image not found\" src=\"/GamEvaluate/images/delete-button.png\" onclick= \"return undoDeleteReview();\">"
						  + "</form>";
					text += "</div>"
					+"<div class='review-below'>"
					+"<p>Utente : "+recensioni[i].username+"</p>"
					+"<p>Testo : </p>"
					+"<p>"+recensioni[i].testo+"</p>"
					+ "</div>"
					+"</div>"
					+ "</div>";
				}
				text += "</div>";
				
				document.getElementById("content").innerHTML = text;
			}
			};
			xhr.open("get", "/GamEvaluate/infogame?gioco=" +<%=id_gioco%>,true);
			xhr.send(); 
		}
		function sliderChanged(slid,lab,stringa) {
			var valore = document.getElementById(slid).value
			document.getElementById(lab).innerHTML = stringa+" : "+valore;
			voti[stringa] = valore;
		}
		function votaClicked() {
			var body = "id_gioco="+<%=id_gioco%>+"&gameplay="+voti['Gameplay']+"&trama="+voti['Trama']+"&grafica="+voti['Grafica']+"&creativita="+voti['Creativita']+"&innovazione="+voti['Innovazione']+"&coinvolgimento="+voti['Coinvolgimento']+"&realismo="+voti['Realismo']+"&rigiocabilita="+voti['Rigiocabilita']+"&difficolta="+voti['Difficolta'];
			console.log(<%=id_gioco%>);
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if(xhr.readyState == 4 && xhr.status == 200) {
					location.reload();
				}};
			xhr.open("get","/GamEvaluate/votegame?"+body,true);
			xhr.send();
		}
		function addReviewClicked() {
			var text = "<div class='header-review'>"
			+ "<div class='review-header-container'>"
			+ "<p>Recensioni</p>"
			+ "</div>"
			+ "<div class='review-btn-container'>"
			+ "<button onclick='addReviewClicked()'>AggiungiRecensione</button>"
			+ "</div>"
			+ "</div>"
			+ "<div class='review-form'>"
			+ "<form method='post' action='/GamEvaluate/addreview'>"
			+ "<input type='hidden' name='idGioco' value='"+<%=id_gioco%>+"'>"
			+ "<div class=\"form-group\">"
		    + "<label for=\"review-textarea\">Testo recensione : </label>"
		    + "<textarea name='testo' class=\"form-control review-input\" id=\"review-textarea\" rows=\"5\"></textarea>"
		  	+ "</div>"
		  	+ "<div class='submit-div'>"
		  	+ "<button type=\"submit\" class=\"btn-addreview btn btn-default button\">Lascia recensione</button>"
		  	+ "</div>"
			+ "</form>"
			+ "</div>";
			document.getElementById("review-container").innerHTML = text;
		}
	</script>
	<div class="site-container">
		<%@include file="/presentation/header-nosearch.jsp" %>
		<div class="content-container"></div>
		<div id="content">
			
		</div>
		<%@include file="/presentation/footer.jsp" %>
	</div>
</body>
</html>