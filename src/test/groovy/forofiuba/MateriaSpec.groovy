package forofiuba

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Materia)
class MateriaSpec extends Specification {
    Carrera carrera
    Departamento dep1
    Departamento dep2
    Materia materia1
    Materia materia2
    Materia materia3
    Usuario usuario
    def setup() {
         carrera = new Carrera(id:1,nombre: "Informatica");
         dep1=new Departamento(id:1,nombre:  "Fisica")
         dep2=new Departamento(id:1,nombre: "Mate")
         materia1=new Materia(id: 1,nombre: "Fisica I",departamento: dep1, carreras: [carrera])
         materia2=new Materia(id: 2,nombre: "Fisica II",departamento: dep1,correlativas: [materia1],carreras: [carrera])
         materia3=new Materia(id: 3,nombre: "Analisis II",departamento: dep2 , carreras: [carrera])
         usuario= new Usuario(id:1,carreras: [carrera ] )
         carrera.materias=[materia1,materia2,materia3]

    }

    def cleanup() {
    }
    void "Probar materiaPerteneceACarrerasUsuario"() {
        when:
            setup()
        then:
            (materia1.materiaPerteneceACarrerasUsuario(usuario) && materia2.materiaPerteneceACarrerasUsuario(usuario) && materia3.materiaPerteneceACarrerasUsuario(usuario))
    }
    void "Probar negativo materiaPerteneceACarrerasUsuario"() {
        when:
            Materia materia4=new Materia(id: 4,nombre: "Fisica I",departamento: dep1)
        then:
            (!materia4.materiaPerteneceACarrerasUsuario(usuario))
    }


}
