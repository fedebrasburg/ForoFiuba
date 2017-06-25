package forofiuba

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Departamento)
class DepartamentoSpec extends Specification {

    def setup() {
        def departamento=new Departamento(nombre: "Matematica")
    }

    def cleanup() {
    }

    void "Probar Nombre"() {
        when:
            def dep=setup()
        then:
            dep.nombre=="Matematica"
    }

}
