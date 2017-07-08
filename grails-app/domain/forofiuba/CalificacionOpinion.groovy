package forofiuba

import groovyx.gpars.extra166y.Ops

class CalificacionOpinion {

    static constraints = {
        meSirvioLaOpinion nullable:false
        usuario nullable: false
        opinion nullable: false
    }
    boolean meSirvioLaOpinion;
    Usuario usuario
    Opinion opinion
    static belongsTo= [Usuario,Opinion]

    public  static boolean  calificoOpinion(Usuario usuario,Opinion opinion){
        usuario.calificaciones.find {CalificacionOpinion c -> c.usuario==usuario }!=null
    }
    def static createCalificacionOpinion(Usuario usuario,Opinion opinion,boolean meSirvioLaOpinion){
        CalificacionOpinion calificacionOpinion= new CalificacionOpinion()
        calificacionOpinion.usuario=usuario
        calificacionOpinion.opinion=opinion
        calificacionOpinion.meSirvioLaOpinion=meSirvioLaOpinion
        calificacionOpinion.save(flush:true)

    }
}
