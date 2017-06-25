package forofiuba

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Curso)
class CursoSpec extends Specification {
    Curso curso1
    Curso curso2
    Catedra catedra


    def setup() {
        curso1=new Curso(nombre: "barrios")
        curso2=new Curso(nombre: "martinez")
        catedra=new Catedra(nombre: "Wachenchauzer",cursos: [curso1,curso2])
    }

    def cleanup() {
    }
    void "Probar getCursos"(){
        when:
            setup()
        then:
            curso1 in Curso.getCursos(catedra)
        then:
            curso2 in Curso.getCursos(catedra)

    }
}
