package forofiuba

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class PerfilController {
    def springSecurityService

    def render(boolean edit,String alumnoid){
        Alumno alumno =Alumno.findByUsername(alumnoid)
        Alumno alumnoActual = springSecurityService.currentUser
        def opiniones=Opinion.getOpinionesByUsername(alumno)
        def carreras
        if(edit){
            carreras=Carrera.findAll()
        }else {
            carreras=alumno.carreras
        }
        render(view: "Perfil",model: [alumno:alumno, "edit":edit, "opiniones":opiniones, alumnoActual:alumnoActual, "carreras":carreras, CursosCompartidos:alumnoActual.getCompas()])
    }
    def index() {
        if(!params.alumnoid){
            render(false,springSecurityService.currentUser.username)
            return
        }
        render(false,params.alumnoid)
    }
    def mostrarEditar(){
        Alumno alumnoActual = springSecurityService.currentUser
        render(true,alumnoActual.username)
    }

    def editar(){
        Alumno alumnoActual = springSecurityService.currentUser
        if(!params.alumnoid){
            render(false,alumnoActual.username)
            return
        }
        Alumno alumno =Alumno.findByUsername(params.alumnoid)
        if(alumnoActual!=alumno){
            render(false,alumno.username)
            return
        }
        alumnoActual.properties=params
        def carrerasListNombres =params.list('carrerasNombre')
        if(carrerasListNombres) {
            def carrerasList = Carrera.findAllByNombreInList(carrerasListNombres)
            alumnoActual.carreras = carrerasList
        }else{
            alumnoActual.carreras=[]
        }
        alumnoActual.save(failOnError:true,flush:true)
        render(false,alumno.username)
    }
    def deleteOpinion() {
        def o = Opinion.get(params.opinionId)
        o.delete(flush: true, failOnError: true)
        index()
    }


}
