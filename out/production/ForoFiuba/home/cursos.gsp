<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>

<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1 class="titulo" align="center">OpinaFiuba - Cursos</h1>

    <div class="hilo">
        <g:link action="index">Departamentos</g:link> >
        <g:link action="materias"
                params="${[departamentoId: hilo.departamentoId, departamentoNombre: hilo.departamentoNombre]}">${hilo.departamentoNombre}</g:link> >
        <g:link action="catedras"
                params="${[materiaId: hilo.materiaId, materiaNombre: hilo.materiaNombre]}">${hilo.materiaNombre}</g:link>
        > ${hilo.catedraNombre}
    </div>

    <div align="center">
        <h2>Cursos</h2>
        <g:each var="curso" in="${Cursos}">
            <g:link action="opiniones" class="listado"
                    params="${[cursoId: curso.id, cursoNombre: curso.nombre]}">${curso.nombre}</g:link>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <g:link action="deleteCurso" class="listado"
                        params="${[cursoId: curso.id, catedraId: hilo.catedraId]}"><span
                        class="glyphicon glyphicon-remove"></span></g:link>
            </sec:ifAllGranted>
            <br/>
        </g:each>
    </div>
    <br/>
    <br/>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <g:form name="myForm" action="createCurso" params="${[catedraId: hilo.catedraId]}">
            <fieldset>
                <legend>Crear Curso</legend>

                <div class="form-group">
                    <label>Nombre Curso:</label>
                    <g:field type="text" placeholder="Nombre" class="form-control" required="true" name="cursoNombre"/>
                </div>

                <div class="form-group">
                    <label>Mail:</label>
                    <g:field type="text" placeholder="Email" class="form-control" name="cursoEmail"/>
                </div>
                <g:submitButton class="btn btn-default" name="botonAgregarCurso" value="Crear Curso"/>
            </fieldset>

        </g:form>
    </sec:ifAllGranted>
</div>
<g:render template="partials/Footer"/>
</body>
</html>