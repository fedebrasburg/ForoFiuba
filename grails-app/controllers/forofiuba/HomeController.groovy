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
        render("view": "departamentos", "model": [Carreras: Carrera.diccionarioMateriasPorCarrera(carreras), Departamentos: Departamento.getAll()])
    }

    def materias() {
        Usuario usuario = springSecurityService.currentUser
        render("view": "materias", "model": [Materias: Materia.getMaterias(Departamento.get(params.departamentoId)), hilo: armadorDeHilo.calcularHiloMaterias(params.departamentoId), carreras: Carrera.findAll(), usuarioActual: usuario])
    }

    def login() {
        redirect(controller: 'login', action: 'departamentos')
    }

    def perfilUsuario() {
        redirect(controller: 'perfil', action: 'index', params: ["usuarioId": "${params.usuarioId}"])
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
        Usuario usuario = springSecurityService.currentUser
        Curso curso = Curso.get(params.cursoId)
        def materiasFaltantes = []
        List<Parecido> materiasRecomendades;
        EstadoUsuario.EstadoEnum estadoDeMateria;
        if (usuario) {
            estadoDeMateria = curso.catedra.materia.estadoUsuario(usuario)
            if (estadoDeMateria == EstadoUsuario.EstadoEnum.FALTANCORRELATIVAS) {
                materiasFaltantes = usuario.materiasFaltantes(curso)
            }
            materiasRecomendades = Materia.obtenerRecomendacionesSegunUsuario(usuario, curso)
        }
        render("view": "opiniones", "model": [estadoDeMateria: estadoDeMateria, textoDefault: createOpinionCommand, materiasFaltantes: materiasFaltantes, Opiniones: Opinion.getOpinionesByCurso(curso), hilo: armadorDeHilo.calcularHiloOpiniones(params.cursoId), materiasParecidas: materiasRecomendades, "usuarioActual": usuario])
        createOpinionCommand = new CreateOpinionCommand()
    }

    def busqueda() {
        [Parecidos: Materia.obtenerMateriasSegunNombre(Curso.getAll(), params.nombre)]
    }

    @Secured(['ROLE_USER'])
    def createOpinion() {
        Usuario user = springSecurityService.currentUser
        CreateOpinionCommand com = new CreateOpinionCommand(params.horarios, params.opinionTp, params.opinionParcial, params.opinionFinal, params.opinionTeorica, params.opinionProfesores, params.opinionPractica, params.modalidad, params.profesores, params.puntuacion.toInteger(), params.cuatrimestre, params.year)
        if (com.validate()) {
            Opinion.createOpinion(params.cursoId, user, params.horarios, params.opinionTp, params.opinionParcial, params.opinionFinal, params.opinionTeorica, params.opinionProfesores, params.opinionPractica, params.modalidad, params.profesores, params.puntuacion.toInteger(), new Date(), params.year, params.cuatrimestre)
        } else {
            println("En create" + com.puntuacion.toString())
            createOpinionCommand = com
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

