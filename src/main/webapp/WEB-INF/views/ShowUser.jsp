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

	<h1>Profil de ${user.pseudo }</h1>
	<ul>
		<li>Nom : ${user.nom }</li>
		<li>Prenom : ${user.prenom }</li>
		<li>Email : ${user.email }</li>
		<li>Telephone : ${user.telephone }</li>
		<li>Adresse : ${user.rue }<br/> ${user.codePostal }  ${user.ville }</li>
	</ul>
	<jsp:include page="/WEB-INF/views/footer.jsp" />

	<!-- JS Bootstrap -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>