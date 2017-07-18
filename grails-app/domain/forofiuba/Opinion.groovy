package forofiuba

class Opinion {
    Curso curso
    Alumno alumno
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
    Cuatrimestre cuatrimestre

    static hasMany = [calificaciones: CalificacionOpinion]

    static belongsTo = [Curso, Alumno]

    static constraints = {
        curso nullable: false
        alumno nullable: false
        puntuacion range: 1..5, nullable: false
        opinionTp nullable: true
        opinionFinal nullable: true
        opinionTeorica nullable: true
        horarios nullable: true
        opinionParcial nullable: true
        opinionPractica nullable: true
        opinionProfesores nullable: true
        modalidad nullable: true
        profesores nullable: true
        fechaPublicacion nullable: false
    }

    static  mapping = {

    }

    public int getCalificacionesPositivas(){
        int cal= calificaciones.findAll{CalificacionOpinion c ->
            c.meSirvioLaOpinion}.size()
        return  cal
    }
    public int getCalificacionesNegativas(){
        int cal= calificaciones.findAll{CalificacionOpinion c ->
            !c.meSirvioLaOpinion}.size()
        return cal
    }


    static createOpinion(String cursoId, Alumno alumno, String horarios = null, String opinionTp = null, String opinionParcial = null, String opinionFinal = null, String opinionTeorica = null, String opinionProfesores = null, String opinionPractica = null, String modalidad = null, String profesores = null, Integer puntuacion = null, Date fechaPublicacion, String anio, String cuatrimestre) {

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
        o.cuatrimestre = new Cuatrimestre("cuatrimestre":cuatrimestre,"anio":anio)
        o.puntuacion = puntuacion
        o.curso = Curso.get(cursoId)
        o.alumno = alumno;
        o.fechaPublicacion = fechaPublicacion
        o.save(flush: true,     failOnError: true)
        alumno.calcularKarma()
        alumno.save()
        List<Alumno> alumnos= Alumno.getTopKarmaAlumnos();
        AlumnoRol.createKarmaAlumnos(alumnos)
        alumno.reauthentificateIfInList(alumnos)
    }


    def static getOpinionesByCurso(Curso curso) {
        curso.opiniones
    }
    def static getOpinionesByUsername(Alumno alumno) {
        alumno.opiniones
    }

    def getMateria(){
        return curso.catedra.materia
    }

}
