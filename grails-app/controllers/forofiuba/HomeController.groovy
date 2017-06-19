package forofiuba

import grails.plugin.springsecurity.annotation.Secured

class HomeController {

    def springSecurityService
    def armadorDeHilo

    def index(){
        departamentos()
    }

    def departamentos(){
        render("view":"departamentos", "model":[Carreras: Carrera.getAllMateriasPorCarrera(), Departamentos: Departamento.getDepartamentos()])
    }

    def materias() {
        render("view":"materias", "model":[Materias: Materia.getMaterias(params.departamentoId), hilo: armadorDeHilo.calcularHiloMaterias(params.departamentoId), carreras:Carrera.findAll()])
    }

    def login() {
        redirect(controller: 'login', action: 'departamentos')
    }
    def perfilUsuario(){
        redirect(controller: 'perfil',action: 'departamentos',params: ["usuarioId":"${params.usuarioId}"])
    }

    def registrar() {
        redirect(controller: 'registro', action: 'departamentos')
    }


    def catedras() {
        def carreras=Materia.get(params.materiaId).carreras
        render("view":"catedras", "model":[Catedras: Catedra.getCatedras(params.materiaId), hilo: armadorDeHilo.calcularHiloCatedras(params.materiaId), correlativas:Materia.get(params.materiaId).correlativas, carreras: Materia.get(params.materiaId).carreras])
    }


    def cursos() {
        render("view":"cursos", "model":[Cursos: Curso.getCursos(params.catedraId), hilo: armadorDeHilo.calcularHiloCursos(params.catedraId)])
    }

    def opiniones(){
        Usuario usuario = springSecurityService.currentUser
        Curso curso = Curso.get(params.cursoId)
        boolean puedeOpinar = false
        def materiasFaltantes = []
        if (usuario){
            puedeOpinar = usuario.puedeOpinar(curso)
            materiasFaltantes = usuario.materiasFaltantes(curso)
        }
        render("view":"opiniones", "model":[materiasFaltantes:materiasFaltantes, puedeOpinar:puedeOpinar, Opiniones: Opinion.getOpinionesByCurso(params.cursoId), hilo: armadorDeHilo.calcularHiloOpiniones(params.cursoId), materiasParecidas: curso.catedra.materia.obtenerMateriasParecidas(params.cursoId)])
    }

    def busqueda(){
        [Parecidos:Materia.obtenerMateriasSegunNombre(params.nombre)]
    }


    @Secured(['ROLE_USER'])
    def createOpinion() {
        println("EERNERNKWJN")
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
        departamentos()
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
        departamentos()
    }




}

