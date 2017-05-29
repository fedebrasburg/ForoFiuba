<html>
<head>
    <link rel="stylesheet" href="styles/general.css" type="text/css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<g:render template="partials/Nav"/>
<div class="body" align="center">
    <h2 class="titulo">Foros-Fiuba</h2>
    <div class="texto-cuerpo">
        ¡Bienvenido a Foros-Fiuba! Este sitio lo hacemos estudiantes de distintas carreras de la Facultad de Ingeniería de la Universidad de Buenos Aires. <br/>
    Es principalmente un foro de discusión, sobre temas inherentes a las carreras, materias, y temas de interés para gente de FIUBA.
    </div>
    <h2>Departamentos</h2>
    <g:each  var="departamento" in="${Departamentos}">
        <g:link action="materias" class="listado"  params="${[departamentoId:departamento.id, departamentoNombre:departamento.nombre]}"> ${departamento.nombre}</g:link>
        <br/>
    </g:each>
</div>

</body>
</html>