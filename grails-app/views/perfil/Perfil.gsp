<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>

<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1>ForosFiuba - Usuarios</h1>
    <legend>Perfil</legend>
    <g:if test="${edit}">
        <g:form name="myForm" action="editar"  params="${[usuarioid: usuario.username]}">
            <fieldset>
                <label>Nombre:</label>
                <g:field type="text" required="true" name="nombre" value="${usuario.nombre}"/>
                <br/>
                <label>Genero:</label>
                <g:field type="text" name="genero" value="${usuario.genero}"/>
                <br/>
                <label>Email:      <td>${usuario.username}</td></label> <br/>
                <br/>
                <label>FechaDeNacimiento:</label>
                <g:datePicker name="fechaDeNacimiento" value="${new Date()}" precision="day"></g:datePicker>
                <br/>
                <label>Telefono:</label>
                <g:field type="number" name="telefono" value="${usuario.telefono}"/>
                <br/>
                <label>Carreras:</label><br/>
                <g:each  in="${carreras}" var="carrera">
                    <g:checkBox name="carrerasNombre" value="${carrera.nombre.toString()}" />     ${carrera.nombre.toString()}       <br/>
                </g:each>
                <br/>
                <g:if test="${usuario==usuarioActual}">
                    <g:submitButton class="btn btn-default" name="editar" value="Guardar"/>
                </g:if>
            </fieldset>
        </g:form>
    </g:if>
    <g:else>
        <g:form name="myForm" action="mostrarEditar" params="${[usuarioid: usuario.username]}">
            <label>Nombre:      <td>${usuario.nombre}</td></label> <br/>
            <label>Genero:      <td>${usuario.genero}</td></label> <br/>
            <label>Email:      <td>${usuario.username}</td></label> <br/>
            <label>Fecha de nacimiento:      <g:formatDate date="${usuario.fechaDeNacimiento}"  format="dd-MM-yyyy" /> </label> <br/>
            <label>Telefono:      <td>${usuario.telefono}</td></label> <br/>
            <label>Carreras: </label><br/>
            <g:each  in="${carreras}" var="carrera">
                   <td> ${carrera.nombre.toString()}    </td>   <br/>
            </g:each>
            <g:if test="${usuario==usuarioActual}">
                <g:submitButton class="btn btn-default" name="edit" value="Editar">
                <span class="glyphicons glyphicons-pencil"></span>
                </g:submitButton>
            </g:if>
        </g:form>
    </g:else>
    <g:if test="${!CursosCompartidos.isEmpty() && usuario==usuarioActual}">
        <legend> Compañeros y compañeras:</legend>
        <g:each in="${CursosCompartidos.keySet()}" var="c">
            <h5>En el curso ${c.cursoNombre} ${c.cuatrimestre}º ${c.year} cursaste con: </h5>
            <ul>
                <g:each in="${CursosCompartidos[c]}" var="usuario">
                    <li>${usuario.nombre}</li>
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
                        <strong>Usuario</strong>
                        <br/>
                        ${opinion.usuario.nombre}
                    </td>
                    <td>
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
                        <g:if test="${usuario==usuarioActual}">

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