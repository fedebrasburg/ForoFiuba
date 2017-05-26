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
    <br/>
    <g:form name="myForm" action="createMateria"  params="${[departamentoId : hilo.departamentoId ]}">
        <fieldset>
            <legend>Crear Materia</legend>
            <label>Nombre Materia: </label>
            <g:field type="text" required="true" name="materiaNombre"/>
            <br/>
            <br/>
            <label>Descripcion: </label>
            <g:field type="text"  name="materiaDescripcion"/>
            <br/>
            <br/>
            <g:submitButton class="btn btn-default" name="botonAgregarMateria" value="Crear Materia"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>