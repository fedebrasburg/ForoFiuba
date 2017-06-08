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
            carreras=Carrera.withCriteria {
                alumnos {
                    eq('username', usuario.username)
                }
            }
        }
        render(view: "Perfil",model: ["usuario":usuario,"edit":edit,"opiniones":opiniones, "usuarioActual":usuarioActual,"carreras":carreras,CursosCompartidos:getCompas(usuarioActual)])
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
        if(!params.usuarioId){
            render(false,usuarioActual.username)
            return
        }
        Usuario usuario =Usuario.findByUsername(params.usuarioId)
        if(usuarioActual!=usuario){
            render(false,usuario.username)
            return
        }
        usuario.properties=params
        def carrerasListNombres =params.list('carrerasNombre')
        def carrerasList= Carrera.findAllByNombreInList(carrerasListNombres)
        usuario.carreras=carrerasList
        usuario.save(failOnError:true)
        render(false)
    }
    def deleteOpinion() {
        def o = Opinion.get(params.opinionId)
        o.delete(flush: true, failOnError: true)
        render(false)
    }

    def getCompas(Usuario user){
        def cursosCompartidos = [:]
        Opinion.findAllByUsuario(user).unique { a, b -> a.curso <=> b.curso }.each{ opinion ->
            def compas = []
            Opinion.findAllByCurso(opinion.curso).each{posibleMatch ->
                if(posibleMatch.year == opinion.year && posibleMatch.cuatrimestre == opinion.cuatrimestre &&  posibleMatch.usuario.id != opinion.usuario.id){
                    compas << posibleMatch.usuario
                }

            }
            if(!compas.isEmpty()){
                def c = new cursoCompartido()
                c.cursoNombre = opinion.curso.nombre
                c.cuatrimestre = opinion.cuatrimestre
                c.year = opinion.year
                cursosCompartidos[c] = compas
            }
        }
        cursosCompartidos
    }

    class cursoCompartido{
        String cursoNombre
        String cuatrimestre
        String year
    }
}
