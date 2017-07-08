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


    public boolean materiaPerteneceACarrerasUsuario(Usuario usuario) {
        usuario.carreras.collect { carrera ->
            carrera.materias
        }.flatten().unique().any { Materia materia ->
            materia.id == this.id
        }
    }


    public EstadoUsuario.EstadoEnum estadoUsuario(Usuario usuario) {
        EstadoUsuario.EstadoEnum estado = EstadoUsuario.estadoUsuario(usuario, this);
        return estado
    }

    def static obtenerMateriasParecidas(Curso curso, Usuario usuario) {
        def listaFinal = []
        def cursosId = []
        curso.opiniones.collect { opinion ->
            // Todos los usuario que dejaron opinion en el curso actual
            opinion.usuario
        }.unique { a, b -> a.id <=> b.id
        }.collect { usu ->
            // Todas las opiniones de esos usuarios
            usu.opiniones
        }.flatten().findAll { opinion ->
            // Saco las opiniones del curso actual
            opinion.curso.id != curso.id
        }.collect { Opinion op ->
            Parecido parecido = new Parecido()
            parecido.curso = op.curso
            parecido.materia = op.getMateria()
            //Si la puntuacion fue mala resta, si no suma.
            if (op.puntuacion <= Constantes.MALA_PUNTUACION) {
                parecido.puntaje = -Constantes.PUNTAJE_INICIAL
            } else {
                parecido.puntaje = Constantes.PUNTAJE_INICIAL
            }
            //Si el opinador es de la misma carrera, multiplica por X
            if (!Collections.disjoint(usuario.carreras, op.usuario.carreras)) {
                parecido.puntaje *= Constantes.MISMA_CARRERA
            }
            //Si el opinador ya curso otra materia con el usuario
            if (cantidadDeMateriasCursadasIguales(usuario, op.usuario) > 1) {
                parecido.puntaje *= Constantes.CURSO_OTRA
            }
            parecido
        }.each{Parecido parecido ->
            if(!(parecido.curso.id in cursosId)){
                cursosId << parecido.curso.id
                listaFinal << parecido
            }else{
                listaFinal.find{Parecido parecidoEnLista ->
                    parecidoEnLista.curso.id = parecido.curso.id
                }.punraje += parecido.puntaje
            }
        }
        listaFinal

    }

    def static obtenerMateriasParecidasCursablesPorUsuario(Curso curso, Usuario usuario) {
        List<Parecido> parecidas = obtenerMateriasParecidas(curso, usuario)
        parecidas.findAll { Parecido parecida ->
            //Cursable = Correlativas + pertene a la carrera + no la curso
            (EstadoUsuario.estadoUsuario(usuario, parecida.materia) == EstadoUsuario.EstadoEnum.CURSABLE)

        }
    }

    def static cantidadDeMateriasCursadasIguales(Usuario usuarioA, Usuario usuarioB) {
        List<Curso> listaCursosB = usuarioB.opiniones.collect { Opinion opinion ->
            //cursos del B
            opinion.curso
        }
        List<Curso> listaCursosA = usuarioA.opiniones.collect { Opinion opinion ->
            //cursos del B
            opinion.curso
        }
        return listaCursosA.intersect(listaCursosB).size()

    }

    def static asignarCategoriaParecido(Parecido parecido) {
        if (parecido.puntaje > Constantes.PISO_RECOMENDABLE) {
            parecido.categoria = Constantes.RECOMENDABLE
        } else if (Constantes.PISO_RECOMENDABLE >= parecido.puntaje && parecido.puntaje >= Constantes.PISO_PROBABLE) {
            parecido.categoria = Constantes.PROBABLE
        } else {
            parecido.categoria = Constantes.NO_PROBABLE
        }
        parecido
    }

    def static obtenerRecomendacionesSegunUsuario(Usuario usuario, Curso curso) {
        obtenerMateriasParecidasCursablesPorUsuario(curso, usuario).collect { Parecido parecido ->
            asignarCategoriaParecido(parecido)
        }
    }

    def static getMaterias(Departamento departamento) {
        departamento.materias
    }
}
