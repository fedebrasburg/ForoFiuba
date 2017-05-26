<html>
<head>
    <link rel="stylesheet" href="styles/general.css" type="text/css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1> ForosFiuba - Departamentos</h1>
    <h2>Departamentos</h2>
    <g:each var="departamento" in="${Departamentos}">
        <g:link action="materias"  params="${[departamentoId:departamento.id, departamentoNombre:departamento.nombre]}"> ${departamento.nombre}</g:link>
        <br/>
    </g:each>
</div>

</body>
</html>