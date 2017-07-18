package forofiuba

import grails.plugin.springsecurity.annotation.Secured

@Secured(['isAnonymous()'])
class RegistroController {

    def springSecurityService
    CreateAlumnoCommand createAlumnoCommand = new CreateAlumnoCommand()

    def index() {
        def carreras=Carrera.findAll()
        render(view: "Registrar",model:["carreras":carreras,"textoDefault":createAlumnoCommand] )
        createAlumnoCommand = new CreateAlumnoCommand()

    }

    def crearAlumno() {
        if(params.password!=params.checkpassword){
            List<String> errorList =new ArrayList<String>();
            errorList.add("Contraseñas diferentes")
            render(view: "Registrar", model: [errorList: errorList,"textoDefault":createAlumnoCommand])
            return
        }

        createAlumnoCommand = new CreateAlumnoCommand("nombre" :params.nombre, "password":params.password,"username": params.username,"genero":params.genero,"telefono":params.telefono,"fechaDeNacimiento":  params.fechaDeNacimiento)
        if (!createAlumnoCommand.validate()){
            List<String> errorList =new ArrayList<String>();
            createAlumnoCommand.errors.allErrors.each {
                errorList.add(it.toString())

            }
            render(view: "Registrar", model: [errorList: errorList,"textoDefault":createAlumnoCommand])
            return
        }
        def alumno = new Alumno(params)
        def carrerasListNombres =params.list('carrerasNombre')
        if(carrerasListNombres){
            def carrerasList= Carrera.findAllByNombreInList(carrerasListNombres)
            alumno.carreras=carrerasList
        }
        alumno.karma=new Karma()
        alumno.save(flush:true)

        if (alumno.hasErrors()){
            List<String> errorList =new ArrayList<String>();
            alumno.errors.allErrors.each {
                errorList.add(it.toString())

            }
            render(view: "Registrar", model: [errorList: errorList,"textoDefault":createAlumnoCommand])
            return
        }
        def role=Rol.findByAuthority("ROLE_USER")
        def alumnoRol =new AlumnoRol( alumno: alumno ,role:role)
        alumnoRol.save(flush:true,failOnError:true)
        springSecurityService.reauthenticate params.username,params.password
        alumno.calcularKarma()
        alumno.save()
        List<Alumno> alumnos= Alumno.getTopKarmaAlumnos();
        AlumnoRol.createKarmaAlumnos(alumnos)
        alumno.reauthentificateIfInList(alumnos)
        backhome()
    }

    def backhome() {
        redirect(controller: 'home', action: 'index')
    }

    def login() {
        redirect(controller: 'login', action: 'index')
    }
}
