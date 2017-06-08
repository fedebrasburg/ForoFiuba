package forofiuba

import grails.plugin.springsecurity.annotation.Secured

class HomeController {

    def springSecurityService

    class Hilo {
        String materiaNombre
        String materiaId
        String catedraNombre
        String catedraId
        String cursoNombre
        String cursoId
        String departamentoNombre
        String departamentoId
    }

    def index() {
        render(view: "Departamentos", model: [Departamentos: Departamento.getDepartamentos()])
    }

    def materias() {

        render(view: "Materias", model: [Materias: Materia.getMaterias(params.departamentoId), hilo: calcularHiloMaterias(params.departamentoId),carreras:Carrera.findAll()])
    }

    def login() {
        redirect(controller: 'login', action: 'index')
    }
    def perfilUsuario(){
        redirect(controller: 'perfil',action: 'index',params: ["usuarioId":"${params.usuarioId}"])
    }

    def registrar() {
        redirect(controller: 'registro', action: 'index')
    }

    def catedras() {
        render(view: "Catedras", model: [Catedras: Catedra.getCatedras(params.materiaId), hilo: calcularHiloCatedras(params.materiaId)],carreras: Materia.get(params.materiaId).carreras)
    }


    def cursos() {
        render(view: "Cursos", model: [Cursos: Curso.getCursos(params.catedraId), hilo: calcularHiloCursos(params.catedraId)])
    }

    def opiniones(){
        render(view:"Opiniones", model: [cursoCorrelativas:cursoCorrelativas(springSecurityService.currentUser,params.cursoId),Opiniones: Opinion.getOpinionesByCurso(params.cursoId), hilo:calcularHiloOpiniones(params.cursoId), materiasParecidas:obtenerMateriasParecidas(params.cursoId)])
    }

    def busqueda(){
        render(view:"Busqueda", model: [Parecidos:obtenerMateriasSegunNombre(params.nombre)] )
    }


    def calcularHiloMaterias(String departamentoId) {
        def hilo = new Hilo()
        hilo.departamentoNombre = Departamento.get(departamentoId).nombre
        hilo.departamentoId = departamentoId
        hilo
    }

    def calcularHiloCatedras(String materiaId) {
        def hilo = calcularHiloMaterias(Materia.get(materiaId).departamento.id.toString())
        hilo.materiaId = materiaId
        hilo.materiaNombre = Materia.get(materiaId).nombre
        hilo
    }

    def calcularHiloCursos(String catedraId) {
        def hilo = calcularHiloCatedras(Catedra.get(catedraId).materia.id.toString())
        hilo.catedraId = catedraId
        hilo.catedraNombre = Catedra.get(catedraId).nombre
        hilo
    }

    def calcularHiloOpiniones(String cursoId) {
        def hilo = calcularHiloCursos(Curso.get(cursoId).catedra.id.toString())
        hilo.cursoId = cursoId
        hilo.cursoNombre = Curso.get(cursoId).nombre
        hilo
    }


    @Secured(['ROLE_USER'])
    def createOpinion() {
        Usuario user = springSecurityService.currentUser
        Opinion.createOpinion(params.cursoId, user,params.horarios, params.opinionTp, params.opinionParcial, params.opinionFinal, params.opinionTeorica, params.opinionProfesores, params.opinionPractica, params.modalidad, params.profesores, params.puntuacion, new Date())
        opiniones()
    }

    @Secured(['ROLE_ADMIN'])
    def createMateria() {
        Materia.createMateria(params.materiaNombre, params.materiaDescripcion, params.departamentoId)
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
        index()
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
        index()
    }

    def obtenerMateriasParecidas(String cursoId){
        def usuarios = []
        Opinion.findAllByCurso(Curso.get(cursoId)).each{
            usuarios << it.usuario
        }
        usuarios = usuarios.unique { a, b -> a.id <=> b.id }
        def pare = []
        usuarios.each { usu ->
            Opinion.findAllByUsuario(usu).unique { a, b -> a.curso.id <=> b.curso.id }.each{ op ->
                if(op.curso.id != cursoId) {
                    boolean entro = false
                    pare.each{
                        if (it.cursoId  == op.curso.id){
                            it.contador += 1
                            entro = true
                        }
                    }
                    if (!entro) {
                        def p = new parecido()
                        p.cursoNombre = op.curso.nombre
                        p.materiaNombre = Materia.get(Catedra.get(Curso.get(op.curso.id).catedra.id).materia.id).nombre
                        p.contador = 1
                        p.cursoId = op.curso.id
                        pare << p
                    }
                }
            }
        }
        pare.findAll{it -> it.cursoId != cursoId}.sort{it.contador}
    }

    def obtenerMateriasSegunNombre(String nombre){
        def listaCursos = []
        Curso.getAll().each{curso ->
            if (curso.nombre.toLowerCase().contains(nombre.toLowerCase())){
                def p = new parecido()
                p.cursoNombre = curso.nombre
                p.materiaNombre = Materia.get(Catedra.get(Curso.get(curso.id).catedra.id).materia.id).nombre
                p.cursoId = curso.id
                listaCursos << p
            }
        }
        return listaCursos
    }

    class parecido{
        String cursoNombre
        String cursoId
        String materiaNombre
        Integer contador
    }

    boolean cursoCorrelativas(Usuario user, String cursoId){
        def correlativas = Materia.getCorrelativas(Curso.get(cursoId).catedra.materia.id)
        def cursadas = []
        return correlativas.size() == cursadas.intersect(correlativas).size()
    }
}

