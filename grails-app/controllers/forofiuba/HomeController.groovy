package forofiuba

import grails.plugin.springsecurity.annotation.Secured

class HomeController {

    class Hilo {
        String materiaNombre
        String materiaId
        String catedraNombre
        String catedraId
        String cursoNombre
        String cursoId
        String departamentoNombre
        String departamentoId
    }

    def index() {
        render(view: "Departamentos", model: [Departamentos: getDepartamentos()])
    }

    def materias() {
        render(view: "Materias", model: [Materias: getMaterias(params.departamentoId), hilo: calcularHiloMaterias(params.departamentoId)])
    }

    def login() {
        redirect(controller: 'login', action: 'index')
    }

    def registrar() {
        redirect(controller: 'registro', action: 'index')
    }

    def catedras() {
        render(view: "Catedras", model: [Catedras: getCatedras(params.materiaId), hilo: calcularHiloCatedras(params.materiaId)])
    }

    def cursos() {
        render(view: "Cursos", model: [Cursos: getCursos(params.catedraId), hilo: calcularHiloCursos(params.catedraId)])
    }

    def opiniones() {
        render(view: "Opiniones", model: [Opiniones: getOpiniones(params.cursoId), hilo: calcularHiloOpiniones(params.cursoId)])
    }


    def calcularHiloMaterias(String departamentoId) {
        def hilo = new Hilo()
        hilo.departamentoNombre = Departamento.get(departamentoId).nombre
        hilo.departamentoId = departamentoId
        hilo
    }

    def calcularHiloCatedras(String materiaId) {
        def hilo = calcularHiloMaterias(Materia.get(materiaId).departamento.id.toString())
        hilo.materiaId = materiaId
        hilo.materiaNombre = Materia.get(materiaId).nombre
        hilo
    }

    def calcularHiloCursos(String catedraId) {
        def hilo = calcularHiloCatedras(Catedra.get(catedraId).materia.id.toString())
        hilo.catedraId = catedraId
        hilo.catedraNombre = Catedra.get(catedraId).nombre
        hilo
    }

    def calcularHiloOpiniones(String cursoId) {
        def hilo = calcularHiloCursos(Curso.get(cursoId).catedra.id.toString())
        hilo.cursoId = cursoId
        hilo.cursoNombre = Curso.get(cursoId).nombre
        hilo
    }


    def getDepartamentos() {
        Departamento.getAll()
    }

    def getMaterias(String departamentoId) {
        Materia.findAllByDepartamento(Departamento.get(departamentoId))
    }

    def getCatedras(String materiaId) {
        Catedra.findAllByMateria(Materia.get(materiaId))
    }

    def getOpiniones(String cursoId) {
        Opinion.findAllByCurso(Curso.get(cursoId))
    }

    def getCursos(String catedraId) {
        Curso.findAllByCatedra(Catedra.get(catedraId))
    }

    @Secured(['ROLE_USER'])
    def createOpinion() {
        Opinion.createOpinion(params.cursoId, params.usuarioId, params.horarios, params.opinionTp, params.opinionParcial, params.opinionFinal, params.opinionTeorica, params.opinionProfesores, params.opinionPractica, params.modalidad, params.profesores, params.puntuacion)
        opiniones()
    }

    @Secured(['ROLE_ADMIN'])
    def createMateria() {
        Materia.createMateria(params.materiaNombre, params.materiaDescripcion, params.departamentoId)
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

    def deleteMateria() {
        def rta = Materia.deleteMateria(params.materiaId)
        if (!rta) {
            println("No lo borre")
        }
        materias()
    }

    def deleteCatedra() {
        def rta = Catedra.deleteCatedra(params.catedraId)
        if (!rta) {
            println("No lo borre")
        }
        catedras()
    }

    def deleteCurso() {
        def rta = Curso.deleteCurso(params.cursoId)
        if (!rta) {
            println("No lo borre")
        }
        cursos()
    }
}

