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

    <form action="${pageContext.request.contextPath}/inscription" method="POST">

        <label for="pseudo">Pseudo:</label>
        <input type="text" id="pseudo" name="pseudo" required><br>

        <label for="motDePasse">Mot de passe:</label>
        <input type="password" id="motDePasse" name="motDePasse" required><br>

        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" required><br>

        <label for="prenom">Prénom:</label>
        <input type="text" id="prenom" name="prenom" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>

        <label for="tel">Téléphone:</label>
        <input type="tel" id="tel" name="tel" ><br>

        <label for="rue">Rue:</label>
        <input type="text" id="rue" name="rue" required><br>

        <label for="codePostal">Code Postal:</label>
        <input type="text" id="codePostal" name="codePostal" required><br>

        <label for="ville">Ville:</label>
        <input type="text" id="ville" name="ville" required><br>



        <input type="submit" value="S'inscrire">
    </form>

</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- JS Bootstrap -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>