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
				<div>
					<a href="${pageContext.request.contextPath}/ModifyUserProfile" class="btn btn-primary">Modifier Profil</a>
					<button type="button" class="btn btn-secondary" onclick="location.href='${pageContext.request.contextPath}/';">Retour</button>
				</div>
				<% if (request.getAttribute("message") != null) { %>
				<div class="alert alert-success alert-dismissible fade show" role="alert">
					<!--<svg class="bi flex-fill me-0" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>-->
					<%= request.getAttribute("message") %>
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
				<% } %>
				<a href="#" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Supprimer Mon Compte</a>
			</div>
		</div>
	</div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

<jsp:include page="/WEB-INF/views/modal/DeleteAccountModal.jsp" />

<!-- JS Bootstrap -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
