<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1 class="titulo" align="center"> ForosFiuba - Materias</h1>
    <div class="hilo">
        <g:link action="index">Departamentos > </g:link> ${hilo.departamentoNombre}
    </div>
    <div align="center">
        <h2>Materias</h2>
        <g:each var="materia" in="${Materias}">
            <g:link action="catedras" class="listado" params="${[materiaId:materia.id, materiaNombre:materia.nombre]}"> ${materia.nombre}</g:link>
            <br/>
        </g:each>
    </div>
    <br/>
    <g:form  name="myForm" action="createMateria"  params="${[departamentoId : hilo.departamentoId ]}">
        <fieldset>
            <legend>Crear Materia</legend>
            <div class="form-group">
                <label>Nombre Materia: </label>
                <g:field type="text" placeholder="Nombre" class="form-control" required="true" name="materiaNombre"/>
            </div>
            <div class="form-group">
                <label>Descripcion: </label>
                <g:field type="text" placeholder="Descripcion" class="form-control" name="materiaDescripcion"/>
            </div>
            <g:submitButton class="btn btn-default" name="botonAgregarMateria" value="Crear Materia"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>