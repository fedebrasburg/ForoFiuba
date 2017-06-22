<style>
body {
    background-color: #E3E5F2;
}

.body {
    margin-left: 20px;
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

form {
    border: double;
    margin-right: 20px;

}
</style>
<title>OpinaFiuba</title>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <g:link action="departamentos" controller="home" class="navbar-brand"> OpinaFiuba</g:link>
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
                        <g:link action="index" controller="Registro"><span class="glyphicon glyphicon-check"></span> Registrarse</g:link>
                    </li>
                </sec:ifNotLoggedIn>
                <sec:ifLoggedIn>
                    <li>
                        <g:link action="index" controller="perfil"><span class="glyphicon glyphicon-user"></span> Perfil</g:link>
                    </li>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <li>
                        <g:link action="index" controller="Login"><span class="glyphicon glyphicon-user"></span> Ingresar</g:link>
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