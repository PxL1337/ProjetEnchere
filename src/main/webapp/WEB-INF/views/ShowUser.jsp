<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <title>${user.pseudo }</title>
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
		<li>Prenom : ${user.Prenom }</li>
		<li>Email : ${user.email }</li>
		<li>Telephone : ${user.telephone }</li>
		<li>Adresse : ${user.rue }<br/> ${user.codePostal }  ${user.ville }</li>
	</ul>
</body>
</html>