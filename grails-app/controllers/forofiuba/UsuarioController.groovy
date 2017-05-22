package forofiuba

import grails.plugin.springsecurity.annotation.Secured

@Secured('administrador')
class UsuarioController {

    static scaffold = Usuario

}
