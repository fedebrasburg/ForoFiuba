<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>

<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1>ForosFiuba - Usuarios</h1>

    <g:form name="myForm" action="crearUsuario">
        <fieldset>
            <legend>Registrar</legend>
            <label>Nombre:</label>
            <g:field type="text" required="true" name="nombre"  value="${textoDefault.nombre}"  />
            <br/>
            <br/>
            <label>Genero:</label>
            <g:field type="text" name="genero" value="${textoDefault.genero}" />
            <br/>
            <br/>
            <label>Email:</label>
            <g:field type="email" name="username" required="true" value="${textoDefault.username}"/>
            <br/>
            <br/>
            <label>FechaDeNacimiento:</label>
            <g:datePicker name="fechaDeNacimiento" value="${textoDefault.fechaDeNacimiento}" precision="day" ></g:datePicker>
            <br/>
            <br/>
            <label>Telefono:</label>
            <g:field type="number" name="telefono" value="${textoDefault.telefono}"/>
            <br/>
            <br/>
            <label>Password:</label>
            <g:field type="password" name="password" value="${textoDefault.password}" required="true"/>
            <br/>
            <br/>
            <label>Password:</label>
            <g:field type="password" name="checkpassword" required="true" value="${textoDefault.password}" />
            <br/>
            <br/>
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