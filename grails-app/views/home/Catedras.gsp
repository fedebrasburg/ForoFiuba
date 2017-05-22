<html>
<head>
    <h1> ForosFiuba</h1>
</head>
<body>
<g:each var="catedra" in="${Catedras}">
    <g:link action="none"> ${catedra.nombre}</g:link>
</g:each>
</body>
</html>