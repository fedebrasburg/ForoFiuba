<html>
<head>
    <style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    </style>
</head>
<body>
<h1> ForosFiuba - Opiniones</h1>
<g:link action="index">Materias  </g:link> >
<g:link action="catedras"  params="${[materiaId:hilo.materiaId, materiaNombre:hilo.materiaNombre]}">${hilo.materiaNombre} </g:link>
>
<g:link action="cursos"  params="${[catedraId:hilo.catedraId, catedraNombre:hilo.catedraNombre]}"> ${hilo.catedraNombre}</g:link>
>
${hilo.cursoNombre}
<h2> Opiniones</h2>
<g:each var="opinion" in="${Opiniones}">
    <table style="width:100%">
        <g:if test="${opinion.profesores != null}">
            <tr>
                <td> Profesores</td>
                <td> ${opinion.profesores}</td>
            </tr>
        </g:if>
        <g:if test="${opinion.modalidad != null}">
            <tr>
                <td> Modalidad</td>
                <td> ${opinion.modalidad}</td>
            </tr>
        </g:if>
        <g:if test="${opinion.horarios != null}">
            <tr>
                <td> Horarios</td>
                <td> ${opinion.horarios}</td>
            </tr>
        </g:if>
        <g:if test="${opinion.opinionTeorica != null}">
            <tr>
                <td> Opinion Teorica</td>
                <td> ${opinion.opinionTeorica}</td>
            </tr>
        </g:if>
        <g:if test="${opinion.opinionPractica != null}">
            <tr>
                <td> Opinion Practica</td>
                <td> ${opinion.opinionPractica}</td>
            </tr>
        </g:if>
        <g:if test="${opinion.opinionProfesores != null}">
        <tr>
            <td> Opinion Profesores</td>
            <td> ${opinion.opinionProfesores}</td>
        </tr>
        </g:if>
        <g:if test="${opinion.opinionTp != null}">
            <tr>
                <td> Opinion Tp</td>
                <td> ${opinion.opinionTp}</td>
            </tr>
        </g:if>
        <g:if test="${opinion.opinionParcial != null}">
            <tr>
                <td> Opinion Parcial</td>
                <td> ${opinion.opinionParcial}</td>
            </tr>
        </g:if>
        <g:if test="${opinion.opinionFinal != null}">
            <tr>
                <td> Opinion Final</td>
                <td> ${opinion.opinionFinal}</td>
            </tr>
        </g:if>
        <g:if test="${opinion.puntuacion != null}">
            <tr>
                <td> Puntuacion</td>
                <td> ${opinion.puntuacion}</td>
            </tr>
        </g:if>
    </table>
    <br/>
</g:each>

<g:form name="myForm" action="createOpinion" style="width:100%" params="${[cursoId:hilo.cursoId,usuarioId:1]}">
    <fieldset>
        <legend>Crear Opinion</legend>
        <label>Profesores: </label>
        <g:field type="text"  name="profesores"/>
        <br/>
        <br/>
        <label>Modalidad: </label>
        <g:field type="text"  name="modalidad"/>
        <br/>
        <br/>
        <label>Horarios: </label>
        <g:field type="text" name="horarios"/>
        <br/>
        <br/>
        <label>Opinion Teorica: </label>
        <g:field type="text"  name="opinionTeorica"/>
        <br/>
        <br/>
        <label>Opinion Practica: </label>
        <g:field type="text"  name="opinionPractica"/>
        <br/>
        <br/>
        <label>Opinion del equipo docente: </label>
        <g:field type="text"  name="opinionProfesores"/>
        <br/>
        <br/>
        <label>Opinion de Tp: </label>
        <g:field type="text"  name="opinionTp"/>
        <br/>
        <br/>
        <label>Opinion del Parcial: </label>
        <g:field type="text"  name="opinionParcial"/>
        <br/>
        <br/>
        <label>Opinion del Final: </label>
        <g:field type="text"  name="opinionFinal"/>
        <br/>
        <br/>
        <label>Puntuacion: </label>
        <g:field type="number"  min="1" max="10"  name="puntuacion"/>
        <br/>
        <br/>
        <g:submitButton name="botonAgregar" value="Crear Opinion"/>
    </fieldset>
</g:form>
</body>
</html>