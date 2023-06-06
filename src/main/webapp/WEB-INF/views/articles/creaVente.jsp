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
	<h1 class="mb-4">Nouvelle Vente</h1>
</div>

<div class="container">
	<div class="row">
		<div class="col-md-6 mb-5">
			<img src="${pageContext.request.contextPath}/images/articles/article.png" class="img-thumbnail" alt="image">
		</div>
		<div class="col-md-6">
			<form action="${pageContext.request.contextPath}/AddArticle" method="POST">
				<div class="form-floating mb-3">
					<input type="text" class="form-control" id="articleName" name="nomArticle" placeholder="Ex : xyz@xyz.com" required>
					<label for="articleName">Article</label>
				</div>
				<div class="form-floating mb-3">
					<textarea class="form-control no-resize" id="articleDescription" name="description" rows="3" style="height: 100px; resize: none" required></textarea>
					<label for="articleDescription">Description</label>
				</div>
				<div class="form-floating mb-3">
					<select id="categorie" class="form-select" aria-label="articleCategorie" name="categorie" required>
						<option value="" disabled selected hidden>Selectionner une catégorie</option>
						<c:forEach var="categorie" items="${categories}">
							<option value="${categorie.noCategorie}">${categorie.libelle}</option>
						</c:forEach>
					</select>
					<label for="categorie">Categorie</label>
				</div>
				<div class="form-floating mb-3">
					<input type="file" class="form-control" id="articlePhoto">
					<label for="articlePhoto">Photo de l'article</label>
				</div>
				<div class="form-floating mb-3">
					<input type="number" step="1" value="1" min="1" class="form-control" id="articlePrixChoix" name="miseAPrix" required>
					<label for="articlePrixChoix">Mise à prix</label>
				</div>
				<div class="form-floating mb-3">
					<input type="date" class="form-control" id="enchereDateDebut" name="dateDebutEncheres" value="${dateDebut}" required>
					<label for="enchereDateDebut">Début de l'enchère</label>
				</div>
				<div class="form-floating mb-3">
					<input type="date" class="form-control" id="enchereDateFin" name="dateFinEncheres" value="${dateFin}" required>
					<label for="enchereDateFin">Fin de l'enchère</label>
				</div>
				<fieldset>
					<legend>Retrait</legend>
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="rue" name="rue" value="${user.rue}" required>
						<label for="rue">Rue</label>
					</div>
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="codePostal" name="codePostal" value="${user.codePostal}" required>
						<label for="codePostal">Code Postal</label>
					</div>
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="ville" name="ville" value="${user.ville}" required>
						<label for="ville">Ville</label>
					</div>
				</fieldset>
				<div class="mb-3">
					<button type="submit" class="btn btn-primary">Enregistrer</button>
					<a href="${pageContext.request.contextPath}/"><button type="button" class="btn btn-dark">Annuler</button></a>
				</div>
			</form>
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
