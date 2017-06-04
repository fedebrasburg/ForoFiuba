package forofiuba

import grails.plugin.springsecurity.annotation.Secured

@Secured(['isAnonymous()'])
class RegistroController {

    def springSecurityService

    def index() {
        render(view: "Registrar")
    }

    def crearUsuario() {
        if(params.password!=params.checkpassword){
            List<String> errorList =new ArrayList<String>();
            errorList.add("Contraseñas diferentes")
            render(view: "Registrar", model: [errorList: errorList])
            return
        }
        def usuario = new Usuario(params)
        usuario.save(flush:true)
        def role=Rol.findByAuthority("ROLE_USER")
        if(!role){
            throw ExceptionMessage("No encuentra el rol de usuario")
        }
        if(usuario.hasErrors()){
            List<String> errorList =new ArrayList<String>();
            usuario.errors.allErrors.each {
                errorList.add(it.toString())

            }
            render(view: "Registrar", model: [errorList: errorList])
            return
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
