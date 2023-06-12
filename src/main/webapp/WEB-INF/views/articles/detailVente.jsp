<%@ page import="java.util.List" %>
<%@ page import="fr.eni.projetenchere.messages.LecteurMessage" %>
<%@ page import="fr.eni.projetenchere.bll.CodeErreur" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Détail de la vente</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- CSS Bootstrap -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
	<link href="${pageContext.request.contextPath}/style/headers.css" rel="stylesheet">
</head>
<body>
<!-- Barre de navigation -->
<jsp:include page="/WEB-INF/views/header.jsp" />

<!-- Titre au milieu -->
<div class="container text-center mt-5">
	<h1 class="mb-4">Détail vente</h1>
</div>

<div class="container">
	<div class="row">
		<div class="col-md-6 mb-5">
			<!-- Empty div for spacing -->
			<div style="height: 60px;"></div>
			<div class="card">
				<img src="${pageContext.request.contextPath}/images/articles/article.png" class="card-img-top" alt="image">
			</div>
		</div>
		<div class="col-md-6">
			<h2>${article.nomArticle }</h2>
			<div class="card">
				<div class="card-body">
					<div class="mb-3">
						<h4>Description:</h4>
						<p>${article.description }</p>
					</div>

					<div class="mb-3">
						<h4>Catégorie:</h4>
						<p>${article.categorie.libelle }</p>
					</div>

					<div class="mb-3">
						<h4>Meilleure offre:</h4>
						<div class="d-flex justify-content-between align-items-center">
							<p>${enchere.montantEnchere }</p>
							<div class="card p-2">
								<p class="mb-0">${enchere.proprietaire.pseudo }</p>
							</div>
						</div>
					</div>

					<div class="mb-3">
						<h4>Mise à prix:</h4>
						<p>${article.prixInitial }</p>
					</div>

					<div class="mb-3">
						<h4>Fin de l'enchère:</h4>
						<p>${article.dateFinEncheres }</p>
					</div>

					<div class="mb-3">
						<h4>Retrait:</h4>
						<p>${retrait.rue }, ${retrait.codePostal }, ${retrait.ville }</p>
					</div>

					<div class="mb-3">
						<h4>Vendeur:</h4>
						<p>${article.utilisateur.pseudo }</p>
					</div>

					<div class="mb-3">
						<h4>Ma proposition:</h4>
						<form action="${pageContext.request.contextPath}/creditenchere?id=${param.id}" method="post">
							<div class="input-group mb-3">
								<input type="number" step="1" value="1" min="1" class="form-control" id="articlePrixChoix" name="proposition" required style="margin-right: 10px;">
								<div class="input-group-append">
									<button class="btn btn-primary" type="submit">Enchérir</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- http://localhost:8080/ProjetEnchere/detailvente?id=5&error=20403 -->
	<!-- FACTORISER LIRE URI ET RENVOYER CODE ERROR -->
	<%
	String errorCode = LecteurMessage.getErrorCodeFromURI(request, CodeErreur.ERROR_URL_PATTERN );
		if (errorCode.length() != 0)
		{%>
			<div class="alert alert-danger">
			        <%= LecteurMessage.getMessage(Integer.parseInt(errorCode)) %>
			</div>
	<% } %>
        
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />

<!-- JS Bootstrap -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>