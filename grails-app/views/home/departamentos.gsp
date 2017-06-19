<html>
<head>
    <link rel="stylesheet" href="styles/general.css" type="text/css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <style>
    .bg-1{
        background-color: blue;
        color: #ffffff;
    }
    .bg-2 {
        background-color: #474e5d; /* Dark Blue */
        color: #ffffff;
    }
    </style>
</head>

<body>
<g:render template="partials/Nav"/>
<div class="container-fluid bg-1 text-center">
    <div style="font-size: 140px; padding-top: 70px;padding-bottom: 70px;">
        Foros Fiuba
    </div>
</div>
<div class="container-fluid bg-2 text-center" style="font-size: 175%">
    <div class="texto-cuerpo" style="padding-top: 70px; padding-bottom: 70px;">
        ¡Bienvenido a Foros-Fiuba! Este sitio lo hacemos estudiantes de distintas carreras de la Facultad de Ingeniería de la Universidad de Buenos Aires. <br/>
        Es principalmente un foro de discusión, sobre temas inherentes a las carreras, materias, y temas de interés para gente de FIUBA.
    </div>
</div>

<div class="body" align="center">
    <h2>Departamentos</h2>
    <g:each var="departamento" in="${Departamentos}">
        <g:link action="materias" class="listado"
                params="${[departamentoId: departamento.id, departamentoNombre: departamento.nombre]}">${departamento.nombre}</g:link>
        <sec:ifAllGranted roles="ROLE_ADMIN">
            <g:link action="deleteDepartamento" class="listado"
                    params="${[departamentoId: departamento.id]}"><span
                    class="glyphicon glyphicon-remove"></span></g:link>
        </sec:ifAllGranted>
        <br/>
    </g:each>
    <br/>
    <h3> Carreras </h3>
    <div style="column-count: 3">
    <g:each var="carrera" in="${Carreras.keySet()}">
        <h4>${carrera.nombre}</h4>
        <g:each in="${carrera.materias}" var="materia">
            <g:link action="catedras"
                    params="${[materiaId: materia.id, materiaNombre: materia.nombre]}">${materia.nombre}</g:link>
            <br/>
        </g:each>
    </g:each>
    </div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <g:form name="myForm" action="createDepartamento">
            <fieldset>
                <legend>Crear Departamento</legend>

                <div class="form-group">
                    <label>Nombre Departamento:</label>
                    <g:field type="text" class="form-control" required="true" placeholder="Nombre del departamento"
                             name="departamentoNombre"/>
                </div>

                <div class="form-group">
                    <label>Mail:</label>
                    <g:field type="text" class="form-control" placeholder="Email" name="departamentoEmail"/>
                </div>
                <div class="form-group">
                    <label>Telefono:</label>
                    <g:field type="text" class="form-control" placeholder="Telefono" name="departamentoTelefono"/>
                </div>
                <g:submitButton class="btn btn-default" name="botonAgregarDepartamento" value="Crear Departamento"/>
            </fieldset>
        </g:form>
    </sec:ifAllGranted>
</div>

</body>
</html>