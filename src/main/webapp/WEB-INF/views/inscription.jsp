<%@ page import="fr.eni.projetenchere.bll.CodeErreur" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Database Test</title>
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

<div class="container">
    <h1>Inscription</h1>
    <% if(request.getAttribute("listeCodesErreur") != null) { %>
    <div class="alert alert-danger">
        <% List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur"); %>
        <% for(Integer codeErreur : listeCodesErreur) { %>
        <% if(codeErreur.equals(CodeErreur.EMAIL_EXISTANT)) { %>
        Email indisponible<br/>
        <% } else if(codeErreur.equals(CodeErreur.PSEUDO_EXISTANT)) { %>
        Pseudo indisponible<br/>
        <% } else if(codeErreur.equals(CodeErreur.CODE_POSTAL_INVALIDE)) { %>
        Code postal erroné<br/>
        <% } else if(codeErreur.equals(CodeErreur.MDP_INVALIDE)) { %>
        Mot de passe erroné<br/>
        <% } %>
        <% } %>
    </div>
    <% } %>

    <form action="${pageContext.request.contextPath}/inscription" method="POST" class="row g-3">
        <div class="col-md-6">
            <div class="form-floating">
                <input type="text" id="pseudo" name="pseudo" class="form-control" placeholder="Ex : Michoudu78" required>
                <label for="pseudo">Pseudo</label>
            </div>
        </div>

        <div class="col-md-6">
            <div class="form-floating">
                <input type="password" id="motDePasse" name="motDePasse" class="form-control" placeholder="Ex : MySecuredPassword" required>
                <label for="motDePasse">Mot de passe</label>
            </div>
        </div>

        <div class="col-md-6">
            <div class="form-floating">
                <input type="text" id="nom" name="nom" class="form-control" placeholder="Ex : Durand" required>
                <label for="nom">Nom</label>
            </div>
        </div>

        <div class="col-md-6">
            <div class="form-floating">
                <input type="text" id="prenom" name="prenom" class="form-control" placeholder="Ex : Bernard" required>
                <label for="prenom">Prénom</label>
            </div>
        </div>

        <div class="col-md-6">
            <div class="form-floating">
                <input type="email" id="email" name="email" class="form-control" placeholder="Ex : xyz@xyz.com" required>
                <label for="email">Email</label>
            </div>
        </div>

        <div class="col-md-6">
            <div class="form-floating">
                <input type="tel" id="tel" name="telephone" class="form-control" pattern="[0-9]{10}" placeholder="Ex : 0123456789">
                <label for="tel">Téléphone</label>
            </div>
        </div>

        <div class="col-12">
            <div class="form-floating">
                <input type="text" id="rue" name="rue" class="form-control" placeholder="Ex : rue de la paix" required>
                <label for="rue">Rue</label>
            </div>
        </div>

        <div class="col-md-6">
            <div class="form-floating">
                <input type="text" id="codePostal" name="codePostal" class="form-control" placeholder="Ex : 01000" required>
                <label for="codePostal">Code Postal</label>
            </div>
        </div>

        <div class="col-md-6">
            <div class="form-floating">
                <input type="text" id="ville" name="ville" class="form-control" placeholder="Ex : Madrid" required>
                <label for="ville">Ville</label>
            </div>
        </div>

        <div class="col-12">
            <input type="submit" value="S'inscrire" class="btn btn-primary">
        </div>
    </form>



</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- JS Bootstrap -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>