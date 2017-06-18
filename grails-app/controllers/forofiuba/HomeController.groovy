package forofiuba

import grails.plugin.springsecurity.annotation.Secured

class HomeController {

    def springSecurityService
    def armadorDeHilo

    def index() {
        departamentos()
    }

    def departamentos(){
        render(view: "Departamentos", model: [Carreras: Carrera.getAllMateriasPorCarrera(),Departamentos: Departamento.getDepartamentos()])
    }

    def materias() {

        render(view: "Materias", model: [Materias: Materia.getMaterias(params.departamentoId), hilo: armadorDeHilo.calcularHiloMaterias(params.departamentoId),carreras:Carrera.findAll()])
    }

    def login() {
        redirect(controller: 'login', action: 'index')
    }
    def perfilUsuario(){
        redirect(controller: 'perfil',action: 'index',params: ["usuarioId":"${params.usuarioId}"])
    }

    def registrar() {
        redirect(controller: 'registro', action: 'index')
    }


    def catedras() {
        def carreras=Materia.get(params.materiaId).carreras
        [Catedras: Catedra.getCatedras(params.materiaId), hilo: armadorDeHilo.calcularHiloCatedras(params.materiaId), correlativas:Materia.get(params.materiaId).correlativas, carreras: Materia.get(params.materiaId).carreras]
    }


    def cursos() {
        render(view: "Cursos", model: [Cursos: Curso.getCursos(params.catedraId), hilo: armadorDeHilo.calcularHiloCursos(params.catedraId)])
    }

    def opiniones(){
        Usuario usuario = springSecurityService.currentUser
        Curso curso = Curso.get(params.cursoId)
        boolean puedeOpinar = false
        if (usuario){
            puedeOpinar = usuario.puedeOpinar(curso)
        }
        render(view:"opiniones", model: [puedeOpinar:puedeOpinar, Opiniones: Opinion.getOpinionesByCurso(params.cursoId), hilo: armadorDeHilo.calcularHiloOpiniones(params.cursoId), materiasParecidas:obtenerMateriasParecidas(params.cursoId)])
    }

    def busqueda(){
        render(view:"Busqueda", model: [Parecidos:obtenerMateriasSegunNombre(params.nombre)] )
    }


    @Secured(['ROLE_USER'])
    def createOpinion() {
        Usuario user = springSecurityService.currentUser
        Opinion.createOpinion(params.cursoId, user,params.horarios, params.opinionTp, params.opinionParcial, params.opinionFinal, params.opinionTeorica, params.opinionProfesores, params.opinionPractica, params.modalidad, params.profesores, params.puntuacion, new Date(), params.year, params.cuatrimestre)
        opiniones()
    }

    @Secured(['ROLE_ADMIN'])
    def createMateria() {
        Materia.createMateria(params.materiaNombre, params.materiaDescripcion, params.departamentoId,params.list("carrerasNombre"))
        materias()
    }

    @Secured(['ROLE_ADMIN'])
    def createCatedra() {
        Catedra.createCatedra(params.catedraNombre, params.catedraEmail, params.materiaId)
        catedras()
    }

    @Secured(['ROLE_ADMIN'])
    def createCurso() {
        Curso.createCurso(params.cursoNombre, params.cursoEmail, params.catedraId)
        cursos()
    }

    @Secured(['ROLE_ADMIN'])
    def createDepartamento() {
        Departamento.createDepartamento(params.departamentoNombre, params.departamentoEmail, params.departamentoTelefono)
        index()
    }

    @Secured(['ROLE_ADMIN'])
    def deleteMateria() {
        def rta = Materia.deleteMateria(params.materiaId)
        if (!rta) {
            println("No lo borre")
        }
        materias()
    }
    @Secured(['ROLE_ADMIN'])
    def deleteCatedra() {
        def rta = Catedra.deleteCatedra(params.catedraId)
        if (!rta) {
            println("No lo borre")
        }
        catedras()
    }
    @Secured(['ROLE_ADMIN'])
    def deleteCurso() {
        def rta = Curso.deleteCurso(params.cursoId)
        if (!rta) {
            println("No lo borre")
        }
        cursos()
    }

    @Secured(['ROLE_ADMIN'])
    def deleteDepartamento() {
        def rta = Departamento.deleteDepartamento(params.departamentoId)
        if (!rta) {
            println("No lo borre")
        }
        index()
    }

    def obtenerMateriasParecidas(String cursoId){
        def usuarios = Opinion.findAllByCurso(Curso.get(cursoId)).collect{opinion->
            opinion.usuario
        }.unique { a, b -> a.id <=> b.id }
        def pare = []
        usuarios.each { usu ->
            Opinion.findAllByUsuario(usu).unique { a, b -> a.curso.id <=> b.curso.id }.each{ op ->
                if(op.curso.id != cursoId) {
                    boolean entro = false
                    pare.each{
                        if (it.cursoId  == op.curso.id){
                            entro = true
                        }
                    }
                    if (!entro) {
                        def parecido = new Parecido()
                        parecido.cursoNombre = op.curso.nombre
                        parecido.materiaNombre = Materia.get(Catedra.get(Curso.get(op.curso.id).catedra.id).materia.id).nombre
                        parecido.cursoId = op.curso.id
                        //pare << p TODO: Que es esto?
                    }
                }
            }
        }
        pare.findAll{it -> it.cursoId != cursoId}.sort{it.contador}
    }



}

