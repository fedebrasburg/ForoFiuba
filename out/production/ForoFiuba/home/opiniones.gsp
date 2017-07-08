<html>
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

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
    <h1 align="center" class="titulo">OpinaFiuba - Opiniones</h1>

    <div class="hilo" align="left">
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

    <div align="center" style="width:90%; padding-left: 140px">
        <h2>Opiniones</h2>
        <g:each var="opinion" in="${Opiniones}">
            <table style="width:90%" class="table">
                <tr>
                    <td width="100px">
                        <strong>Usuario</strong>
                        <br/>
                        <sec:ifNotLoggedIn>
                            ${opinion.usuario.nombre}
                        </sec:ifNotLoggedIn>
                        <sec:ifLoggedIn>
                            <g:if test="${opinion.usuario != usuarioActual}">

                                <g:link action="perfilUsuario"
                                        params="${[usuarioId: opinion.usuario.username]}">
                                    ${opinion.usuario.nombre}

                                    </span>
                                </g:link>
                            </g:if>
                            <g:else>
                                ${opinion.usuario.nombre}
                            </g:else>
                        </sec:ifLoggedIn>

                    </td>
                    <td>
                        <strong>Cursada</strong>
                        <br/>

                        <div align="right">
                            ${opinion.cuatrimestre.cuatrimestre}º cuatrimestre de ${opinion.cuatrimestre.anio}
                        </div>
                        <br/>

                        <g:if test="${opinion.fechaPublicacion != null}">
                            <strong>Fecha de Publicacion</strong>
                            <br/>

                            <div align="right">
                                ${opinion.fechaPublicacion[Calendar.DATE]}/${opinion.fechaPublicacion[Calendar.MONTH]}/${opinion.fechaPublicacion[Calendar.YEAR]}
                            </div>
                            <br/>
                        </g:if>
                        <g:if test="${opinion.profesores != ""}">
                            <strong>Profesores</strong>
                            <br/>

                            <div align="right">
                                ${opinion.profesores}
                            </div>
                            <br/>
                        </g:if>
                        <g:if test="${opinion.modalidad != ""}">
                            <strong>Modalidad</strong>
                            <br/>

                            <div align="right">
                                ${opinion.modalidad}
                            </div>
                            <br/>
                        </g:if>
                        <g:if test="${opinion.horarios != ""}">
                            <strong>Horarios</strong>
                            <br/>

                            <div align="right">
                                ${opinion.horarios}
                            </div>
                            <br/>
                        </g:if>
                        <g:if test="${opinion.opinionTeorica != ""}">
                            <strong>Opinion Teorica</strong>
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
                            <strong>Opinion Tp</strong>
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
                            <strong>Opinion Final</strong>
                            <br/>

                            <div align="right">
                                ${opinion.opinionFinal}
                            </div>
                            <br/>
                        </g:if>
                        <g:if test="${opinion.puntuacion != null}">
                            <strong>Puntuacion</strong>
                            <br/>

                            <div align="right">
                                ${opinion.puntuacion}
                            </div>
                            <br/>
                        </g:if>
                        <g:link action="meSirvioLaOpinion" params="${[opinion: opinion,usuarioActual: usuarioActual,meSirvioLaOpinion:true]}">
                        <button class="btn btn-default" >Me Sirvio<span class="glyphicon glyphicon-thumbs-up"></span></button>
                        </g:link> opinion.getCalificacionesPositivas()
                        <g:link action="meSirvioLaOpinion" params="${[opinion: opinion,usuarioActual: usuarioActual,meSirvioLaOpinion:false]}">
                        <button class="btn btn-default" >No me Sirvio<span class="glyphicon glyphicon-thumbs-down"></span></button>
                        </g:link> opinion.getCalificacionesNegativas()
                    </td>
                </tr>
            </table>
            <br/>
        </g:each>
        <div align="center" style="padding-left: 60px">
            <h2 class="listado">Los que cursaron este curso tambien cursaron...</h2>
            <sec:ifAllGranted roles="ROLE_USER">
                <g:each var="parecido" in="${materiasParecidas}">
                    <div style="font-size: large">
                        Materia: ${parecido.materiaNombre}
                        ,Curso:  <g:link action="opiniones"
                                         params="${[cursoId: parecido.cursoId, cursoNombre: parecido.cursoNombre]}">${parecido.cursoNombre}</g:link>
                    </div>
                    <br/>
                </g:each>
                <g:if test="${materiasParecidas.isEmpty()}">
                    <div style="font-size: large">
                        No hay opiniones suficientes para el sistema de recomendaciones
                    </div>
                </g:if>
            </sec:ifAllGranted>
            <sec:ifNotLoggedIn>
                <g:link action="index" controller="Login">Ingresar para ver recomendaciones</g:link>
            </sec:ifNotLoggedIn>
            <br/>
        </div>
    </div>
    <sec:ifAllGranted roles="ROLE_USER">
        <g:if test="${estadoDeMateria == forofiuba.EstadoUsuario.EstadoEnum.CURSABLE}">
            <g:hasErrors bean="${textoDefault}">
                <g:eachError bean="${textoDefault}">
                    <p style="color: red;"><g:message error="${it}"/></p>
                </g:eachError>
            </g:hasErrors>
            <div id="formulario" style="display: none">
                <g:form name="myForm" action="createOpinion" align="left" params="${[cursoId: hilo.cursoId]}">
                    <ul class="form-style-1" style="column-count: 2">
                        <fieldset>
                            <div class="form-group">
                                <label>Cuatrimestre cursado:</label>
                                <g:field type="number" class="form-control"
                                         value="${textoDefault.cuatrimestre.cuatrimestre}"
                                         required="true" min="1" max="2" name="cuatrimestre"/>
                            </div>

                            <div class="form-group">
                                <label>Año cursado:</label>
                                <g:field type="number" class="form-control" value="${textoDefault.cuatrimestre.anio}"
                                         required="true" min="1960" max="2100" name="year"/>
                            </div>

                            <div class="form-group">
                                <label>Profesores:</label>
                                <g:field type="text" class="form-control" value="${textoDefault.profesores}"
                                         placeholder="Profesores" name="profesores"/>
                            </div>

                            <div class="form-group">
                                <label>Modalidad:</label>
                                <g:field type="text" class="form-control" value="${textoDefault.modalidad}"
                                         placeholder="Modalidad" name="modalidad"/>
                            </div>

                            <div class="form-group">
                                <label>Horarios:</label>
                                <g:field type="text" class="form-control" value="${textoDefault.horarios}"
                                         placeholder="Horarios" name="horarios"/>
                            </div>

                            <div class="form-group">
                                <label>Opinion Teorica:</label>
                                <g:field type="text" class="form-control" value="${textoDefault.opinionTeorica}"
                                         name="opinionTeorica"/>
                            </div>
                            <br/>
                            <br/>

                            <div class="form-group">
                                <label>Opinion Practica:</label>
                                <g:field type="text" class="form-control" value="${textoDefault.opinionPractica}"
                                         name="opinionPractica"/>
                            </div>

                            <div class="form-group">
                                <label>Opinion del equipo docente:</label>
                                <g:field type="text" class="form-control" value="${textoDefault.opinionProfesores}"
                                         name="opinionProfesores"/>
                            </div>

                            <div class="form-group">
                                <label>Opinion de Tp:</label>
                                <g:field type="text" class="form-control" value="${textoDefault.opinionTp}"
                                         name="opinionTp"/>
                            </div>

                            <div class="form-group">
                                <label>Opinion del Parcial:</label>
                                <g:field type="text" class="form-control" value="${textoDefault.opinionParcial}"
                                         name="opinionParcial"/>
                            </div>

                            <div class="form-group">
                                <label>Opinion del Final:</label>
                                <g:field type="text" class="form-control" value="${textoDefault.opinionFinal}"
                                         name="opinionFinal"/>
                            </div>

                            <div class="form-group">
                                <label>Puntuacion:</label>
                                <g:field type="number" required="true" class="form-control"
                                         value="${textoDefault.puntuacion}"
                                         min="1" max="10" name="puntuacion"/>
                            </div>
                            <button class="btn btn-danger" type="button" onclick="handleCancelar()">Cancelar</button>
                            <g:submitButton class="btn btn-success" name="botonAgregar" value="Crear Opinion"/>
                        </fieldset>
                    </ul>
                </g:form>
            </div>

            <div align="center">
                <button onclick="handleCreateOpinion()" class="btn btn-info"
                        id="botonCrearOpinion">Crear Opinion</button>
            </div>
            <script>
                function handleCreateOpinion() {
                    var x = document.getElementById('formulario');
                    var boton = document.getElementById('botonCrearOpinion');
                    x.style.display = 'block';
                    boton.style.display = "none";
                }
                function handleCancelar() {
                    var x = document.getElementById('formulario');
                    var boton = document.getElementById('botonCrearOpinion');
                    x.style.display = 'none';
                    boton.style.display = "block";
                }
            </script>
        </g:if>
        <g:else>
            <g:if test="${estadoDeMateria == forofiuba.EstadoUsuario.EstadoEnum.NOESTAENELPLAN}">
                <h4 align="center">
                    No podes opinar por que no pertenece a tu plan
                </h4>
            </g:if>
            <g:if test="${estadoDeMateria == forofiuba.EstadoUsuario.EstadoEnum.FALTANCORRELATIVAS}">

                <h4 align="center">
                    No podes opinar por no haber opinado en las correlativas :(
                    <br/>
                    <br/>
                    Te faltan cursar:
                </h4>

                <div align="center">
                    <g:each in="${materiasFaltantes}" var="materia">
                        <g:link action="catedras"
                                params="${[materiaId: materia.id, materiaNombre: materia.nombre]}">${materia.nombre}</g:link>
                    </g:each>
                </div>
            </g:if>
            <g:else>
                <div align="center">
                    <h4 align="center">
                        Ya opinaste sobre esta materia, gracias por dejar tu opinion :)
                    </h4>
                </div>
                <br/>
                <br/>

            </g:else>
        </g:else>

    </sec:ifAllGranted>

</div>

<div style="padding-top: 60px">
    <g:render template="partials/Footer"/>
</div>
</body>
</html>