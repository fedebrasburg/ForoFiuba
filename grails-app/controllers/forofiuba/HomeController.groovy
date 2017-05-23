package forofiuba

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

    def index() {
        render(view:"Materias", model: [Materias: getMaterias()])
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
        h.cursoId = params.cursoId
        h.cursoNombre = params.cursoNombre
        render(view:"opiniones", model: [Opiniones: getOpiniones(params.cursoId), hilo:h])
    }

    def createOpinion(){
        Opinion.createOpinion(params.cursoId,params.usuarioId, params.opinion)
        opiniones()
    }

    def getMaterias(){
        Materia.getAll()
    }

    def getCatedras(String materiaId){
        Catedra.findByMateria(Materia.get(materiaId))
    }

    def getOpiniones(String cursoId){
        Opinion.findByCurso(Curso.get(cursoId))
    }

    def getCursos(String catedraId){
        Curso.findByCatedra(Catedra.get(catedraId))
    }
}

