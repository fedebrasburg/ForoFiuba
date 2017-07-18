<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

    <style type="text/css" media="screen">

    </style>
</head>

<body>
<g:render template="partials/Nav"/>

<div id="login">
    <div class="inner">
        <h4>Ingresar</h4>


        <g:if test='${flash.message}'>
            <div class="login_message">${flash.message}</div>
        </g:if>

        <form action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm" class="cssform"
              autocomplete="on">
            <p>
                <label for="username">Email</label>
                <input type="text" class="form-control" name="${usernameParameter ?: 'username'}" id="username"/>
            </p>

            <p>
                <label for="password">Contraseña</label>
                <input type="password" class="form-control" name="${passwordParameter ?: 'password'}" id="password"/>
            </p>

            <p id="remember_me_holder">
                <input type="checkbox" class="chk" name="${rememberMeParameter ?: 'remember-me'}" id="remember_me"
                       <g:if test='${hasCookie}'>checked="checked"</g:if>/>
                <label for="remember_me">Recordarme</label>
            </p>

            <p>
                <input type="submit" id="submit" value="Ingresar"/>
            </p>
        </form>
    </div>
</div>
<script>
    (function () {
        document.forms['loginForm'].elements['${usernameParameter ?: 'username'}'].focus();
    })();
</script>
</body>
</html>
