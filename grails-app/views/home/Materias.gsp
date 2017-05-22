<html>
<head>
    <h1> ForosFiuba</h1>
</head>
<body>
<g:each var="materia" in="${Materias}">
    <g:link action="catedras" materia="fede" params="${[materia:materia.id]}"> ${materia.nombre}</g:link>
</g:each>
</body>
</html>