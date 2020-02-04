<%@page import="gamevaluate.bean.GeneralUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
</head>
<style>
  .toggle.ios, .toggle-on.ios, .toggle-off.ios { border-radius: 20rem; }
  .toggle.ios .toggle-handle { border-radius: 20rem; }
</style>
<body>
	
	<%GeneralUser user = (GeneralUser) session.getAttribute("user");
		int role = 0;
		if(user != null)
			role = user.getRole();
	%>
	
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

	<script>
		var target = "giochi";
		var valutazione = 0;
		var titolo = 0;
		var genere = "Tutti";
		var piattaforma = "Tutte";
		
		
		function toggleChanged() {
	      	if(document.getElementById('toggle-search').checked)
	      		target = "giochi";
	      	else
	      		target = "utenti";
	      	doSearch();
	  	}
		
		
		function genereChanged(genre) {
	      	genere = genre;
	      	console.log(genere);
	      	doSearch();
	  	}
		
		function piattaformaChanged(platform) {
	      	piattaforma = platform;
	      	console.log(piattaforma);
	      	doSearch();
	  	}
		
		function fillSelectGenre() {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 200) {
				document.getElementById("select-genre").innerHTML = xhr.responseText;
			}
			};
			xhr.open("get","/GamEvaluate/selectfiller?target=genre&for_filters=1",true);
			xhr.send(); 
		}
		function fillSelectPlatform() {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 200) {
				document.getElementById("select-platform").innerHTML = xhr.responseText;
			}
			};
			xhr.open("get","/GamEvaluate/selectfiller?target=platform&for_filters=1",true);
			xhr.send(); 
		}
		function fillSidebar() {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 200) {
				document.getElementById("sidenav").innerHTML = xhr.responseText;
			}
			};
			xhr.open("get","/GamEvaluate/fillsidebar",true);
			xhr.send(); 
		}
		function fillHome() {
			fillSelectGenre();
			fillSelectPlatform();
			fillSidebar();
			doSearch();
			$('#group-vote button').on('click', function() {
			    var thisBtn = $(this);
			    
			    valutazione = thisBtn.val();
			    titolo = 0;
			    console.log(valutazione);
			    doSearch();
			  });
			$('#group-title button').on('click', function() {
			    var thisBtn = $(this);
			    
			    titolo = thisBtn.val();
			    console.log(titolo);
			    valutazione = 0;
			    doSearch();
			  });
			
		}
		function undoDeleteGame() {
			var richiesta = window.confirm("Sei sicuro di voler eliminare il gioco?");
			
			return richiesta;
		}
		function doSearch() {
				var xhr2 = new XMLHttpRequest();
				xhr2.onreadystatechange = function() {
					if(xhr2.readyState == 4 && xhr2.status == 200) {
						var arr = JSON.parse(xhr2.responseText);
						if(target == "utenti") {
							var utenti = arr.utenti;
							var text = "";
							if(utenti.length != 0) {
								var i;
								for(i = 0; i < utenti.length; i++) {
									text = text.concat("<div class='utente-container' onclick=\'window.location.href = \"/GamEvaluate/VisualizzaUtente?username=" + utenti[i].username + "\"\' >"
											+ "<div class='utente-data'>"
											+ "<div class='user-left-container'>"
											+ "<img class='user-img' alt='Image not found' src='/GamEvaluate/images/user.png'>"
											+ "<div class='user-name-role'>"
											+ "<p>Username : "+utenti[i].username+"</p>");
									var role = "";
									switch(utenti[i].role) {
										case 0 :
											role = "Visitatore";
											break;
										case 1 :
											role = "Utente";
											break;
										case 2 :
											role = "Moderatore";
											break;
										case 3 :
											role = "Amministratore";
											break;
									}
									text = text.concat("<p>Role : "+role+"</p>"
											+ "</div>"
											+ "</div>"
											+ "<div class='user-email'>"
											+ "<p>Email : "+utenti[i].email+"</p>"
											+ "</div>"
											+ "</div>");
									text = text.concat("</div>");
								}
							} else {
								text = text.concat("<div class='no-found'>"
										+ "<p>Nessun utente trovato</p>"
										+ "</div>");
							}
							document.getElementById("content").innerHTML = text;
						} else {
							var giochi = arr.giochi;
							var text = "";
							if(giochi.length != 0) {
								var i;
								for(i = 0; i < giochi.length; i++) {
									if(giochi[i].valutazione == '0')
										giochi[i].valutazione = '-';
									text = text.concat("<a class='game-container-link' href='/GamEvaluate/presentation/info-game.jsp?gioco="+giochi[i].id+"'>"
											+ "<div class='game-container'>"
											+ "<div class='game-data'>"
											+ "<div class='game-left-container'>"
											+ "<img class='game-img' alt='Image not found' src='"+giochi[i].immagine+"'>"
											+ "<div class='game-name-genre'>"
											+ "<p>Titolo : "+giochi[i].nome+"</p>"
											+ "<p>Genere: "+giochi[i].genere+"</p>"
											+ "</div>"
											+ "</div>"
											+ "<div class='game-platform'>"
											+ "<p>Piattaforma : "+giochi[i].piattaforma+"</p>"
											+ "<div class='game-vote'>"
											+ "<p class='vote-text'>Voto</p>"
											+ "<div class='vote-container'><span>"+giochi[i].valutazione+"</span></div>"
											+ "</div>");
									if(<%=role%> == 3) {
										text = text.concat("<form  action='/GamEvaluate/admin/DelGame' class = \"del-button-container\">"
											  +	"<input type = \"hidden\" value = '"+giochi[i].id+"' name = idgioco>"
											  +	"<input type=\"image\" class=\"delete-img\" alt=\"Image not found\" src=\"/GamEvaluate/images/delete-button.png\" onclick= \"return undoDeleteGame();\">"
											  + "</form>");
										text = text.concat("<form  action='/GamEvaluate/presentation/admin/modify-game.jsp' class = \"del-button-container\">"
												  +	"<input type = \"hidden\" value = '"+giochi[i].id+"' name = gioco>"
												  +	"<input type=\"image\" class=\"modify-img\" alt=\"Image not found\" src=\"/GamEvaluate/images/wrench.png\">"
												  + "</form>");
									}
									text = text.concat("</div>"
											+ "</div>");
									text = text.concat("</div></a>");
								}
							} else {
								text = text.concat("<div class='no-found'>"
										+ "<p>Nessun gioco trovato</p>"
										+ "</div>");
							}
							document.getElementById("content").innerHTML = text;
						}
					}
				};
				xhr2.open("get","/GamEvaluate/search?target="+target+"&valutazione="+valutazione+"&titolo="+titolo+"&genere="+genere+"&piattaforma="+piattaforma+"&value="+document.getElementById("searchbar").value,true);
				xhr2.send();
			}
	</script>
	<div class="header-container">
		<a class ="header-img" href="/GamEvaluate/presentation/home.jsp"><img lt="File not found" src="/GamEvaluate/images/logo-lungo.png"></a>
		<% if (user != null) { 
		
			String ruolo = "";
			switch(user.getRole()) {
			case 1: ruolo = "utente"; break;
			case 2: ruolo = "moderatore"; break;
			case 3: ruolo = "amministratore"; break;
			}
		%>
			<div style = "position: absolute; z-index: 15; font-size: 20px; font-weight: bold; left: 40px;">Benvenuto: <%= user.getUsername() %>, <%= ruolo %></div>
		<% } %>
		<div class="search-container ">
			<div class="active-black-3 active-black-4 mb-4 searchbar">
  				<input oninput="doSearch()" id="searchbar" class="form-control" type="text" placeholder="Search" aria-label="Search">
			</div>
			<div class="toggle-container">
				<input onchange="toggleChanged()" id="toggle-search" type="checkbox" checked data-onstyle="danger" data-offstyle="danger" data-toggle="toggle" data-on="Giochi" data-off="Utenti" data-style="ios">
			</div>
		</div>
		<div class="filters-container">
			<p class="filter-header">Filtri</p>
			<div class="filters-form-container">
				<p class="filter-name">Votazione</p>
				<div id="group-vote" class="btn-group" role="group" aria-label="Basic example">
  					<button type="button" value="1" class="filter-button btn btn-secondary">Crescente</button>
  					<button type="button" value="2" class="filter-button btn btn-secondary">Decrescente</button>
				</div>
				<p class="filter-name">Titolo</p>
				<div id="group-title" class="btn-group" role="group" aria-label="Basic example">
  					<button type="button" value="1" class="filter-button btn btn-secondary">Crescente</button>
  					<button type="button" value="2" class="filter-button btn btn-secondary">Decrescente</button>
				</div>
				<div class="dropdown-container">
					<div class="dropdown drop-genere">
  						<button class="filter-button btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    						Genere
  						</button>
  						<div id="select-genre" class="dropdown-menu" aria-labelledby="dropdownMenuButton"></div>
					</div>
					<div class="dropdown drop-piattaforma">
  						<button class="filter-button btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    						Piattaforma
  						</button>
  						<div id="select-platform" class="dropdown-menu" aria-labelledby="dropdownMenuButton"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>