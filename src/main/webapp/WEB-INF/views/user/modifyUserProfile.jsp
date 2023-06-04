<%@ page import="java.util.List" %>
<%@ page import="fr.eni.projetenchere.messages.LecteurMessage" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User profile modification</title>
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

<!-- À mettre en page pour afficher les éléments dans un ordre choisi. -->
<div class="container">
	<h1 id="pageName"> Modifier mon Profil </h1>

	<form action="${pageContext.request.contextPath}/ModifyUserProfile" method="POST" class="row g-3">
		<div class="col-12">
			<div class="form-floating">
				<input type="text" id="pseudo" name="pseudo" class="form-control" placeholder="Ex : Michoudu78" value="">
				<label for="pseudo">Pseudo</label>
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-floating">
				<input type="text" id="nom" name="nom" class="form-control" placeholder="Ex : Durand" value="" >
				<label for="nom">Nom</label>
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-floating">
				<input type="text" id="prenom" name="prenom" class="form-control" placeholder="Ex : Bernard" value="" >
				<label for="prenom">Prénom</label>
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-floating">
				<input type="email" id="email" name="email" class="form-control" pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" placeholder="Ex : xyz@xyz.com" oninvalid="this.setCustomValidity('Veuillez entrer un email valide. Exemple : xyz@xyz.com')" oninput="this.setCustomValidity('')" value="" >
				<label for="email">Email</label>
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-floating">
				<input type="tel" id="tel" name="telephone" class="form-control" pattern="[0-9]{10}" placeholder="Ex : 0123456789" title="Veuillez entrer un numéro de téléphone à 10 chiffres." oninvalid="this.setCustomValidity('Veuillez entrer un numéro de téléphone à 10 chiffres.')" oninput="this.setCustomValidity('')" value="">
				<label for="tel">Téléphone</label>
			</div>
		</div>

		<div class="col-12">
			<div class="form-floating">
				<input type="text" id="rue" name="rue" class="form-control" placeholder="Ex : rue de la paix" value="" >
				<label for="rue">Adresse</label>
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-floating">
				<input type="text" id="codePostal" name="codePostal" class="form-control" placeholder="Ex : 01000" oninvalid="this.setCustomValidity('Veuillez entrer un code postal à 5 chiffres.')" oninput="this.setCustomValidity('')" value="" >
				<label for="codePostal">Code Postal</label>
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-floating">
				<input type="text" id="ville" name="ville" class="form-control" placeholder="Ex : Madrid" value="" >
				<label for="ville">Ville</label>
			</div>
		</div>

		<div class="col-12">
			<div class="form-floating">
				<input type="password" id="motDePasse" name="motDePasse" class="form-control" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$" placeholder="Ex : MySecuredPassword" oninvalid="this.setCustomValidity('Le mot de passe doit contenir au moins 8 caractères, une lettre majuscule, une lettre minuscule et un chiffre.')" oninput="this.setCustomValidity('')" required>
				<label for="motDePasse">Mot de passe</label>
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-floating">
				<input type="password" id="newMdp" name="newMdp" class="form-control" placeholder="Nouveau mot de passe" >
				<label for="newMdp">Nouveau mot de passe</label>
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-floating">
				<input type="password" id="confirmationMdp" name="confirmationMdp" class="form-control" placeholder="Confirmation du nouveau mot de passe" >
				<label for="confirmationMdp">Confirmation du nouveau mot de passe</label>
			</div>
		</div>

		<p>Crédit : ${user.credit}</p>

		<div class="mt-4 d-flex justify-content-between">
			<div>
				<input type="submit" value="Enregistrer" class="btn btn-primary">
				<button type="button" class="btn btn-secondary" onclick="location.href='${pageContext.request.contextPath}/Profile';">Annuler</button>
			</div>
			<a href="#" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Supprimer Mon Compte</a>
		</div>

		<% if(request.getAttribute("listeCodesErreur") != null) { %>
		<div class="alert alert-danger">
			<% List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur"); %>
			<% for(Integer codeErreur : listeCodesErreur) { %>
			<% String messageErreur = LecteurMessage.getMessage(codeErreur); %>
			<%= messageErreur %><br/>
			<% } %>
		</div>
		<% } %>
	</form>


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