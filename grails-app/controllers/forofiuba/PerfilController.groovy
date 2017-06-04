package forofiuba

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class PerfilController {
    def springSecurityService

    def render(boolean edit,String usuarioId){
        Usuario usuario =Usuario.findByUsername(usuarioId)
        Usuario  usuarioActual = springSecurityService.currentUser
        def opiniones=Opinion.getOpinionesByUsername(usuario.username)
        render(view: "Perfil",model: ["usuario":usuario,"edit":edit,"opiniones":opiniones, "usuarioActual":usuarioActual])
    }
    def index() {
        if(!params.usuarioId){
            render(false,springSecurityService.currentUser.username)
            return
        }
        render(false,params.usuarioId)
    }
    def mostrarEditar(){
        Usuario usuarioActual = springSecurityService.currentUser
        Usuario usuario =Usuario.findByUsername(params.usuarioId)
        if(usuarioActual!=usuario){
            render(false,usuario.username)
        }
        render(true,params.usuarioId)
    }
    def editar(){
        Usuario usuarioActual = springSecurityService.currentUser
        Usuario usuario =Usuario.findByUsername(params.usuarioId)
        if(usuarioActual!=usuario){
            render(false,usuario.username)
        }
        usuario.properties=params
        usuario.save(failOnError:true)
        render(false)
    }
    def deleteOpinion() {
        def o = Opinion.get(params.opinionId)
        o.delete(flush: true, failOnError: true)
        render(false)
    }

}
