<html>
<head>
    <link rel="stylesheet" href="styles/general.css" type="text/css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>

<body>
<g:render template="partials/Nav"/>
<div class="body" align="center">
    <h2 class="titulo">Foros-Fiuba</h2>

    <h2>Cursos encontrados:</h2>
    <g:each var="parecido" in="${Parecidos}">
          ${parecido.materia.nombre} >
        <g:link action="opiniones"
                params="${[cursoId: parecido.curso.id, cursoNombre: parecido.curso.nombre]}">${parecido.curso.nombre}</g:link>
        <br/>
    </g:each>
    <g:if test="${Parecidos.isEmpty()}">
        No se encontraron resultados..
    </g:if>
    <g:link action="index"> Volver a Departamentos</g:link>
</div>
<g:render template="partials/Footer"/>
</body>
</html>