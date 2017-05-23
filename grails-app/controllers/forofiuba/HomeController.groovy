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
    }

    def h = new Hilo()

    @Secured('ROLE_USER')
    def index() {
        render(view:"Materias", model: [Materias: getMaterias()])
    }

    @Secured('ROLE_USER')
    def catedras(){
        h.materiaNombre = params.materiaNombre
        h.materiaId = params.materiaId
        render(view:"Catedras", model: [Catedras: getCatedras(params.materiaId), hilo:h])
    }

    @Secured('ROLE_USER')
    def cursos(){
        h.catedraNombre = params.catedraNombre
        h.catedraId = params.catedraId
        render(view:"cursos", model: [Cursos: getCursos(params.catedraId), hilo:h])
    }
    @Secured('ROLE_USER')
    def opiniones(){
        h.cursoId = params.cursoId
        h.cursoNombre = params.cursoNombre
        render(view:"opiniones", model: [Opiniones: getOpiniones(params.cursoId), hilo:h])
    }
    @Secured('ROLE_USER')
    def getMaterias(){
        Materia.getAll()
    }
    @Secured('ROLE_USER')
    def getCatedras(String materiaId){
        Catedra.findByMateria(Materia.get(materiaId))
    }
    @Secured('ROLE_USER')
    def getOpiniones(String cursoId){
        Opinion.findByCurso(Curso.get(cursoId))
    }
    @Secured('ROLE_USER')
    def getCursos(String catedraId){
        Curso.findByCatedra(Catedra.get(catedraId))
    }
}

