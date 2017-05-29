<style>
.body {
    background-color: white;
    margin-left: 20px;
}
.table{
    font-size: small;
}
</style>
<title> ForosFiuba</title>
<nav class="navbar navbar-default navbar-static-top navbar-inverse">
    <div class="container">
        <ul class="nav navbar-nav">
            <li>
                <g:link action="backhome"><img height="22" width="22" src="http://www.foros-fiuba.com.ar/templates/fisubsilversh/images/phpbb2_logo.gif"></g:link>
            </li>
            <li >
                <g:link action="backhome"><span class="glyphicon glyphicon-home"></span> Home</g:link>
            </li>
            <li class="active" >
                <g:link action="auth"><span class="glyphicon glyphicon-user"></span> Ingresar</g:link>
            </li>
            <li >
                <g:link action="registrar"><span class="glyphicon glyphicon-check"></span> Registrarse</g:link>
            </li>
            <li >
                <g:link controller='logout'><span class="glyphicon glyphicon-power"></span>Logout</g:link>
            </li>
        </ul>
    </div>
</nav>