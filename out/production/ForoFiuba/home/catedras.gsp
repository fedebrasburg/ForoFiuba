<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>

<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1 class="titulo" align="center">ForosFiuba - Catedras</h1>

    <div class="hilo">
        <g:link action="index">Departamentos</g:link> >
        <g:link action="materias"
                params="${[departamentoId: hilo.departamentoId, departamentoNombre: hilo.departamentoNombre]}">${hilo.departamentoNombre}</g:link> >
        ${hilo.materiaNombre}
    </div>
    <div align="center">
        <h3>Correlativas</h3>
        <g:each  in="${correlativas}" var="materia">
            <td> ${materia.nombre.toString()}    <br/>
        </g:each>
        <g:if test="${correlativas.isEmpty()}">
            No tiene
        </g:if>
    </div>
    <div align="center">
        <h3>Carreras</h3>
        <g:each  in="${carreras}" var="carrera">
            <td> ${carrera.nombre.toString()}    </td>   <br/>
        </g:each>
    </div>
    <div align="center">
        <h2>Catedras</h2>
        <g:each var="catedra" in="${Catedras}">
            <g:link action="cursos" class="listado"
                    params="${[catedraId: catedra.id, catedraNombre: catedra.nombre]}">${catedra.nombre}</g:link>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <g:link action="deleteCatedra" class="listado"
                        params="${[catedraId: catedra.id, materiaId: hilo.materiaId]}"><span
                        class="glyphicon glyphicon-remove"></span></g:link>
            </sec:ifAllGranted>

            <br/>
        </g:each>
    </div>
    <br/>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <g:form name="myForm" action="createCatedra" params="${[materiaId: hilo.materiaId]}">
            <fieldset>
                <legend>Crear Catedra</legend>

                <div class="form-group">
                    <label>Nombre Catedra:</label>
                    <g:field type="text" class="form-control" required="true" placeholder="Nombre"
                             name="catedraNombre"/>
                </div>

                <div class="form-group">
                    <label>Mail:</label>
                    <g:field type="text" class="form-control" placeholder="Email" name="catedraEmail"/>
                </div>
                <g:submitButton class="btn btn-default" name="botonAgregarMateria" value="Crear Catedra"/>
            </fieldset>
        </g:form>
    </sec:ifAllGranted>
</div>
</body>
</html>