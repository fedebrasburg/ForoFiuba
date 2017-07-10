package forofiuba

import grails.plugin.springsecurity.annotation.Secured

@Secured(['isAnonymous()'])
class RegistroController {

    def springSecurityService
    CreateUsuarioCommand createUsuarioCommand = new CreateUsuarioCommand()

    def index() {
        def carreras=Carrera.findAll()
        render(view: "Registrar",model:["carreras":carreras,"textoDefault":createUsuarioCommand] )
        createUsuarioCommand = new CreateUsuarioCommand()

    }

    def crearUsuario() {
        if(params.password!=params.checkpassword){
            List<String> errorList =new ArrayList<String>();
            errorList.add("Contraseñas diferentes")
            render(view: "Registrar", model: [errorList: errorList,"textoDefault":createUsuarioCommand])
            return
        }

        createUsuarioCommand = new CreateUsuarioCommand("nombre" :params.nombre, "password":params.password,"username": params.username,"genero":params.genero,"telefono":params.telefono,"fechaDeNacimiento":  params.fechaDeNacimiento)
        if (!createUsuarioCommand.validate()){
            List<String> errorList =new ArrayList<String>();
            createUsuarioCommand.errors.allErrors.each {
                errorList.add(it.toString())

            }
            render(view: "Registrar", model: [errorList: errorList,"textoDefault":createUsuarioCommand])
            return
        }
        def usuario = new Usuario(params)
        def carrerasListNombres =params.list('carrerasNombre')
        if(carrerasListNombres){
            def carrerasList= Carrera.findAllByNombreInList(carrerasListNombres)
            usuario.carreras=carrerasList
        }
        usuario.karma=new Karma()
        usuario.save(flush:true)
        if (usuario.hasErrors()){
            List<String> errorList =new ArrayList<String>();
            usuario.errors.allErrors.each {
                errorList.add(it.toString())

            }
            render(view: "Registrar", model: [errorList: errorList,"textoDefault":createUsuarioCommand])
            return
        }
        def role=Rol.findByAuthority("ROLE_USER")
        if(!role){
            throw ExceptionMessage("No encuentra el rol de usuario")
        }
        def usuarioRol =new UsuarioRol( user: usuario ,role:role)
        usuarioRol.save(flush:true,failOnError:true)
        springSecurityService.reauthenticate params.username,params.password

        backhome()
    }

    def backhome() {
        redirect(controller: 'home', action: 'index')
    }

    def login() {
        redirect(controller: 'login', action: 'index')
    }
}
