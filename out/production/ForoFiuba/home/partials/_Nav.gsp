<style>
body {
    background-color: #E3E5F2;
    position: relative;
}

.body {
    padding-left: 20px;
    padding-right: 20px;

}

.navbar {
    margin-bottom: 0 !important;
    margin-top: 0;
}

.table {
    font-size: medium;
    border: groove;
}

.texto-cuerpo {
    font-size: large;
    font-weight: bold;
}

.titulo {
    font-size: xx-large;
}

.listado {
    font-size: x-large;
}

.hilo {
    font-size: medium;
}

.footer {
    position: absolute;
    bottom: 0;
    width: 100%;
    /* Set the fixed height of the footer here */
    height: 60px;
    background-color: #f5f5f5;
    clear: both;
}

.form-style-1 {
    margin: 10px auto;
    max-width: 700px;
    padding: 20px 12px 10px 20px;
    font: 13px "Lucida Sans Unicode", "Lucida Grande", sans-serif;
}

.form-style-1 li {
    padding: 0;
    display: block;
    list-style: none;
    margin: 10px 0 0 0;
}

.form-style-1 label {
    margin: 0 0 3px 0;
    padding: 0px;
    display: block;
    font-weight: bold;
}

.form-style-1 input[type=text],
.form-style-1 input[type=date],
.form-style-1 input[type=datetime],
.form-style-1 input[type=number],
.form-style-1 input[type=search],
.form-style-1 input[type=time],
.form-style-1 input[type=url],
.form-style-1 input[type=email],
textarea,
select {
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    border: 1px solid #BEBEBE;
    padding: 7px;
    margin: 0px;
    -webkit-transition: all 0.30s ease-in-out;
    -moz-transition: all 0.30s ease-in-out;
    -ms-transition: all 0.30s ease-in-out;
    -o-transition: all 0.30s ease-in-out;
    outline: none;
}

.form-style-1 input[type=text]:focus,
.form-style-1 input[type=date]:focus,
.form-style-1 input[type=datetime]:focus,
.form-style-1 input[type=number]:focus,
.form-style-1 input[type=search]:focus,
.form-style-1 input[type=time]:focus,
.form-style-1 input[type=url]:focus,
.form-style-1 input[type=email]:focus,
.form-style-1 textarea:focus,
.form-style-1 select:focus {
    -moz-box-shadow: 0 0 8px #88D5E9;
    -webkit-box-shadow: 0 0 8px #88D5E9;
    box-shadow: 0 0 8px #88D5E9;
    border: 1px solid #88D5E9;
}

.form-style-1 .field-divided {
    width: 60%;
}

.form-style-1 .field-long {
    width: 100%;
}

.form-style-1 .field-select {
    width: 100%;
}

.form-style-1 .field-textarea {
    height: 100px;
}

.departamentosList .btn {
    background: #3498db;
    background-image: -webkit-linear-gradient(top, #3498db, #2980b9);
    background-image: -moz-linear-gradient(top, #3498db, #2980b9);
    background-image: -ms-linear-gradient(top, #3498db, #2980b9);
    background-image: -o-linear-gradient(top, #3498db, #2980b9);
    background-image: linear-gradient(to bottom, #3498db, #2980b9);
    -webkit-border-radius: 8;
    -moz-border-radius: 8;
    border-radius: 8px;
    font-family: Courier New;
    color: #ffffff;
    font-size: 34px;
    padding: 30px 30px 30px 30px;
    text-decoration: none;
    margin: 15px;
}

.departamentosList .btn:hover {
    background: #3cb0fd;
    background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
    background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
    background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
    background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
    background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
    text-decoration: none;
}

</style>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <g:link action="departamentos" class="navbar-brand">OpinaFiuba</g:link>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left" action="busqueda">
                <div class="form-group">
                    <input type="text" name="nombre" class="form-control" placeholder="Buscar Curso">
                </div>
                <button type="submit" class="btn btn-default">Buscar</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <sec:ifNotLoggedIn>
                    <li>
                        <g:link action="index" controller="Registro"><span
                                class="glyphicon glyphicon-check"></span> Registrarse</g:link>
                    </li>
                </sec:ifNotLoggedIn>
                <sec:ifLoggedIn>
                    <li>
                        <g:link action="index" controller="perfil"><span
                                class="glyphicon glyphicon-user"></span> Perfil</g:link>
                    </li>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <li>
                        <g:link action="index" controller="Login"><span
                                class="glyphicon glyphicon-user"></span> Ingresar</g:link>
                    </li>
                </sec:ifNotLoggedIn>
                <sec:ifLoggedIn>
                    <li>
                        <g:link controller='logout'><span class="glyphicon glyphicon-power"></span>Logout</g:link>
                    </li>
                </sec:ifLoggedIn>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>