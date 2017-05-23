<html>
<head>
    <h1> ForosFiuba - Catedras</h1>
</head>
<body>
<g:link action="index">Materias > </g:link> ${hilo.materiaNombre}
<h2> Catedras</h2>
<g:each var="catedra" in="${Catedras}">
    <g:link action="cursos"  params="${[catedraId:catedra.id, catedraNombre:catedra.nombre]}"> ${catedra.nombre}</g:link>
</g:each>
</body>
</html>