package forofiuba

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(EstadoAlumnoCurso)
class EstadoAlumnoSpec extends Specification {
    Carrera carrera1
    Carrera carrera2

    Materia materia1
    Materia materia2
    Materia materia3
    Materia materia4
    Alumno usuario1

    Alumno usuario2
    Opinion opinion1
    Catedra catedra1
    Curso curso1


    def setup() {
        carrera1 = new Carrera(id:1,nombre: "Informatica");
        carrera2 = new Carrera(id:2,nombre: "Electronica");
        curso1 = new Curso(id:1,nombre: "Echarri")
        catedra1 = new Catedra(id:1,nombre: "Echarri", cursos: [curso1])
        curso1.catedra=catedra1
        materia1=new Materia(id: 1,nombre: "Fisica I" ,carreras: [carrera1,carrera2],catedras: [catedra1])
        catedra1.materia=materia1
        materia2=new Materia(id: 2,nombre: "Fisica II",correlativas: [materia1],carreras: [carrera1,carrera2])
        materia3=new Materia(id: 3,nombre: "Algo II", carreras: [carrera1])
        materia4=new Materia(id: 3,nombre: "Electromagnetismo",correlativas: [materia2], carreras: [carrera1,carrera2])

        usuario2=new Alumno(id:2,carreras: [carrera1 ] )
        usuario1= new Alumno(id:1,carreras: [carrera2 ] )
        carrera1.materias=[materia1, materia2, materia3]
        carrera2.materias=[materia1, materia2, materia3,materia4]
        opinion1 =new Opinion( alumno: usuario1,curso: curso1,puntuacion: 5,cuatrimestre: new Cuatrimestre(cuatrimestre: 1,anio: 2014))
        curso1.opiniones=[opinion1]
        usuario1.opiniones=[opinion1]
    }

    def cleanup() {
    }
    void "Estado cursado"(){
        when:
            setup()
        then:
            EstadoAlumnoCurso.estadoAlumnoCurso(usuario1, materia1)==EstadoAlumnoCurso.EstadoEnum.CURSADA
    }
    void "Estado no esta en el plan"(){
        when:
        setup()
        then:
        EstadoAlumnoCurso.estadoAlumnoCurso(usuario2, materia4)==EstadoAlumnoCurso.EstadoEnum.NOESTAENELPLAN
    }
    void "Estado faltan correlativas"(){
        when:
        setup()
        then:
        EstadoAlumnoCurso.estadoAlumnoCurso(usuario1, materia4)==EstadoAlumnoCurso.EstadoEnum.FALTANCORRELATIVAS
    }
    void "Estado cursable "(){
        when:
        setup()
        then:
            EstadoAlumnoCurso.estadoAlumnoCurso(usuario1, materia2)==EstadoAlumnoCurso.EstadoEnum.CURSABLE
    }

}
