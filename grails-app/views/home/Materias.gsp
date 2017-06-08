<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>

<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1 class="titulo" align="center">ForosFiuba - Materias</h1>

    <div class="hilo">
        <g:link action="index">Departamentos </g:link> > ${hilo.departamentoNombre}
    </div>

    <div align="center">
        <h2>Materias</h2>
        <g:each var="materia" in="${Materias}">
            <g:link action="catedras" class="listado"
                    params="${[materiaId: materia.id, materiaNombre: materia.nombre]}">${materia.nombre}</g:link>
            <sec:ifAllGranted roles="ROLE_ADMIN">

                <g:link action="deleteMateria" class="listado"
                        params="${[materiaId: materia.id, departamentoId: hilo.departamentoId]}"><span
                        class="glyphicon glyphicon-remove"></span></g:link>
            </sec:ifAllGranted>
            <br/>
        </g:each>
    </div>
    <br/>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <g:form name="myForm" action="createMateria" params="${[departamentoId: hilo.departamentoId]}">
            <fieldset>
                <legend>Crear Materia</legend>

                <div class="form-group">
                    <label>Nombre Materia:</label>
                    <g:field type="text" placeholder="Nombre" class="form-control" required="true"
                             name="materiaNombre"/>
                </div>

                <div class="form-group">
                    <label>Descripcion:</label>
                    <g:field type="text" placeholder="Descripcion" class="form-control" name="materiaDescripcion"/>
                </div>
                <div class="form-group">

                <label>Carreras:</label>
                <br/>
                <g:each  in="${carreras}" var="carrera">
                    <g:checkBox name="carrerasNombre" value="${carrera.nombre.toString()}" /> ${carrera.nombre.toString()}           <br/>
                </g:each>
                </div>
                <g:submitButton class="btn btn-default" name="botonAgregarMateria" value="Crear Materia"/>
            </fieldset>
        </g:form>
    </sec:ifAllGranted>
</div>
</body>
</html>