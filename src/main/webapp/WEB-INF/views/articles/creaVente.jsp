<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link href="${pageContext.request.contextPath}/style/articleCreation.css" rel="stylesheet">
    
</head>
<body>

<!-- Barre de navigation -->
<jsp:include page="/WEB-INF/views/header.jsp" />
 
 <!--Titre au milieur-->
 <div id=centre>
 	<h1>Nouvelle Vente</h1>
 </div>

<br><br>

<div class="container">
<div class="row justify-content-start">
	<div class="col-sm-4">	
			<img src="${pageContext.request.contextPath}/article/shogun.jpg" class="img-thumbnail" alt="image">
	</div>	
	<div class="col-sm-8">
		<form action="${pageContext.request.contextPath}/AddArticle" method="POST" class="row g-3">
			<div class="container">
				<div class="col-sm-12">
					<div class="form-floating">
						<input type="text" class="form-control" id="articleName" name="nomArticle" placeholder="Ex : xyz@xyz.com" required>
						<label for="articleName">Article</label>
					</div>
				</div>
				<br>
				<div class="col-sm-12">
					<div class="form-floating">
						<textarea class="form-control" id="articleDescription" name="description" rows="3" required></textarea>
						<label for="articleDescription">Description</label>
					</div>
				</div>
				<br>
				<div class="col-sm-12">
					<select id="categorie" class="form-select" aria-label="articleCategorie" name="categorie" required>
						<option selected>Selectionner une catégorie</option>
						<option value="1">Informatique</option>
						<option value="2">Ameublement</option>
						<option value="3">Vêtement</option>
					</select>
					<label for="categorie"></label>
				</div>
				<br>
				<div class="col-sm-12">
					<div class="form-floating">
						<input type="file" class="form-control" id="articlePhoto">
						<label for="articlePhoto">Photo de l'article</label>
					</div>
				</div>
				<br>
				<div class="col-sm-12">
					<div class="form-floating">
						<input type="number" step="1" value="0" min="0" max="99999" class="form-control" id="articlePrixChoix" name="miseAPrix" required>
						<label for="articlePrixChoix">Mise à prix</label>
					</div>
				</div>
				<br>
				<div class="col-sm-12">
					<div class="form-floating">
						<input type="date" class="form-control" id="enchereDateDebut" name="dateDebutEncheres" required>
						<label for="enchereDateDebut">Début de l'enchère</label>
					</div>
				</div>
				<br>
				<div class="col-lg-12">
					<div class="form-floating">
						<input type="date" class="form-control" id="enchereDateFin" name="dateFinEncheres" required>
						<label for="enchereDateFin">Fin de l'enchère</label>
					</div>
					<br>
				</div>
			</div>
			<br>
			<div class="container">
				<fieldset>
					<legend align="left">Retrait</legend>
					<div class="col-sm-12">
						<div class="form-floating">
							<input type="text" class="form-control" id="rue" name="rue">
							<label for="rue">Rue</label>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="form-floating">
							<input type="text" class="form-control" id="codePostal" name="codePostal">
							<label for="codePostal">Code Postal</label>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="form-floating">
							<input type="text" class="form-control" id="ville" name="ville" required>
							<label for="ville">Ville</label>
						</div>
					</div>
				</fieldset>
				<br>
				<div class="container">
					<button type="submit" class="btn btn-primary mb-2">Enregistrer</button>
					<button type="button" class="btn btn-dark mb-2">Annuler</button>
				</div>
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
