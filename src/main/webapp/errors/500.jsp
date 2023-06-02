<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>CodePen - Error Pages</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/errors/style.css">

</head>
<body>
<!-- partial:index.partial.html -->
<div class="error-page">
    <div>
        <!--h1(data-h1='400') 400-->
        <!--p(data-p='BAD REQUEST') BAD REQUEST-->
        <!--h1(data-h1='401') 401-->
        <!--p(data-p='UNAUTHORIZED') UNAUTHORIZED-->
        <!--h1(data-h1='403') 403-->
        <!--p(data-p='FORBIDDEN') FORBIDDEN-->
        <h1 data-h1="500">500</h1>
        <p data-p="ERREUR SERVEUR DON'T PANIC !">ERREUR SERVEUR DON'T PANIC !</p>
        <!--h1(data-h1='500') 500-->
        <!--p(data-p='SERVER ERROR') SERVER ERROR-->
    </div>
</div>
<div id="particles-js"></div>
<a href="${pageContext.request.contextPath}/" class="back">Accueil</a>
<!-- partial -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/particles.js/2.0.0/particles.min.js'></script>
<script  src="${pageContext.request.contextPath}/errors/script.js"></script>

</body>
</html>