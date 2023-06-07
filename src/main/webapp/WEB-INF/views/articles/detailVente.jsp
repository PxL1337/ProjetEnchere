<%@ page import="java.util.List" %>
<%@ page import="fr.eni.projetenchere.messages.LecteurMessage" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Création de la vente</title>
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
			<img src="${pageContext.request.contextPath}/images/articles/article.png" class="img-thumbnail" alt="image">
		</div>
		<div class="col-md-6">				
			<h2>${article.nomArticle }</h2>
			<div class="mb-3 d-flex align-items-center">
	<h4 style="display: inline-block; margin-right: 90px;">Description:</h4>
	<h6 style="display: inline-block; margin: 0;">${article.description }</h6>
</div>

			<div class="mb-2">
				<h4 style="display: inline-block; margin-right: 105px;">Catégorie:</h4>
				<h6 style="display: inline-block; margin: 0;">${article.categorie }</h6>
			</div>		
			<div class="mb-2">
				<h4 style="display: inline-block; margin-right: 50px;">Meilleure offre:</h4>
				<h6 style="display: inline-block; margin: 0;">${enchere.montantEnchere }</h6>
				<h6 style="display: inline-block; margin: 0;"> ${enchere.encherisseur }</h6>
			</div>
			<div class="mb-2">
				<h4 style="display: inline-block; margin-right: 90px;">Mise à prix:</h4>
				<h6 style="display: inline-block; margin: 0;">${article.prixInitial }</h6>
			</div>
			<div class="mb-2">
				<h4 style="display: inline-block; margin-right: 38px;">Fin de l'enchère:</h4>
				<h6 style="display: inline-block; margin: 0;">${article.dateFinEncheres }</h6>
			</div>
			
			
			
			<div class="mb-2">
				<h4 style="display: inline-block; margin-right: 138px;">Retrait:</h4>
				<h6 style="display: inline-block; margin: 0;">${retrait.rue }</h6>
				<h6 style="display: inline-block; margin: 0;">${retrait.codePostal }</h6>
				<h6 style="display: inline-block; margin: 0;">${retrait.ville }</h6>
			</div>
			<div class="mb-2">
				<h4 style="display: inline-block; margin-right: 120px;">Vendeur:</h4>
				<h6 style="display: inline-block; margin: 0;">${user.pseudo }</h6>
			</div>
			
			<div class="mb-2">
			<h4 style="display: inline-block; margin-right: 42px;"><label for="articlePrixChoix">Ma proposition:</label></h4>
					<input type="number" step="1" value="1" min="1" class="form-control-sm" id="articlePrixChoix" name="miseAPrix" required>
				
					<button class="btn btn-primary"  type="submit">Enchérir</button>
					
				</div>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />

<!-- JS Bootstrap -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>