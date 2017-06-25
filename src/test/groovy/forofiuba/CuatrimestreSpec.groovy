package forofiuba

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Cuatrimestre)
class CuatrimestreSpec extends Specification {

    def setup1() {
        new Cuatrimestre("anio":2014,"cuatrimestre":1)
    }
    def setup2() {
        new Cuatrimestre("anio":2014,"cuatrimestre":2)
    }
    def setup3() {
        new Cuatrimestre("anio":2013,"cuatrimestre":1)
    }
    def setup4() {
        new Cuatrimestre("anio":2014,"cuatrimestre":1)
    }
    def cleanup() {
    }

    void "Cuatrimestres son iguales con dos instancias diferentes"() {
        when:
        def cuatrimestre1=setup1()
        def cuatrimestre2=setup4()
        then:
        cuatrimestre1==cuatrimestre2
    }
    void "Cuatrimestres son diferentes"() {
        when:
        def cuatrimestre1=setup1()
        def cuatrimestre2=setup2()
        then:
        cuatrimestre1!=cuatrimestre2
    }
    void "Cuatrimestres menor"() {
        when:
        def cuatrimestre1=setup1()
        def cuatrimestre2=setup2()
        then:
        cuatrimestre1<cuatrimestre2
    }
    void "Cuatrimestres mayor"() {
        when:
        def cuatrimestre1=setup1()
        def cuatrimestre2=setup3()
        then:
        cuatrimestre1>cuatrimestre2
    }

}
