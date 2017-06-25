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
    Usuario usuario1
    Usuario usuario2
    Curso curso1
    Curso curso2
    Curso curso3
    Catedra catedra1
    Catedra catedra2
    Opinion opinion1
    Opinion opinion2
    Opinion opinion3

    def setup() {
        carrera = new Carrera(id:1,nombre: "Informatica");
        dep1=new Departamento(id:1,nombre:  "Fisica")
        dep2=new Departamento(id:2,nombre: "Mate")
        materia1=new Materia(id: 1,nombre: "Fisica I",departamento: dep1, carreras: [carrera])
        materia2=new Materia(id: 2,nombre: "Fisica II",departamento: dep1,correlativas: [materia1],carreras: [carrera])
        materia3=new Materia(id: 3,nombre: "Analisis II",departamento: dep2 , carreras: [carrera])
        curso1=new Curso(id:1,nombre: "echarri",catedra: catedra1)
        curso2=new Curso(id:2,nombre: "garea",catedra: catedra1)
        curso3=new Curso(id:3,nombre: "garea2",catedra: catedra2)
        catedra1=new Catedra(id:1,nombre: "echarri",cursos:[curso1,curso2],materia: materia1)
        catedra2=new Catedra(id:1,nombre: "garea",cursos:[curso3],materia: materia2)
        materia1.catedras=[catedra1]
        materia2.catedras=[catedra2]
        usuario1= new Usuario(id:1,carreras: [carrera ] )
        usuario2= new Usuario(id:2,carreras: [carrera ] )
        carrera.materias=[materia1,materia2,materia3]
        opinion1 = new Opinion(curso:  curso1,usuario: usuario1)
        opinion2 = new Opinion(curso:  curso3,usuario: usuario1)
        opinion3 = new Opinion(curso:  curso1,usuario: usuario2)
        curso1.opiniones=[opinion1,opinion3]
        curso3.opiniones=[opinion2]
        usuario1.opiniones=[opinion1,opinion2]
        usuario2.opiniones=[opinion3]
    }

    def cleanup() {
    }
    void "Probar materiaPerteneceACarrerasUsuario"() {
        when:
            setup()
        then:
            (materia1.materiaPerteneceACarrerasUsuario(usuario1) && materia2.materiaPerteneceACarrerasUsuario(usuario1) && materia3.materiaPerteneceACarrerasUsuario(usuario1))
    }
    void "Probar obtenerMateriasSegunNombre vacio"() {
        when:
            setup()
        then:
             List<Parecido> parecidos= Materia.obtenerMateriasSegunNombre([curso1,curso2,curso3],"")
             !parecidos.isEmpty()
        then:
            parecidos.size()==3
    }

    void "Probar obtenerMateriasSegunNombre materias diferenetes"() {
        when:
        setup()
        then:
        List<Parecido> parecidos= Materia.obtenerMateriasSegunNombre([curso1,curso2,curso3],"garea")
        !parecidos.isEmpty()
        then:
        parecidos.size()==2
    }

    void "Probar obtenerMateriasSegunNombre unico curso"() {
        when:
        setup()
        then:
            List<Parecido> parecidos= Materia.obtenerMateriasSegunNombre([curso1,curso2,curso3],"echarri")
            !parecidos.isEmpty()
        then:
            parecidos.size()==1
        when:
            Parecido parecido=parecidos[0]

        then:
            parecido.cursoNombre=="echarri"

    }/**
    void "Probar obtenerMateriasParecidas"() {
            when:
            setup()
            then:
            List<Parecido> parecidos= materia1.obtenerMateriasParecidas(curso1)
            !parecidos.isEmpty()
            then:
            parecidos.size()==1
            //when:
            //Parecido parecido=parecidos[0]

    }**/

}
