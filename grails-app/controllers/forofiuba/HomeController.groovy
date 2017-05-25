package forofiuba

import grails.plugin.springsecurity.annotation.Secured

class HomeController {

    class Hilo{
        String materiaNombre
        String materiaId
        String catedraNombre
        String catedraId
        String cursoNombre
        String cursoId
        String departamentoNombre
        String departamentoId
    }

    def h = new Hilo()

    def index() {
        render(view:"Departamentos", model: [Departamentos: getDepartamentos()])
    }

    def materias(){
        h.departamentoId = params.departamentoId
        h.departamentoNombre = params.departamentoNombre
        render(view:"materias", model: [Materias: getMaterias(params.departamentoId), hilo:h])
    }

    def catedras(){
        h.materiaNombre = params.materiaNombre
        h.materiaId = params.materiaId
        render(view:"Catedras", model: [Catedras: getCatedras(params.materiaId), hilo:h])
    }

    def cursos(){
        h.catedraNombre = params.catedraNombre
        h.catedraId = params.catedraId
        render(view:"cursos", model: [Cursos: getCursos(params.catedraId), hilo:h])
    }
    def opiniones(){
        if (params.action != 'createOpinion') {
            h.cursoId = params.cursoId
            h.cursoNombre = params.cursoNombre
        }
        render(view:"opiniones", model: [Opiniones: getOpiniones(params.cursoId), hilo:h])
    }

    def getDepartamentos(){
        Departamento.getAll()
    }

    def getMaterias(String departamentoId){
        Materia.findAllByDepartamento(Departamento.get(departamentoId))
    }
    def getCatedras(String materiaId){
        Catedra.findAllByMateria(Materia.get(materiaId))
    }
    def getOpiniones(String cursoId){
        Opinion.findAllByCurso(Curso.get(cursoId))
    }
    def getCursos(String catedraId){
        Curso.findAllByCatedra(Catedra.get(catedraId))
    }


    def createOpinion(){
        Opinion.createOpinion( params.cursoId, params.usuarioId, params.horarios,params.opinionTp, params.opinionParcial, params.opinionFinal, params.opinionTeorica, params.opinionProfesores, params.opinionPractica, params.modalidad, params.profesores, params.puntuacion)
        opiniones()
    }
}

