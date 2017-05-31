<style>
body {
    background-color: #E3E5F2;
}

.body {
    margin-left: 20px;
}

.table {
    font-size: small;
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
<title>ForosFiuba</title>
<nav class="navbar navbar-default navbar-static-top navbar-inverse">
    <div class="container">
        <ul class="nav navbar-nav">
            <li>
                <g:link action="index"><img height="22" width="22"
                                            src="http://www.foros-fiuba.com.ar/templates/fisubsilversh/images/phpbb2_logo.gif"></g:link>
            </li>
            <li class="active">
                <g:link action="index"><span class="glyphicon glyphicon-home"></span> Home</g:link>
            </li>


            <sec:ifNotLoggedIn>
                <li>
                    <g:link action="registrar"><span class="glyphicon glyphicon-check"></span> Registrarse</g:link>
                </li>
            </sec:ifNotLoggedIn>
            <sec:ifNotLoggedIn>
                <li>
                    <g:link action="login"><span class="glyphicon glyphicon-user"></span> Ingresar</g:link>
                </li>
            </sec:ifNotLoggedIn>
            <sec:ifLoggedIn>
                <li>
                    <g:link controller='logout'><span class="glyphicon glyphicon-power"></span>Logout</g:link>
                </li>
            </sec:ifLoggedIn>

        </ul>
    </div>
</nav>