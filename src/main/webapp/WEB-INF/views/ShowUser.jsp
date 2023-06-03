<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<title>${user.pseudo}</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- CSS Bootstrap -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
	<link href="${pageContext.request.contextPath}/style/headers.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/style/sign-in.css" rel="stylesheet">
</head>
<body>
<!-- Barre de navigation -->
<jsp:include page="/WEB-INF/views/header.jsp" />

<div class="container mt-5">
	<div class="card">
		<div class="card-header">
			<h1 class="text-center">Profil de ${user.pseudo }</h1>
		</div>
		<div class="card-body">
			<h5 class="card-title">Nom : ${user.nom }</h5>
			<p class="card-text">Prenom : ${user.prenom }</p>
			<p class="card-text">Email : ${user.email }</p>
			<p class="card-text">Telephone : ${user.telephone }</p>
			<p class="card-text">Adresse : ${user.rue }, ${user.codePostal }, ${user.ville }</p>

			<div class="mt-4 d-flex justify-content-between">
				<a href="lien_vers_servlet_de_modification" class="btn btn-primary">Modifier Profil</a>
				<a href="#" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Supprimer Mon Compte</a>
			</div>
		</div>
	</div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="deleteModalLabel">Confirmation de suppression</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				Êtes-vous sûr de vouloir supprimer votre compte ? Cette action est irréversible.
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
				<a href="${pageContext.request.contextPath}/DeleteAccount" class="btn btn-danger">Supprimer</a>
			</div>
		</div>
	</div>
</div>
<!-- Fin du modal -->

<!-- JS Bootstrap -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
