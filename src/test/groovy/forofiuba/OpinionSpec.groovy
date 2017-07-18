package forofiuba

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Opinion)
class OpinionSpec extends Specification {

    Alumno usuario
    Opinion opinion


    def setup() {
        usuario=new Alumno()
        opinion=new Opinion()
        CalificacionOpinion calificacionOpinion=new CalificacionOpinion(alumno: usuario,opinion: opinion,meSirvioLaOpinion: true)
        CalificacionOpinion calificacionOpinion2=new CalificacionOpinion(alumno: usuario,opinion: opinion,meSirvioLaOpinion: true)
        CalificacionOpinion calificacionOpinion3=new CalificacionOpinion(alumno: usuario,opinion: opinion,meSirvioLaOpinion: false)

        opinion.calificaciones=[calificacionOpinion , calificacionOpinion2,calificacionOpinion3]
    }

    def cleanup() {
    }
    void "Calificaciones positivas de una opinion"() {
        when:
            setup()
        then:
            opinion.getCalificacionesPositivas()==2

    }
    void "Calificaciones negativas de una opinion"() {
        when:
        setup()
        then:
        opinion.getCalificacionesNegativas()==1

    }
}
