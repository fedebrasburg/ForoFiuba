package forofiuba

class CalificacionOpinion {

    static constraints = {
        meSirvioLaOpinion nullable:false
        alumno nullable: false
        opinion nullable: false
    }
    boolean meSirvioLaOpinion;
    Alumno alumno
    Opinion opinion
    static belongsTo= [Alumno, Opinion]

    public  static boolean  calificoOpinion(Alumno alumno, Opinion opinion){
        opinion.calificaciones.find {CalificacionOpinion c -> c.alumno==alumno }!=null
    }
    def static createCalificacionOpinion(Alumno alumno, Opinion opinion, boolean meSirvioLaOpinion){
        CalificacionOpinion calificacionOpinion= new CalificacionOpinion()
        calificacionOpinion.alumno=alumno
        calificacionOpinion.opinion=opinion
        calificacionOpinion.meSirvioLaOpinion=meSirvioLaOpinion
        calificacionOpinion.save(flush:true)
        alumno.calcularKarma()
        alumno.save(flush:true)
        opinion.alumno.calcularKarma()
        opinion.alumno.save(flush:true)
        List<Alumno> alumnos= Alumno.getTopKarmaAlumnos();
        AlumnoRol.createKarmaAlumnos(alumnos)
        alumno.reauthentificateIfInList(alumnos)
    }
}
