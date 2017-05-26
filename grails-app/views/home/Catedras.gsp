<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1> ForosFiuba - Catedras</h1>
    <g:link action="index">Departamentos  </g:link> >
    <g:link action="materias"  params="${[departamentoId:hilo.departamentoId, departamentoNombre:hilo.departamentoNombre]}"> ${hilo.departamentoNombre} </g:link> >
    ${hilo.materiaNombre}
    <h2> Catedras</h2>
    <g:each var="catedra" in="${Catedras}">
        <g:link action="cursos"  params="${[catedraId:catedra.id, catedraNombre:catedra.nombre]}"> ${catedra.nombre}</g:link>
        <br/>
    </g:each>
    <br/>
    <g:form name="myForm" action="createCatedra"  params="${[materiaId : hilo.materiaId ]}">
        <fieldset>
            <legend>Crear Catedra</legend>
            <label>Nombre Catedra: </label>
            <g:field type="text" required="true" name="catedraNombre"/>
            <br/>
            <br/>
            <label>Mail: </label>
            <g:field type="text"  name="catedraEmail"/>
            <br/>
            <br/>
            <g:submitButton class="btn btn-default" name="botonAgregarMateria" value="Crear Catedra"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>