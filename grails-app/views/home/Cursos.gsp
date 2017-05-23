<html>
<head>
    <h1> ForosFiuba - Cursos</h1>
</head>
<body>
<g:link action="index">Materias  </g:link> >
<g:link action="catedras"  params="${[materiaId:hilo.materiaId, materiaNombre:hilo.materiaNombre]}">${hilo.materiaNombre} </g:link>
    > ${hilo.catedraNombre}
<h2> Cursos</h2>
<g:each var="curso" in="${Cursos}">
    <g:link action="opiniones"  params="${[cursoId:curso.id, cursoNombre:curso.nombre]}"> ${curso.nombre}</g:link>
</g:each>
</body>
</html>