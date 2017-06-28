<style>
body {
    background-color: #E3E5F2;
}

.body{
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

form {
    border: double;
    margin-right: 20px;

}

.footer {
    bottom: 0;
    width: 100%;
    /* Set the fixed height of the footer here */
    height: 60px;
    background-color: #f5f5f5;
}

.item-btn {
    background: #0c95f0;
    background-image: -webkit-linear-gradient(top, #0c95f0, #5287a8);
    background-image: -moz-linear-gradient(top, #0c95f0, #5287a8);
    background-image: -ms-linear-gradient(top, #0c95f0, #5287a8);
    background-image: -o-linear-gradient(top, #0c95f0, #5287a8);
    background-image: linear-gradient(to bottom, #0c95f0, #5287a8);
    -webkit-border-radius: 28;
    -moz-border-radius: 28;
    border-radius: 28px;
    font-family: Arial;
    color: #000000;
    font-size: 60px;
    padding: 15px 14px 15px 13px;
    text-decoration: none;
}

.item-btn:hover {
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