package forofiuba

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class PerfilController {
    def springSecurityService

    def render(boolean edit,String usuarioId){
        Usuario usuario =Usuario.findByUsername(usuarioId)
        Usuario  usuarioActual = springSecurityService.currentUser
        def opiniones=Opinion.getOpinionesByUsername(usuario.username.toString())
        def carreras
        if(edit){
            carreras=Carrera.findAll()
        }else {
            carreras=usuario.carreras
        }
        render(view: "Perfil",model: ["usuario":usuario,"edit":edit,"opiniones":opiniones, "usuarioActual":usuarioActual,"carreras":carreras,CursosCompartidos:usuarioActual.getCompas()])
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
        render(true,usuarioActual.username)
    }

    def editar(){
        Usuario usuarioActual = springSecurityService.currentUser
        if(!params.usuarioid){
            render(false,usuarioActual.username)
            return
        }
        Usuario usuario =Usuario.findByUsername(params.usuarioid)
        if(usuarioActual!=usuario){
            render(false,usuario.username)
            return
        }
        usuario.properties=params
        def carrerasListNombres =params.list('carrerasNombre')
        def carrerasList= Carrera.findAllByNombreInList(carrerasListNombres)
        usuario.carreras=carrerasList
        usuario.save(failOnError:true)
        render(false,usuario.username)
    }
    def deleteOpinion() {
        def o = Opinion.get(params.opinionId)
        o.delete(flush: true, failOnError: true)
        index()
    }


}
