package forofiuba

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

 class ArmadorDeHilo{
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

}