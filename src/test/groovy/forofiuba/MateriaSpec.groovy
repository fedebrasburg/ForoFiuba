package forofiuba

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Materia)
class MateriaSpec extends Specification {
    Carrera carrera
    Carrera carrera2
    Departamento dep1
    Departamento dep2
    Departamento dep3
    Departamento dep4
    Materia materia1
    Materia materia2
    Materia materia3
    Materia materia4
    Materia materia5
    Alumno alumno1
    Alumno alumno2
    Alumno alumno3
    Curso curso1
    Curso curso2
    Curso curso3
    Curso curso4
    Curso curso5
    Curso curso6
    Catedra catedra1
    Catedra catedra2
    Catedra catedra3
    Catedra catedra4
    Catedra catedra5
    Opinion opinion12,opinion11,opinion23,opinion13
    CalificacionOpinion calificacionOpinion1,calificacionOpinion2

    def setup() {
        carrera = new Carrera(id:1,nombre: "Informatica");
        carrera2 = new Carrera(id:1,nombre: "Industrial");
        dep1=new Departamento(id:1,nombre:  "Fisica")
        dep2=new Departamento(id:2,nombre: "Mate")
        dep3=new Departamento(id:3,nombre: "Compu")
        dep4=new Departamento(id:4,nombre: "Indus")


        materia1=new Materia(id: 1,nombre: "Fisica I",departamento: dep1, carreras: [carrera])
        materia2=new Materia(id: 2,nombre: "Fisica II",departamento: dep1,correlativas: [materia1],carreras: [carrera])
        materia3=new Materia(id: 3,nombre: "Analisis II",departamento: dep2 , carreras: [carrera])
        materia4=new Materia(id: 4,nombre: "Algoritmos I",departamento: dep3 , carreras: [carrera])
        materia5=new Materia(id: 4,nombre: "cosas indus",departamento: dep3 , carreras: [carrera])

        curso1=new Curso(id:1,nombre: "echarri",catedra: catedra1)
        curso2=new Curso(id:2,nombre: "garea",catedra: catedra1)
        curso3=new Curso(id:3,nombre: "abraham",catedra: catedra2)
        curso4=new Curso(id:4,nombre: "sirne",catedra: catedra3)
        curso5=new Curso(id:5,nombre: "rosita",catedra: catedra4)
        curso6=new Curso(id:6,nombre: "robo",catedra: catedra5)

        catedra1=new Catedra(id:1,nombre: "echarri",cursos:[curso1,curso2],materia: materia1)
        catedra2=new Catedra(id:2,nombre: "abraham",cursos:[curso3],materia: materia2)
        catedra3=new Catedra(id:3,nombre: "Sirne",cursos:[curso4],materia: materia3)
        catedra4=new Catedra(id:4,nombre: "rosita",cursos:[curso5],materia: materia4)
        catedra5=new Catedra(id:5,nombre: "rosita",cursos:[curso6],materia: materia5)

        materia1.catedras=[catedra1]
        materia2.catedras=[catedra2]
        materia3.catedras=[catedra3]
        materia4.catedras=[catedra4]
        materia5.catedras=[catedra5]

        alumno1= new Alumno(id:1,carreras: [carrera ] )
        alumno2= new Alumno(id:2,carreras: [carrera ] )
        alumno3= new Alumno(id:3,carreras: [carrera2 ] )

        carrera.materias=[materia1,materia2,materia3,materia4]
        carrera2.materias=[materia1,materia2,materia3,materia5]

        calificacionOpinion1=new CalificacionOpinion(meSirvioLaOpinion: true)
        calificacionOpinion2=new CalificacionOpinion(meSirvioLaOpinion: false)

        opinion11 = new Opinion(curso:  curso1, alumno: alumno1 , puntuacion: 3,calificaciones: [])
        opinion12 = new Opinion(curso:  curso1, alumno: alumno2 , puntuacion: 3,calificaciones: [])
        opinion13 = new Opinion(curso:  curso2, alumno: alumno3 , puntuacion: 3,calificaciones: [])
        opinion23 = new Opinion(curso:  curso2, alumno: alumno3 , puntuacion: 3,calificaciones: [])

        curso1.opiniones=[opinion11,opinion12,opinion13]
        curso2.opiniones=[opinion23]
        curso3.opiniones=[]
        curso4.opiniones=[]
        curso5.opiniones=[]
        curso6.opiniones=[]

        alumno1.opiniones=[opinion11]
        alumno2.opiniones=[opinion12]
        alumno3.opiniones=[opinion23,opinion13]

    }

    def cleanup() {
    }
    void "Probar materiaPerteneceACarrerasalumno"() {
        when:
            setup()
        then:
            (materia1.materiaPerteneceACarrerasAlumno(alumno1) && materia2.materiaPerteneceACarrerasAlumno(alumno1) && materia3.materiaPerteneceACarrerasAlumno(alumno1))
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
        parecidos.size()==1
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
            parecido.curso.nombre=="echarri"

    }

    void "Recomendar Materia"(){
        when:
            setup()
        then:
            materia1.obtenerRecomendacionesSegunAlumno(alumno2,curso1)==[]


    }

}
