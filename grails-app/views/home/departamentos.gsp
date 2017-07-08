<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <style>
    .bg-2 {
        background-color: #474e5d; /* Dark Blue */
        color: #ffffff;
        margin-top: 0px;
    }

    .image {
        position: relative;
        width: 100%;
    }

    .h1 {
        position: absolute;
        bottom: 100px;
        left: 0;
        width: 100%;
    }

    .h1 span {
        color: white;
        font: bold 100px/45px Helvetica, Sans-Serif;
        letter-spacing: -1px;
        background: rgb(0, 0, 0); /* fallback color */
        background: rgba(0, 0, 0, 0.7);
    }
    </style>
</head>

<body>
<g:render template="partials/Nav"/>

<script type="text/javascript">
    var TxtType = function (el, toRotate, period) {
        this.toRotate = toRotate;
        this.el = el;
        this.loopNum = 0;
        this.period = parseInt(period, 10) || 2000;
        this.txt = '';
        this.tick();
        this.isDeleting = false;
    };

    TxtType.prototype.tick = function () {
        var i = this.loopNum % this.toRotate.length;
        var fullTxt = this.toRotate[i];

        if (this.isDeleting) {
            this.txt = fullTxt.substring(0, this.txt.length - 1);
        } else {
            this.txt = fullTxt.substring(0, this.txt.length + 1);
        }

        this.el.innerHTML = '<span class="wrap">' + this.txt + '</span>';

        var that = this;
        var delta = 200 - Math.random() * 100;

        if (this.isDeleting) {
            delta /= 2;
        }

        if (!this.isDeleting && this.txt === fullTxt) {
            delta = this.period;
            this.isDeleting = true;
        } else if (this.isDeleting && this.txt === '') {
            this.isDeleting = false;
            this.loopNum++;
            delta = 500;
        }

        setTimeout(function () {
            that.tick();
        }, delta);
    };

    window.onload = function () {
        var elements = document.getElementsByClassName('typewrite');
        for (var i = 0; i < elements.length; i++) {
            var toRotate = elements[i].getAttribute('data-type');
            var period = elements[i].getAttribute('data-period');
            if (toRotate) {
                new TxtType(elements[i], JSON.parse(toRotate), period);
            }
        }
        // INJECT CSS
        var css = document.createElement("style");
        css.type = "text/css";
        // de la linea
        css.innerHTML = ".typewrite > .wrap { border-right: 0.08em solid #fff}";
        document.body.appendChild(css);
    };
</script>

<div class="image">

    <img height="100%" width="100%" src="${assetPath(src: 'fiuba.jpg')}"/>

    <h1 class="h1" align="center" style="color:White; font-size: 100px">
        <div class="typewrite" data-period="6000" data-type='[ "OpinaFiuba." ]'>
            <span class="wrap"></span>
        </div>
    </h1>

</div>

<div class="container-fluid bg-2 text-center" style="font-size: 175%">
    <div class="texto-cuerpo" style="padding-top: 70px; padding-bottom: 70px;">
        ¡Bienvenido a OpinaFiuba! Este sitio lo hacemos estudiantes de distintas carreras de la Facultad de Ingeniería de la Universidad de Buenos Aires. <br/>
        Es principalmente un sitio para compartir opinonenes sobre cursos, hecho para gente de FIUBA, por gente de FIUBA.
    </div>
</div>

<div class="body">
    <div  class="departamentosList" style="padding-top: 20px" align="center">
        <g:each var="departamento" in="${Departamentos}">
            <g:link action="materias" class="btn "
                    params="${[departamentoId: departamento.id, departamentoNombre: departamento.nombre]}">${departamento.nombre}</g:link>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <g:link action="deleteDepartamento" class="listado"
                        params="${[departamentoId: departamento.id]}"><span
                        class="glyphicon glyphicon-remove"></span></g:link>
            </sec:ifAllGranted>
        </g:each>
    </div>
    <br/>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <g:form name="myForm" action="createDepartamento">
            <fieldset>
                <legend>Crear Departamento</legend>

                <div class="form-group">
                    <label>Nombre Departamento:</label>
                    <g:field type="text" class="form-control" required="true" placeholder="Nombre del departamento"
                             name="departamentoNombre"/>
                </div>

                <div class="form-group">
                    <label>Mail:</label>
                    <g:field type="text" class="form-control" placeholder="Email" name="departamentoEmail"/>
                </div>

                <div class="form-group">
                    <label>Telefono:</label>
                    <g:field type="text" class="form-control" placeholder="Telefono" name="departamentoTelefono"/>
                </div>
                <g:submitButton class="btn btn-default" name="botonAgregarDepartamento" value="Crear Departamento"/>
            </fieldset>
        </g:form>
    </sec:ifAllGranted>


    <div class="container" style="padding-bottom: 50px">
        <h2>Plan de Carreras</h2>

        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <g:each var="carrera" in="${Carreras.keySet()}">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="${carrera.id}">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#${carrera.nombre}"
                               aria-expanded="false" aria-controls="${carrera.nombre}">
                                ${carrera.nombre}
                            </a>
                        </h4>
                    </div>

                    <div id="${carrera.nombre}" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="${carrera.id}">
                        <div class="panel-body">
                            <div style="font-size: large">
                                <g:each in="${carrera.materias.sort{it.nombre}}" var="materia">
                                    <g:link action="catedras"
                                            params="${[materiaId: materia.id, materiaNombre: materia.nombre]}">${materia.nombre}</g:link>
                                    <br/>
                                </g:each>
                            </div>
                        </div>
                    </div>
                </div>
            </g:each>
        </div>
    </div>
</div>

<div style="padding-top: 20px">
    <g:render template="partials/Footer"/>
</div>
</body>
</html>