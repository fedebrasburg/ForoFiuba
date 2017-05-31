<html>
<head>

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    </style>
</head>

<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1 align="center" class="titulo">ForosFiuba - Opiniones</h1>

    <div class="hilo">
        <g:link action="index">Departamentos</g:link> >
        <g:link action="materias"
                params="${[departamentoId: hilo.departamentoId, departamentoNombre: hilo.departamentoNombre]}">${hilo.departamentoNombre}</g:link> >
        <g:link action="catedras"
                params="${[materiaId: hilo.materiaId, materiaNombre: hilo.materiaNombre]}">${hilo.materiaNombre}</g:link>
        >
        <g:link action="cursos"
                params="${[catedraId: hilo.catedraId, catedraNombre: hilo.catedraNombre]}">${hilo.catedraNombre}</g:link>
        >
        ${hilo.cursoNombre}
    </div>

    <div align="center">
        <h2>Opiniones</h2>
        <g:each var="opinion" in="${Opiniones}">
            <table style="width:90%" class="table" lass="table">
                <tr>
                    <td>Usuario</td>
                    <td>${opinion.usuario.nombre}</td>
                </tr>
                <g:if test="${opinion.profesores != ""}">
                    <tr>
                        <td>Profesores</td>
                        <td>${opinion.profesores}</td>
                    </tr>
                </g:if>
                <g:if test="${opinion.modalidad != ""}">
                    <tr>
                        <td>Modalidad</td>
                        <td>${opinion.modalidad}</td>
                    </tr>
                </g:if>
                <g:if test="${opinion.horarios != ""}">
                    <tr>
                        <td>Horarios</td>
                        <td>${opinion.horarios}</td>
                    </tr>
                </g:if>
                <g:if test="${opinion.opinionTeorica != ""}">
                    <tr>
                        <td>Opinion Teorica</td>
                        <td>${opinion.opinionTeorica}</td>
                    </tr>
                </g:if>
                <g:if test="${opinion.opinionPractica != ""}">
                    <tr>
                        <td>Opinion Practica</td>
                        <td>${opinion.opinionPractica}</td>
                    </tr>
                </g:if>
                <g:if test="${opinion.opinionProfesores != ""}">
                    <tr>
                        <td>Opinion Profesores</td>
                        <td>${opinion.opinionProfesores}</td>
                    </tr>
                </g:if>
                <g:if test="${opinion.opinionTp != ""}">
                    <tr>
                        <td>Opinion Tp</td>
                        <td>${opinion.opinionTp}</td>
                    </tr>
                </g:if>
                <g:if test="${opinion.opinionParcial != ""}">
                    <tr>
                        <td>Opinion Parcial</td>
                        <td>${opinion.opinionParcial}</td>
                    </tr>
                </g:if>
                <g:if test="${opinion.opinionFinal != ""}">
                    <tr>
                        <td>Opinion Final</td>
                        <td>${opinion.opinionFinal}</td>
                    </tr>
                </g:if>
                <g:if test="${opinion.puntuacion != null}">
                    <tr>
                        <td>Puntuacion</td>
                        <td>${opinion.puntuacion}</td>
                    </tr>
                </g:if>
            </table>
            <br/>
        </g:each>
        <h2 class="titulo">Los que cursaron este curso tambien cursaron...</h2>
        <g:each var="parecido" in="${materiasParecidas}">
            Materia: ${parecido.materiaNombre}
            Curso:  <g:link action="opiniones" params="${[cursoId:parecido.cursoId, cursoNombre:parecido.cursoNombre]}"> ${parecido.cursoNombre}</g:link>
            <br/>
        </g:each>
    </div>
    <sec:ifAllGranted roles="ROLE_USER">
        <g:form name="myForm" action="createOpinion" params="${[cursoId: hilo.cursoId, usuarioId: 15]}">
            <fieldset>
                <legend>Crear Opinion</legend>

                <div class="form-group">
                    <label>Profesores:</label>
                    <g:field type="text" class="form-control" placeholder="Profesores" name="profesores"/>
                </div>

                <div class="form-group">
                    <label>Modalidad:</label>
                    <g:field type="text" class="form-control" placeholder="Modalidad" name="modalidad"/>
                </div>

                <div class="form-group">
                    <label>Horarios:</label>
                    <g:field type="text" class="form-control" placeholder="Horarios" name="horarios"/>
                </div>

                <div class="form-group">
                    <label>Opinion Teorica:</label>
                    <g:field type="text" class="form-control" name="opinionTeorica"/>
                </div>

                <div class="form-group">
                    <label>Opinion Practica:</label>
                    <g:field type="text" class="form-control" name="opinionPractica"/>
                </div>

                <div class="form-group">
                    <label>Opinion del equipo docente:</label>
                    <g:field type="text" class="form-control" name="opinionProfesores"/>
                </div>

                <div class="form-group">
                    <label>Opinion de Tp:</label>
                    <g:field type="text" class="form-control" name="opinionTp"/>
                </div>

                <div class="form-group">
                    <label>Opinion del Parcial:</label>
                    <g:field type="text" class="form-control" name="opinionParcial"/>
                </div>

                <div class="form-group">
                    <label>Opinion del Final:</label>
                    <g:field type="text" class="form-control" name="opinionFinal"/>
                </div>

                <div class="form-group">
                    <label>Puntuacion:</label>
                    <g:field type="number" class="form-control" min="1" max="10" name="puntuacion"/>
                </div>
                <g:submitButton class="btn btn-default" name="botonAgregar" value="Crear Opinion"/>
            </fieldset>
        </g:form>
    </sec:ifAllGranted>

</div>
</body>
</html>