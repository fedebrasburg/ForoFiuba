package forofiuba

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Carrera)
class CarreraSpec extends Specification {
    Carrera carrera1
    Carrera carrera2
                    Materia materia1
    Materia materia2
    Materia materia3

    def setup() {
        carrera1 = new Carrera(id:1,nombre: "Informatica");
        carrera2 = new Carrera(id:2,nombre: "Electronica");
        materia1=new Materia(id: 1,nombre: "Fisica I", carreras: [carrera1,carrera2])
        materia2=new Materia(id: 2,nombre: "ElectroMagnetismo",correlativas: [materia1],carreras: [carrera2])
        materia3=new Materia(id: 3,nombre: "Analisis II", carreras: [carrera1])
        carrera1.materias=[materia1,materia3]
        carrera2.materias=[materia1,materia2]
    }

    def cleanup() {
    }
    void "Probar diccionarioMateriasPorCarrera"() {
        when:
            setup()
        then:
            def dicc =Carrera.diccionarioMateriasPorCarrera([carrera2,carrera1])
            (materia1 in dicc[carrera1])&&(materia3 in dicc[carrera1])&&(materia1 in dicc[carrera2])&&(materia2 in dicc[carrera2])
    }

    void "Probar diccionarioMateriasPorCarrera Vacio"() {
        when:
        carrera1 = new Carrera(id:1,nombre: "Informatica");
        materia1=new Materia(id: 1,nombre: "Fisica I", carreras: [carrera1,carrera2])
        then:
            def dicc =Carrera.diccionarioMateriasPorCarrera([carrera1])
            !(materia1 in dicc[carrera1])

        when:
            carrera1.materias=[materia1]
            dicc =Carrera.diccionarioMateriasPorCarrera([carrera1])
        then: (materia1 in dicc[carrera1])
    }
}
