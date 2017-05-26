<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1> ForosFiuba - Cursos</h1>
    <g:link action="index">Departamentos  </g:link> >
    <g:link action="materias"  params="${[departamentoId:hilo.departamentoId, departamentoNombre:hilo.departamentoNombre]}"> ${hilo.departamentoNombre} </g:link> >
    <g:link action="catedras"  params="${[materiaId:hilo.materiaId, materiaNombre:hilo.materiaNombre]}">${hilo.materiaNombre} </g:link>
        > ${hilo.catedraNombre}
    <h2> Cursos</h2>
    <g:each var="curso" in="${Cursos}">
        <g:link action="opiniones"  params="${[cursoId:curso.id, cursoNombre:curso.nombre]}"> ${curso.nombre}</g:link>
    </g:each>
</div>
</body>
</html>