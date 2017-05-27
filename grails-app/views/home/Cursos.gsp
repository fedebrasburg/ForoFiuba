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
    <br/>
    <br/>
    <g:form name="myForm" action="createCurso"  params="${[catedraId : hilo.catedraId ]}">
        <fieldset>
            <legend>Crear Curso</legend>
            <label>Nombre Curso: </label>
            <g:field type="text" required="true" name="cursoNombre"/>
            <br/>
            <br/>
            <label>Mail: </label>
            <g:field type="text"  name="cursoEmail"/>
            <br/>
            <br/>
            <g:submitButton class="btn btn-default" name="botonAgregarCurso" value="Crear Curso"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>