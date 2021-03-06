<%@ page import="forofiuba.EstadoAlumnoCurso" %>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>

<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1>OpinaFiuba - Alumno</h1>
    <legend>Perfil</legend>
    <g:if test="${edit}">
        <g:form name="myForm" action="editar"  params="${[alumnoid: alumno.username]}">
            <fieldset>
                <label>Nombre:</label>
                <g:field type="text" required="true" name="nombre" value="${alumno.nombre}"/>
                <br/>
                <label>Karma:      <td>${alumno.karmaCalculado}</td></label> <br/>
                    <label>Genero:</label>
                    <g:field type="text" name="genero" value="${alumno.genero}"/>
                    <br/>
                <label>Email:      <td>${alumno.username}</td></label> <br/>

                <label>FechaDeNacimiento:</label>
                <g:datePicker name="fechaDeNacimiento" value="${alumno.fechaDeNacimiento}" precision="day"></g:datePicker>
                <br/>

                <label>Telefono:</label>
                <g:field type="number" name="telefono" value="${alumno.telefono}"/>
                <br/>
                <label>Carreras:</label><br/>
                <g:each  in="${carreras}" var="carrera">
                    <g:checkBox name="carrerasNombre" value="${carrera.nombre.toString()}" checked="${alumno.carreras.any{ carreraAlumno -> return (carreraAlumno == carrera)}}"/>     ${carrera.nombre.toString()}       <br/>
                </g:each>
                <br/>
                <g:if test="${alumno==alumnoActual}">
                    <g:submitButton class="btn btn-default" name="editar" value="Guardar"/>
                </g:if>
                <br/>
            </fieldset>
        </g:form>
    </g:if>
    <g:else>
        <g:form name="myForm" action="mostrarEditar" params="${[alumnoid: alumno.username]}">
            <label>Nombre:      <td>${alumno.nombre}</td></label> <br/>
            <label>Karma:      <td>${alumno.karmaCalculado}</td></label> <br/>
            <g:if test="${alumno.genero!=null }">
                <label>Genero:      <td>${alumno.genero}</td></label> <br/>
            </g:if>
            <label>Email:      <td>${alumno.username}</td></label> <br/>
            <label>Fecha de nacimiento:      <g:formatDate date="${alumno.fechaDeNacimiento}" format="dd-MM-yyyy" /> </label> <br/>
            <g:if test="${alumno.telefono!=null }">

                <label>Telefono:      <td>${alumno.telefono}</td></label> <br/>
            </g:if>
            <label>Carreras: </label><br/>
            <g:each  in="${carreras}" var="carrera">
                   <td> ${carrera.nombre.toString()}    </td>   <br/>
            </g:each>
            <g:if test="${carreras.empty() }">
                <td>No esta anotado en ninguna carrera</td>
            </g:if>
            <br/>
            <g:if test="${alumno==alumnoActual}">
                <g:submitButton class="btn btn-default" name="edit" value="editar">
                <span class="glyphicons glyphicons-pencil"></span>
                </g:submitButton>
            </g:if>
        </g:form>
    </g:else>
    <g:if test="${!CursosCompartidos.isEmpty() && alumno==alumnoActual}">
        <legend> Compañeros y compañeras:</legend>
        <g:each in="${CursosCompartidos.keySet()}" var="c">
            <h5>En el curso ${c.cursoNombre} ${c.cuatrimestre.cuatrimestre}º ${c.cuatrimestre.anio} cursaste con: </h5>
            <ul>
                <g:each in="${CursosCompartidos[c]}" var="alumno">
                    <g:link controller="perfil"
                            action="index"
                            params="${[alumnoid: alumno.username]}">
                        ${alumno.nombre}

                    </g:link>
                </g:each>
            </ul>
        </g:each>
    </g:if>
    <legend>Opiniones</legend>
    <div align="center">
        <g:each var="opinion" in="${opiniones}">
            <table style="width:90%" class="table">
                <tr>
                    <td width="100px">
                        <strong> Materia </strong>
                        <br/>
                        <g:link action="catedras"  controller="home" params="${[materiaId: opinion.curso.catedra.materia.id, materiaNombre: opinion.curso.catedra.materia.nombre]}">${opinion.curso.catedra.materia.nombre}
                        </g:link>
                        <br/>
                        <strong> Catedra </strong>
                        <g:link action="cursos" controller="home" params="${[ catedraId: opinion.curso.catedra.id]}">
                            ${opinion.curso.catedra.nombre}</g:link>
                        <strong> Curso </strong>
                        <g:link action="opiniones"  controller="home" params="${[cursoId: opinion.curso.id, cursoNombre: opinion.curso.nombre]}">${opinion.curso.nombre}
                        </g:link>
                        <br/>
                    </td>
                    <td>

                            <g:if test="${opinion.alumno!=alumnoActual}">
                                <strong> Estado  </strong><br/>
                                <div align="right">
                                    ${opinion.curso.catedra.materia.estadoAlumno(alumnoActual).toString()}
                                </div>
                                <g:if test="${opinion.curso.catedra.materia.estadoAlumno(alumnoActual)== forofiuba.EstadoAlumnoCurso.EstadoEnum.CURSADA  }">
                                    <strong> Curso conmigo  </strong><br/>
                                    <div align="right">

                                    <g:if test="${alumnoActual.cursoCon(CursosCompartidos,opinion.cuatrimestre,opinion.curso.nombre)  }">
                                        Si
                                    </g:if>
                                    <g:else>
                                        No
                                    </g:else>
                                    </div>
                                    <br/>

                                </g:if>
                            </g:if>              <strong> Cursada  </strong>
                        <br/>
                        <div align="right">
                            ${opinion.cuatrimestre.cuatrimestre}º cuatrimestre de ${opinion.cuatrimestre.anio}
                        </div>
                        <br/>
                        <g:if test="${opinion.fechaPublicacion != null}">
                            <strong> Fecha de Publicacion </strong>
                            <br/>
                            <div align="right">
                                ${opinion.fechaPublicacion[Calendar.DATE]}/${opinion.fechaPublicacion[Calendar.MONTH]}/${opinion.fechaPublicacion[Calendar.YEAR]}
                            </div>
                            <br/>
                        </g:if>
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
                    <td width="100px" align="center"  valign="center" >
                        <g:if test="${alumno==alumnoActual}">

                            <g:link action="deleteOpinion" class="listado"
                                    params="${[opinionId: opinion.id]}" ><span
                                    class="glyphicon glyphicon-remove"></span>
                            </g:link>

                        </g:if>
                    </td>
                </tr>
            </table>
            <br/>

        </g:each>
</div>
</body>
</html>