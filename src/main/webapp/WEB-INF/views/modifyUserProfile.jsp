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

<h1 id="pageName"> Mon Profil </h1>

<!-- À mettre en page pour afficher les éléments dans un ordre choisi. -->
<div class="content">

	<form action="${pageContext.request.contextPath}/modifyUserProfile" method="POST">

        <label for="pseudo">Pseudo:</label>
        <input type="text" id="pseudo" name="pseudo"><br>

        <label for="motDePasse">Mot de passe:</label>
        <input type="password" id="motDePasse" name="motDePasse"><br>

        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom"><br>

        <label for="prenom">Prénom:</label>
        <input type="text" id="prenom" name="prenom"><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email"><br>

        <label for="tel">Téléphone:</label>
        <input type="text" id="tel" name="te" ><br>

        <label for="rue">Rue:</label>
        <input type="text" id="rue" name="rue"><br>

        <label for="codePostal">Code Postal:</label>
        <input type="text" id="codePostal" name="codePostal"><br>

        <label for="ville">Ville:</label>
        <input type="text" id="ville" name="ville"><br>
        
        <label for="currentMdp">Mot de passe actuel:</label>
        <input type="text" id="currentMdp" name="currentMdp"><br>
        
        <!-- Section contenant les champs newMDP et confirmationMDP -->
        <section class="newMDPSection">
			<table>
				<tr>
					<td>
						<label for="newMdp">Nouveau Mot de passe:</label>
						<input type="text" id="newMdp" name="newMdp">
					</td>
					<td>
						<label for="confirmationMdp">Confirmation:</label>
        				<input type="text" id="confirmationMdp" name="confirmationMdp">
					</td>
				</tr>
			</table>		
		</section>
		
		<!-- Credit display here (je ne sais pas si c'est comme ça qu'il faut faire) -->
		<p> Crédit : ${user.credit}</p>

		<!-- Section contenant les boutons d'actions agissants sur le formulaire -->
		<section class="buttons">
		<table>
			<tr>
				<td>
				<!-- Point to a page ? -->
					<a href=""> 
						<input type="submit" value="Enregistrer">
					</a>
				</td>
				<td>
				<!-- Point to a page ? -->
					<a href=""> 
						<input type="submit" value="Supprimer mon compte">
					</a>
				</td>
			</tr>
		</table>		
		</section>		
    </form>
</div>


<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

<!-- JS Bootstrap -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>