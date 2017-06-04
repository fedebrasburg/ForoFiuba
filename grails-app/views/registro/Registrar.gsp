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
            <g:field type="text" required="true" name="nombre"/>
            <br/>
            <br/>
            <label>Genero:</label>
            <g:field type="text" name="genero"/>
            <br/>
            <br/>
            <label>Email:</label>
            <g:field type="email" name="username" required="true"/>
            <br/>
            <br/>
            <label>FechaDeNacimiento:</label>
            <g:datePicker name="fechaDeNacimiento" value="${new Date()}" precision="day"></g:datePicker>
            <br/>
            <br/>
            <label>Telefono:</label>
            <g:field type="number" name="telefono"/>
            <br/>
            <br/>
            <label>Password:</label>
            <g:field type="password" name="password" required="true"/>
            <br/>
            <br/>
            <label>Password:</label>
            <g:field type="password" name="checkpassword" required="true"/>
            <br/>
            <br/>
            <g:submitButton class="btn btn-default" name="botonRegistrar" value="Registrar"/>
        </fieldset>
    </g:form>
    <g:each var="empanada" in="${errorList}">
        <li> "${empanada}"</li>
    </g:each>

    </div>
    </body>
    </html>