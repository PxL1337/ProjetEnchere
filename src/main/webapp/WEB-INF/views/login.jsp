<%--
  Created by IntelliJ IDEA.
  User: valpx
  Date: 01/06/2023
  Time: 09:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Page d'accueil</title>
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

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">Se connecter</div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="form-group">
                            <label for="pseudoOuEmail">Pseudo ou Email</label>
                            <input type="text" class="form-control" id="pseudoOuEmail" name="pseudoOuEmail" required>
                        </div>
                        <div class="form-group">
                            <label for="motDePasse">Mot de passe</label>
                            <input type="password" class="form-control" id="motDePasse" name="motDePasse" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Se connecter</button>
                    </form>
                    <c:if test="${not empty requestScope.loginError}">
                        <div class="alert alert-danger mt-3">${requestScope.loginError}</div>
                    </c:if>
                    <c:if test="${not empty requestScope.loginSqlError}">
                        <div class="alert alert-danger mt-3">${requestScope.loginSqlError}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>




<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

<!-- JS Bootstrap -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>