package forofiuba

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Catedra)
class CatedraSpec extends Specification {

    Catedra catedra1
    Catedra catedra2
    Materia materia

    def setup() {
        catedra1=new Catedra(nombre: "Rosita")
        catedra2=new Catedra(nombre: "Rosita")
        materia=new Materia(nombre: "Algoritmos",catedras: [catedra1,catedra2])

    }

    def cleanup() {
    }
    void "Probar getMaterias"(){
        when:
            setup()
        then:
            catedra1 in Catedra.getCatedras(materia)
        then:
            catedra2 in Catedra.getCatedras(materia)

    }



}
