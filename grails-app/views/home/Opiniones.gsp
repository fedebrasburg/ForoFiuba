<html>
<head>
    <h1> ForosFiuba - Opiniones</h1>
</head>
<body>
<g:link action="index">Materias  </g:link> >
<g:link action="catedras"  params="${[materiaId:hilo.materiaId, materiaNombre:hilo.materiaNombre]}">${hilo.materiaNombre} </g:link>
>
<g:link action="cursos"  params="${[catedraId:hilo.catedraId, catedraNombre:hilo.catedraNombre]}"> ${hilo.catedraNombre}</g:link>
>
${hilo.cursoNombre}
<h2> Opiniones</h2>
<g:each var="opinion" in="${Opiniones}">
    ${opinion.opinion}
</g:each>
</body>
</html>