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




    def static obtenerMateriasSegunNombre(List<Curso> cursos,String nombre) {
        cursos.findAll { curso ->
            curso.nombre.toLowerCase().contains(nombre.toLowerCase())
        }.collect { curso ->
            def parecido = new Parecido()
            parecido.cursoNombre = curso.nombre
            parecido.materiaNombre = curso.catedra.materia.nombre
            parecido.cursoId = curso.id

            parecido
        }
    }



    public boolean materiaPerteneceACarrerasUsuario(Usuario usuario){
        usuario.carreras.collect{carrera->
            carrera.materias
        }.flatten().unique().any{Materia materia->
            materia.id == this.id
        }
    }


    public EstadoUsuario.EstadoEnum estadoUsuario(Usuario usuario){
        EstadoUsuario.EstadoEnum estado=EstadoUsuario.estadoUsuario(usuario,this);
        return estado
    }

    def static obtenerMateriasParecidas(Curso curso){
        curso.opiniones.collect{opinion->
            opinion.usuario
        }.unique { a, b -> a.id <=> b.id
        }.collect{usu->
            usu.opiniones
        }.flatten().findAll{ opinion ->
            opinion.curso.id != curso.id
        }.collect{Opinion op->
            Parecido parecido = new Parecido()
            parecido.cursoNombre = op.curso.nombre
            parecido.materiaNombre = op.getMateria().nombre
            parecido.materia = op.getMateria()
            parecido.cursoId = op.curso.id
            parecido
        }.unique{a,b -> a.cursoId <=> b.cursoId}
    }
    def static  obtenerMateriasParecidasNoCursadasPorUsuario(Curso curso,Usuario usuario){
        List<Parecido> parecidas= obtenerMateriasParecidas(curso)
        parecidas.findAll{Parecido parecida->
            (EstadoUsuario.estadoUsuario(usuario,parecida.materia)!=EstadoUsuario.EstadoEnum.CURSADO)

        }
    }
    def static getMaterias(Departamento departamento){
        departamento.materias
    }
}
