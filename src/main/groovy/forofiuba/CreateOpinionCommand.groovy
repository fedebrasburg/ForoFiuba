package forofiuba

import forofiuba.Opinion
import grails.validation.Validateable


class CreateOpinionCommand implements Validateable{
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
    Cuatrimestre cuatrimestre

    public CreateOpinionCommand(){
        cuatrimestre=new Cuatrimestre()
    }

    public CreateOpinionCommand(String horarios,String opinionTp,String opinionParcial,String opinionFinal,String opinionTeorica,String opinionProfesores,String opinionPractica,String modalidad,String profesores,Integer puntuacion,String cuatrimestre,String anio){
        this.horarios = horarios
        this.opinionTp = opinionTp
        this.opinionParcial = opinionParcial
        this.opinionFinal = opinionFinal
        this.opinionTeorica = opinionTeorica
        this.opinionProfesores = opinionProfesores
        this.opinionPractica = opinionPractica
        this.modalidad = modalidad
        this.profesores = profesores
        this.cuatrimestre = new Cuatrimestre("cuatrimestre":cuatrimestre,"anio":anio)
        this.puntuacion=puntuacion
        println(puntuacion)
    }

    static constraints = {
        importFrom Opinion
    }
}