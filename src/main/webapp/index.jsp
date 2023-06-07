<%@ page import="fr.eni.projetenchere.messages.LecteurMessage" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <title>PLANB.org</title>
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

<div class="container mt-2">
    <% if(request.getAttribute("listeCodesErreur") != null) { %>
    <div class="alert alert-danger">
        <% List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur"); %>
        <% for(Integer codeErreur : listeCodesErreur) { %>
        <% String messageErreur = LecteurMessage.getMessage(codeErreur); %>
        <%= messageErreur %><br/>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        <% } %>
    </div>
    <% } %>
</div>

<!-- Contenu de la page -->
<div class="container">
  <h1>Bienvenue sur ProjetEnchere!</h1>
</div>

<div class="col-md-6">
	<c:forEach items="${encheres }" var="enchere">
		<h2><a href="#">"${enchere.article.nomArticle }"</a></h2>
		<p>prix : "${enchere.montantEnchere }"<br/>Fin de l'enchere : "${enchere.article.dateFinEncheres }" <br/>Vendeur : 
	  <a href="${pageContext.request.contextPath}/ProfileUser?id=${enchere.encherisseur.noUtilisateur }">"${enchere.encherisseur.pseudo }"</a></p>

	</c:forEach>
</div>
	


<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

<!-- JS Bootstrap -->
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
