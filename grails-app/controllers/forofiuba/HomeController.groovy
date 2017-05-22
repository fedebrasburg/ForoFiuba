package forofiuba

class HomeController {

    def index() {
        render(view:"Materias", model: [Materias: getMaterias()])
    }

    def catedras(){
        render(view:"Catedras", model: [Catedras: getCatedras(params.materia)])
    }

    def getMaterias(){
        Materia.getAll()
    }

    def getCatedras(String materiaId){
        Catedra.findByMateria(Materia.get(materiaId))
    }
}

