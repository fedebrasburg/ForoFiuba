package forofiuba

class Opinion {
    Curso curso
    Usuario usuario
    String horarios
    String opinionTp
    String opinionParcial
    String opinionFinal
    String opinionTeorica
    String opinionProfesores
    String opinionPractica
    String modalidad
    String profesores
    Integer puntuacion
    Date fechaPublicacion


    static belongsTo = [Curso, Usuario]

    static constraints = {
        curso nullable: false
        usuario nullable: false
        puntuacion size: 1..5, nullable: true
        opinionTp nullable: true
        opinionFinal nullable: true
        opinionTeorica nullable: true
        horarios nullable: true
        opinionParcial nullable: true
        opinionPractica nullable: true
        opinionProfesores nullable: true
        modalidad nullable: true
        profesores nullable: true
        fechaPublicacion nullable: true

    }

    static createOpinion(String cursoId, Usuario user, String horarios = null, String opinionTp = null, String opinionParcial = null, String opinionFinal = null, String opinionTeorica = null, String opinionProfesores = null, String opinionPractica = null, String modalidad = null, String profesores = null, String puntuacion = null, Date fechaPublicacion) {
        def o = new Opinion()
        o.horarios = horarios
        o.opinionFinal = opinionFinal
        o.opinionParcial = opinionParcial
        o.opinionTeorica = opinionTeorica
        o.opinionTp = opinionTp
        o.opinionProfesores = opinionProfesores
        o.opinionPractica = opinionPractica
        o.modalidad = modalidad
        o.profesores = profesores
        if (puntuacion != "") {
            o.puntuacion = puntuacion.toInteger()
        } else {
            o.puntuacion = null
        }
        o.curso = Curso.get(cursoId)
        o.usuario = user.usuario;
        o.fechaPublicacion = fechaPublicacion
        o.save(flush: true,     failOnError: true)
    }

    def static getOpiniones(String cursoId) {
        Opinion.findAllByCurso(Curso.get(cursoId))
    }

}
