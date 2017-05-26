<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1> ForosFiuba - Materias</h1>
    <g:link action="index">Departamentos > </g:link> ${hilo.departamentoNombre}
    <h2>Materias</h2>
    <g:each var="materia" in="${Materias}">
        <g:link action="catedras"  params="${[materiaId:materia.id, materiaNombre:materia.nombre]}"> ${materia.nombre}</g:link>
        <br/>
    </g:each>
</div>
</body>
</html>