package forofiuba


class Materia {

    String nombre, descripcion;
    Departamento departamento;
    static belongsTo = [Departamento, Carrera]
    static hasMany = [catedras: Catedra, carreras: Carrera, correlativas: Materia]
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
        m.carreras = Carrera.findAllByNombreInList(carreras)
        m.save(flush: true, failOnError: true)
        m.carreras.each { Carrera carrera ->
            carrera.materias.add(m)
            carrera.save(flush: true)
        }
    }

    static boolean deleteMateria(String materiaId) {
        if (!Catedra.findAllByMateria(get(materiaId)).isEmpty()) {
            return false
        }
        def m = get(materiaId)
        m.delete(flush: true, failOnError: true)
        true
    }


    def static obtenerMateriasSegunNombre(List<Curso> cursos, String nombre) {
        cursos.findAll { curso ->
            curso.nombre.toLowerCase().contains(nombre.toLowerCase())
        }.collect { curso ->
            def parecido = new Parecido()
            parecido.curso = curso
            parecido.materia = curso.catedra.materia

            parecido
        }
    }


    public boolean materiaPerteneceACarrerasAlumno(Alumno alumno) {
        alumno.carreras.collect { carrera ->
            carrera.materias
        }.flatten().unique().any { Materia materia ->
            if(materia.id){
                return materia.id == this.id
            }
            return materia==this
        }
    }


    public EstadoAlumnoCurso.EstadoEnum estadoAlumno(Alumno alumno) {
        EstadoAlumnoCurso.EstadoEnum estado = EstadoAlumnoCurso.estadoAlumnoCurso(alumno, this);
        return estado
    }

    def static obtenerRecomendacionesSegunAlumno(Alumno alumno, Curso curso) {
        Recomendador.obtenerRecomendacionesSegunAlumno(alumno,curso)
    }

    def static getMaterias(Departamento departamento) {
        departamento.materias
    }
}
