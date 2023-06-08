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

<div class="container mt-2 d-flex justify-content-center">
    <c:if test="${sessionScope.message != null}">
        <div class="alert alert-success d-flex justify-content-between align-items-center custom-alert" style="width: max-content; max-width: 100%;">
            <div>${sessionScope.message}</div>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <c:remove var="message" scope="session"/>
    </c:if>
</div>

<div class="container">
	  <div class="row align-items-center">
	    <div class="col-12 col-lg-auto mb-2 mb-lg-0 me-lg-auto">
	      <form action="${pageContext.request.contextPath}/accueil" method="POST">
	        <div class="d-flex align-items-center">
	          <p class="me-2 mb-0 custom-text">
	          
	          Recherche actuelle : 
	          <c:if test="${not empty filter}"> "${filter}" </c:if>
	          ${not empty filter && not empty categorie ? " | " : ""}${not empty categorie ? categorie : ""}</p>
	          <button type="submit" name="filterContent" class="btn btn-primary">Annuler le filtre</button>
	        </div>
	      </form>
	    </div>
	  </div>
</div>

<!-- AFFICHAGE DU RESULTAT DE LA REQUETE -->
<div class="container">
    <div class="row">
        <c:forEach items="${encheres}" var="enchere" varStatus="status">
        <div class="col-md-4">
            <div class="card mb-3" style="border-radius: 15px; overflow: hidden;">
                <div class="row g-0">
                    <div class="col-md-4 d-flex align-items-center justify-content-center">
                        <img src="${pageContext.request.contextPath}/images/articles/article.png" class="card-img img-thumbnail ms-2" alt="Image de l'article" style="height: 100px; object-fit: contain;">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title"><a href="${pageContext.request.contextPath}/detailvente?id=${enchere.no_Enchere}" class="btn btn-outline-secondary">${enchere.article.nomArticle}</a></h5>
                            <ul class="list-group">
                                <li class="list-group-item"><strong>Prix :</strong> ${enchere.montantEnchere}</li>
                                <li class="list-group-item"><strong>Fin de l'enchère :</strong> ${enchere.article.dateFinEncheres}</li>
                                <c:choose>
                                	<c:when test="${empty utilisateurConnecte }">
                                		 <li class="list-group-item"><strong>Vendeur : </strong> ${enchere.encherisseur.pseudo}</li>
                                	</c:when>
                                	<c:otherwise>
                                	 	<li class="list-group-item"><strong>Vendeur : </strong><a href="${pageContext.request.contextPath}/ProfileUser?id=${enchere.encherisseur.noUtilisateur}" class="btn btn-outline-primary">${enchere.encherisseur.pseudo}</a></li>
                                	</c:otherwise>
                                </c:choose>
                                
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- À chaque troisième élément, fermer la rangée et en commencer une nouvelle -->
        <c:if test="${status.index % 3 == 2}">
    </div>
    <div class="row">
        </c:if>
        </c:forEach>
    </div>
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