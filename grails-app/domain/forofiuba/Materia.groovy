package forofiuba

class Materia {
    String nombre, descripcion;
    Departamento departamento;
    static belongsTo = [Departamento,Carrera]
    static hasMany = [catedras: Catedra,carreras: Carrera, correlativas: Materia]
    static constraints = {
        nombre nullable: false, blank: false
        descripcion nullable: true
        departamento nullable: false
        catedras nullable: true
        carreras nullable: true
    }

    def static createMateria(String nombre, String descripcion, String departamentoId, List<String> carreras) {
        def m = new Materia()
        m.nombre = nombre
        m.descripcion = descripcion
        m.departamento = Departamento.get(departamentoId)
        m.carreras=Carrera.findAllByNombreInList(carreras)
        m.save(flush: true, failOnError: true)
        m.carreras.each{ Carrera carrera->
            carrera.materias.add(m)
            carrera.save(flush: true)
        }
    }

    static boolean deleteMateria(String materiaId) {
        if (!Catedra.findAllByMateria(Materia.get(materiaId)).isEmpty()) {
            return false
        }
        def m = Materia.get(materiaId)
        m.delete(flush: true, failOnError: true)
        true
    }

    def static getMaterias(String departamentoId) {
        Materia.findAllByDepartamento(Departamento.get(departamentoId))
    }

    def static getCorrelativas(Long materiaId){
        Materia.get(materiaId).correlativas
    }

    def static obtenerMateriasSegunNombre(String nombre) {
        Curso.getAll().findAll { curso ->
            curso.nombre.toLowerCase().contains(nombre.toLowerCase())
        }.collect { curso ->
            def parecido = new Parecido()
            parecido.cursoNombre = curso.nombre
            parecido.materiaNombre = Materia.get(Catedra.get(Curso.get(curso.id).catedra.id).materia.id).nombre
            parecido.cursoId = curso.id
            parecido
        }
    }

    def obtenerMateriasParecidas(String cursoId){
        Opinion.findAllByCurso(Curso.get(cursoId)).collect{opinion->
            opinion.usuario
        }.unique { a, b -> a.id <=> b.id
        }.collect{usu->
            Opinion.findAllByUsuario(usu)
        }.flatten().collect{ op->
            def parecido = new Parecido()
            parecido.cursoNombre = op.curso.nombre
            parecido.materiaNombre = op.getMateria().nombre
            parecido.cursoId = op.curso.id
            parecido
        }.findAll{pare ->
            (pare.cursoId != cursoId )
        }.unique{a,b -> a.cursoId <=> b.cursoId}
    }

}
