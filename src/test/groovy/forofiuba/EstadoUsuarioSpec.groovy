package forofiuba

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(EstadoUsuario)
class EstadoUsuarioSpec extends Specification {
    Carrera carrera1
    Carrera carrera2

    Materia materia1
    Materia materia2
    Materia materia3
    Materia materia4
    Usuario usuario1
    Usuario usuario2


    def setup() {
        carrera1 = new Carrera(id:1,nombre: "Informatica");
        carrera2 = new Carrera(id:2,nombre: "Electronica");

        materia1=new Materia(id: 1,nombre: "Fisica I" ,carreras: [carrera1])
        materia2=new Materia(id: 2,nombre: "Fisica II",correlativas: [materia1],carreras: [carrera1])
        materia3=new Materia(id: 3,nombre: "Analisis II", carreras: [carrera1])
        materia4=new Materia(id: 3,nombre: "Electromagnetismo", carreras: [carrera1])

        usuario1= new Usuario(id:1,carreras: [carrera1 ] )
        usuario2=new Usuario(id:2,carreras: [carrera1,carrera2])
        carrera1.materias=[materia1, materia2, materia3]
        carrera2.materias=[materia1, materia2, materia3,materia4]

    }

    def cleanup() {
    }

}
