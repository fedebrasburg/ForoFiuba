<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<g:render template="partials/Nav"/>
<div class="body">
    <h1 class="titulo" align="center">  ForosFiuba - Catedras</h1>
    <div class="hilo">
        <g:link action="index">Departamentos  </g:link> >
        <g:link action="materias"  params="${[departamentoId:hilo.departamentoId, departamentoNombre:hilo.departamentoNombre]}"> ${hilo.departamentoNombre} </g:link> >
        ${hilo.materiaNombre}
    </div>
    <div align="center">
        <h2> Catedras</h2>
        <g:each var="catedra" in="${Catedras}">
            <g:link action="cursos" class="listado"  params="${[catedraId:catedra.id, catedraNombre:catedra.nombre]}"> ${catedra.nombre}</g:link>
            <br/>
        </g:each>
    </div>
    <br/>
    <g:form name="myForm" action="createCatedra"  params="${[materiaId : hilo.materiaId ]}">
        <fieldset>
            <legend>Crear Catedra</legend>
            <div class="form-group">
                <label>Nombre Catedra: </label>
                <g:field type="text" class="form-control" required="true" placeholder="Nombre" name="catedraNombre"/>
            </div>
            <div class="form-group">
                <label>Mail: </label>
                <g:field type="text" class="form-control" placeholder="Email"  name="catedraEmail"/>
            </div>
            <g:submitButton class="btn btn-default" name="botonAgregarMateria" value="Crear Catedra"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>