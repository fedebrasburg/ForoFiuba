package forofiuba

import grails.test.mixin.TestFor
import groovyx.gpars.extra166y.Ops
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Alumno)
class AlumnoSpec extends Specification {
    Alumno alumno1
    Opinion opinion1
    Alumno alumno2
    Opinion opinion2
    def setup() {
        alumno1=new Alumno()
        alumno1.karma=new Karma(alumno: alumno1)
    }
    def setup1() {
        setup()
        opinion1=new Opinion(puntuacion: 4,cuatrimestre: new Cuatrimestre(anio: 2017,cuatrimestre: 1))
        alumno1.opiniones=[opinion1]
    }

    def setup2() {
        alumno2=new Alumno()
        alumno2.karma=new Karma(alumno: alumno2)
        opinion2=new Opinion(puntuacion:5,cuatrimestre: new Cuatrimestre(anio: 2017,cuatrimestre: 1))
        alumno2.opiniones=[opinion2]
    }
    def setup3(){
        setup1()
        setup2()
        CalificacionOpinion calificacionOpinion=new CalificacionOpinion(alumno: alumno2,opinion: opinion1,meSirvioLaOpinion: true)
        opinion1.calificaciones=[calificacionOpinion]
        alumno2.calificaciones=[calificacionOpinion]
    }

    def setup4(){
        setup1()
        setup2()
        CalificacionOpinion calificacionOpinion=new CalificacionOpinion(alumno: alumno2,opinion: opinion1,meSirvioLaOpinion: false)
        opinion1.calificaciones=[calificacionOpinion]
        alumno2.calificaciones=[calificacionOpinion]
    }
    def cleanup() {
    }
    void  "Alumno sin interaccion"(){
        when:
            setup()
        then:
            alumno1.calcularKarma()==alumno1.karmaCalculado
        then:
            alumno1.karmaCalculado==0
    }
    void  "Alumno con un comentario"(){
        when:
        setup1()
        then:
        alumno1.calcularKarma()==15//Confiable(1)*10 + Regularidad(1)*2+CantidadDeOpioniones(1)*3+cantidad de calificaciones(0)*1+Calificacione positivas - negativas(0)*2
    }

    void  "Alumno con un comentario, no confiable"(){
        when:
        setup2()
        then:
        alumno2.calcularKarma()==0//Confiable(-1)*10 + Regularidad(1)*2+CantidadDeOpioniones(1)*3+cantidad de calificaciones(0)*1+Calificacione positivas - negativas(0)*2
  }
    void  "Alumno con un comentario, no confiable, se vuelve confiable"(){
        when:
        setup2()
        then:
        alumno2.calcularKarma()==0//Confiable(-1)*10 + Regularidad(1)*2+CantidadDeOpioniones(1)*3+cantidad de calificaciones(0)*1+Calificacione positivas - negativas(0)*2
        when:
        opinion1=new Opinion(puntuacion: 2,cuatrimestre: new Cuatrimestre(anio: 2017,cuatrimestre: 1))
        alumno2.opiniones.add(opinion1)
        then:
        alumno2.calcularKarma()==20//Confiable(1)*10 + Regularidad(2)*2+CantidadDeOpioniones(2)*3+cantidad de calificaciones(0)*1+Calificacione positivas - negativas(0)*2
    }
    void "Alumno menos regular"(){
        when:
        setup2()
        opinion1=new Opinion(puntuacion: 2,cuatrimestre: new Cuatrimestre(anio: 2016,cuatrimestre: 1))
        alumno2.opiniones.add(opinion1)
        then:
        alumno2.calcularKarma()==18//Confiable(1)*10 + Regularidad(1)*2+CantidadDeOpioniones(2)*3+cantidad de calificaciones(0)*1+Calificacione positivas - negativas(0)*2
    }
    void "Alumno califica positivo"(){
        when:
            setup3()
        then:
            alumno1.calcularKarma()==17 //Confiable(1)*10 + Regularidad(1)*2+CantidadDeOpioniones(1)*3+cantidad de calificaciones(0)*1+Calificacione positivas - negativas(1)*2
        then:
            alumno2.calcularKarma()==0 //Confiable(-1)*10 + Regularidad(1)*2+CantidadDeOpioniones(1)*3+cantidad de calificaciones(1)*1+Calificacione positivas - negativas(0)*2
        when:
            Opinion opinion= new Opinion(puntuacion: 2,cuatrimestre: new Cuatrimestre(anio: 2017,cuatrimestre: 1))
            alumno2.opiniones.add(opinion)
        then:
            alumno2.calcularKarma()==21 //Confiable(1)*10 + Regularidad(2)*2+CantidadDeOpioniones(2)*3+cantidad de calificaciones(1)*1+Calificacione positivas - negativas(0)*2
    }
    void "Alumno realiza una nueva calificacion y una nueva opinion"(){
        when:
            setup3()
            Opinion opinion= new Opinion(puntuacion: 2,cuatrimestre: new Cuatrimestre(anio: 2017,cuatrimestre: 1))
            alumno1.opiniones.add(opinion)
            CalificacionOpinion calificacionOpinion=new CalificacionOpinion(alumno: alumno1,opinion: opinion2,meSirvioLaOpinion: true)
            opinion2.calificaciones=[calificacionOpinion]
            alumno1.calificaciones=[calificacionOpinion]
        then:
            alumno1.calcularKarma()==23 //Confiable(1)*10 + Regularidad(2)*2+CantidadDeOpioniones(2)*3+cantidad de calificaciones(1)*1+Calificacione positivas - negativas(1)*2
    }
    void "Alumno realiza una calificacion negativa"(){
        when:
            setup4()
        then:
            alumno1.calcularKarma()==13 //Confiable(1)*10 + Regularidad(1)*2+CantidadDeOpioniones(1)*3+cantidad de calificaciones(0)*1+Calificacione positivas - negativas(-1)*2
    }
}
