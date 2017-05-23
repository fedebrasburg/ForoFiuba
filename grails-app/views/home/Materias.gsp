<html>
<head>

</head>
<body>
<h1> ForosFiuba - Materias</h1>
<h2>Materias</h2>
<g:each var="materia" in="${Materias}">
    <g:link action="catedras"  params="${[materiaId:materia.id, materiaNombre:materia.nombre]}"> ${materia.nombre}</g:link>
    </br>
</g:each>
</body>
</html>