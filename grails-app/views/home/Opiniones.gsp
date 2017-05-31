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
                    <td width="100px">
                        <strong>Usuario</strong>
                        <br/>
                        ${opinion.usuario.nombre}
                    </td>
                <td>
                <g:if test="${opinion.profesores != ""}">
                    <strong> Profesores </strong>
                    <br/>
                    <div align="right">
                    ${opinion.profesores}
                    </div>
                    <br/>
                </g:if>
                <g:if test="${opinion.modalidad != ""}">
                    <strong> Modalidad </strong>
                    <br/>
                    <div align="right">
                        ${opinion.modalidad}
                    </div>
                    <br/>
                </g:if>
                <g:if test="${opinion.horarios != ""}">
                    <strong> Horarios</strong>
                    <br/>
                    <div align="right">
                        ${opinion.horarios}
                    </div>
                    <br/>
                </g:if>
                <g:if test="${opinion.opinionTeorica != ""}">
                    <strong>Opinion Teorica </strong>
                    <br/>
                    <div align="right">
                        ${opinion.opinionTeorica}
                    </div>
                    <br/>
                </g:if>
                <g:if test="${opinion.opinionPractica != ""}">
                    <strong>Opinion Practica</strong>
                    <br/>
                    <div align="right">
                        ${opinion.opinionPractica}
                    </div>
                    <br/>
                </g:if>
                <g:if test="${opinion.opinionProfesores != ""}">
                    <strong>Opinion Profesores</strong>
                    <br/>
                    <div align="right">
                        ${opinion.opinionProfesores}
                    </div>
                    <br/>
                </g:if>
                <g:if test="${opinion.opinionTp != ""}">
                    <strong> Opinion Tp</strong>
                    <br/>
                    <div align="right">
                        ${opinion.opinionTp}
                    </div>
                    <br/>
                </g:if>
                <g:if test="${opinion.opinionParcial != ""}">
                    <strong>Opinion Parcial</strong>
                    <br/>
                    <div align="right">
                        ${opinion.opinionParcial}
                    </div>
                    <br/>
                </g:if>
                <g:if test="${opinion.opinionFinal != ""}">
                    <strong> Opinion Final</strong>
                    <br/>
                    <div align="right">
                        ${opinion.opinionFinal}
                    </div>
                    <br/>
                </g:if>
                <g:if test="${opinion.puntuacion != null}">
                    <strong> Puntuacion</strong>
                    <br/>
                    <div align="right">
                        ${opinion.puntuacion}
                    </div>
                    <br/>
                </g:if>
                </td>
                </tr>
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
        <g:form name="myForm" action="createOpinion" params="${[cursoId: hilo.cursoId, usuarioId:11]}">
            <fieldset>
                <legend>Crear Opinion</legend>

                <div class="form-group">
                    <label>Profesores:</label>
                    <g:field type="text" class="form-control" required="true" placeholder="Profesores" name="profesores"/>
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