<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>

<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1>OpinaFiuba - Usuarios</h1>

    <g:form name="myForm" action="crearUsuario">
        <fieldset>
            <legend>Registrar</legend>
            <div class="form-group">
                <label>Nombre:</label>
                <g:field type="text" class="form-control" required="true" name="nombre"  value="${textoDefault.nombre}"  />
            </div>
                <div class="form-group">

                    <label>Genero:</label>

                    <g:field type="text" class="form-control" name="genero" value="${textoDefault.genero}" />
                </div>

                    <div class="form-group">
                        <label>Email:</label>
            <g:field type="email" class="form-control" name="username" required="true" value="${textoDefault.username}"/>
                    </div>
            <div class="form-group">

                <label>FechaDeNacimiento:</label>
            <g:datePicker name="fechaDeNacimiento" value="${textoDefault.fechaDeNacimiento}" precision="day" ></g:datePicker>
            </div>
            <div class="form-group">
                <label>Telefono:</label>
            <g:field type="number" name="telefono" class="form-control" value="${textoDefault.telefono}"/>
            </div>
            <div class="form-group">

                <label>Password:</label>
            <g:field type="password" name="password" class="form-control" value="${textoDefault.password}" required="true"/>
            </div>
            <div class="form-group">

                <label>Password:</label>
            <g:field type="password" name="checkpassword" class="form-control" required="true" value="${textoDefault.password}" />
            </div>

            <label>Carreras:</label>
            <br/>
            <g:each  in="${carreras}" var="carrera">
                <g:checkBox name="carrerasNombre" value="${carrera.nombre.toString()}" /> ${carrera.nombre.toString()}           <br/>
            </g:each>
            <br/>
            <g:submitButton class="btn btn-default" name="botonRegistrar" value="Registrar"/>

        </fieldset>
    </g:form>
    <g:each var="error" in="${errorList}">
        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                error="${error}"/></li>
    </g:each>

    </div>
    </body>
    </html>