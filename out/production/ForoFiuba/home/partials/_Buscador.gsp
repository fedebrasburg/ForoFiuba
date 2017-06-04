<h2> Buscar cursos:</h2>
<g:form name="myForm" action="busqueda">
    <fieldset>
        <legend>Buscar Curso</legend>

        <div class="form-group">
            <label>Curso:</label>
            <g:field type="text" placeholder="Nombre del curso" class="form-control" name="nombre"/>
        </div>
        <g:submitButton class="btn btn-default" name="botonAgregarCurso" value="Buscar"/>
    </fieldset>

</g:form>