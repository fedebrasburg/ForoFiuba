package forofiuba

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class PerfilController {
    def springSecurityService

    def index() {
        Usuario usuario = springSecurityService.currentUser
        render(view: "Perfil",model: ["usuario":usuario])

    }
}
