package forofiuba

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(CalificacionOpinion)
class CalificacionOpinionSpec extends Specification {
    Alumno usuario
    Opinion opinion
    CalificacionOpinion calificacionOpinion
    def setup() {
        usuario=new Alumno()
        opinion=new Opinion()
        calificacionOpinion=new CalificacionOpinion(alumno: usuario,opinion: opinion,meSirvioLaOpinion: true)
        opinion.calificaciones=[calificacionOpinion]
        usuario.calificaciones=[calificacionOpinion]
    }

    def cleanup() {
    }

    void "Usuario Califico Opinion"() {
        when:
            setup()
        then:
            CalificacionOpinion.calificoOpinion(usuario,opinion)
    }
}
