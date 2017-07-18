package forofiuba

import grails.plugin.springsecurity.annotation.Secured

class HomeController {

    def springSecurityService
    def armadorDeHilo

    def index() {
        departamentos()
    }

    def departamentos() {
        List<Carrera> carreras = Carrera.getAll()
        List<Alumno> alumnos= Alumno.getTopKarmaAlumnos();
        render("view": "departamentos", "model": [Carreras: Carrera.diccionarioMateriasPorCarrera(carreras), Departamentos: Departamento.getAll(), alumnosKarma:alumnos])
    }

    def materias() {
        Alumno alumno = springSecurityService.currentUser
        render("view": "materias", "model": [Materias: Materia.getMaterias(Departamento.get(params.departamentoId)), hilo: armadorDeHilo.calcularHiloMaterias(params.departamentoId), carreras: Carrera.findAll(), alumnoActual: alumno])
    }

    def login() {
        redirect(controller: 'login', action: 'departamentos')
    }

    def perfilAlumno() {
        redirect(controller: 'perfil', action: 'index', params: ["alumnoid": "${params.alumnoid}"])
    }

    def registrar() {
        redirect(controller: 'registro', action: 'departamentos')
    }


    def catedras() {
        def carreras = Materia.get(params.materiaId).carreras
        render("view": "catedras", "model": [Catedras: Catedra.getCatedras(Materia.get(params.materiaId)), hilo: armadorDeHilo.calcularHiloCatedras(params.materiaId), correlativas: Materia.get(params.materiaId).correlativas, carreras: Materia.get(params.materiaId).carreras])
    }


    def cursos() {
        render("view": "cursos", "model": [Cursos: Curso.getCursos(Catedra.get(params.catedraId)), hilo: armadorDeHilo.calcularHiloCursos(params.catedraId)])
    }


    CreateOpinionCommand createOpinionCommand = new CreateOpinionCommand()

    def opiniones() {
        Alumno alumno = springSecurityService.currentUser
        Curso curso = Curso.get(params.cursoId)
        def materiasFaltantes = []
        List<Parecido> materiasRecomendades;
        EstadoAlumnoCurso.EstadoEnum estadoDeMateria;
        if (alumno) {
            estadoDeMateria = curso.catedra.materia.estadoAlumno(alumno)
            if (estadoDeMateria == EstadoAlumnoCurso.EstadoEnum.FALTANCORRELATIVAS) {
                materiasFaltantes = alumno.materiasFaltantes(curso)
            }
            materiasRecomendades = Materia.obtenerRecomendacionesSegunAlumno(alumno, curso)
        }
        render("view": "opiniones", "model": [estadoDeMateria: estadoDeMateria, textoDefault: createOpinionCommand, materiasFaltantes: materiasFaltantes, Opiniones: Opinion.getOpinionesByCurso(curso), hilo: armadorDeHilo.calcularHiloOpiniones(params.cursoId), materiasParecidas: materiasRecomendades, alumnoActual: alumno])
        createOpinionCommand = new CreateOpinionCommand()
    }

    def meSirvioLaOpinion(){
        Alumno alumno= springSecurityService.currentUser
        Opinion opinion=Opinion.get(params.opinion)

        if(!CalificacionOpinion.calificoOpinion(alumno,opinion)&&(opinion.alumno!=alumno)){
            CalificacionOpinion.createCalificacionOpinion(alumno,opinion,Boolean.valueOf(params.meSirvioLaOpinion))
        }
        redirect(action: "opiniones",params: [cursoId:opinion.curso.id])

    }

    def busqueda() {
        [Parecidos: Materia.obtenerMateriasSegunNombre(Curso.getAll(), params.nombre)]
    }

    @Secured(['ROLE_USER'])
    def createOpinion() {
        Alumno alumno = springSecurityService.currentUser
        if(EstadoAlumnoCurso.estadoAlumnoCurso(alumno,Curso.findById(params.cursoId).catedra.materia)!=EstadoAlumnoCurso.EstadoEnum.CURSADA){
            CreateOpinionCommand com = new CreateOpinionCommand(params.horarios, params.opinionTp, params.opinionParcial, params.opinionFinal, params.opinionTeorica, params.opinionProfesores, params.opinionPractica, params.modalidad, params.profesores, params.puntuacion.toInteger(), params.cuatrimestre, params.year)
            if (com.validate()) {
                Opinion.createOpinion(params.cursoId, alumno, params.horarios, params.opinionTp, params.opinionParcial, params.opinionFinal, params.opinionTeorica, params.opinionProfesores, params.opinionPractica, params.modalidad, params.profesores, params.puntuacion.toInteger(), new Date(), params.year, params.cuatrimestre)
            } else {
                println("En create" + com.puntuacion.toString())
                createOpinionCommand = com
            }
        }
        opiniones()
    }

    @Secured(['ROLE_ADMIN'])
    def createMateria() {
        Materia.createMateria(params.materiaNombre, params.materiaDescripcion, params.departamentoId, params.list("carrerasNombre"))
        materias()
    }

    @Secured(['ROLE_ADMIN'])
    def createCatedra() {
        Catedra.createCatedra(params.catedraNombre, params.catedraEmail, params.materiaId)
        catedras()
    }

    @Secured(['ROLE_ADMIN'])
    def createCurso() {
        Curso.createCurso(params.cursoNombre, params.cursoEmail, params.catedraId)
        cursos()
    }

    @Secured(['ROLE_ADMIN'])
    def createDepartamento() {
        Departamento.createDepartamento(params.departamentoNombre, params.departamentoEmail, params.departamentoTelefono)
        departamentos()
    }

    @Secured(['ROLE_ADMIN'])
    def deleteMateria() {
        def rta = Materia.deleteMateria(params.materiaId)
        if (!rta) {
            println("No lo borre")
        }
        materias()
    }

    @Secured(['ROLE_ADMIN'])
    def deleteCatedra() {
        def rta = Catedra.deleteCatedra(params.catedraId)
        if (!rta) {
            println("No lo borre")
        }
        catedras()
    }

    @Secured(['ROLE_ADMIN'])
    def deleteCurso() {
        def rta = Curso.deleteCurso(params.cursoId)
        if (!rta) {
            println("No lo borre")
        }
        cursos()
    }

    @Secured(['ROLE_ADMIN'])
    def deleteDepartamento() {
        def rta = Departamento.deleteDepartamento(params.departamentoId)
        if (!rta) {
            println("No lo borre")
        }
        departamentos()
    }


}

