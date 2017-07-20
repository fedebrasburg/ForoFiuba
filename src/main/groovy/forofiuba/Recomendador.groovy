package forofiuba

/**
 * Created by andres on 7/20/17.
 */
@Singleton
class Recomendador {
    def private static obtenerMateriasParecidas(Curso curso, Alumno alumno) {
        def listaFinal = []
        def cursosId = []
        curso.opiniones.collect { opinion ->
            // Todos los alumno que dejaron opinion en el curso actual
            opinion.alumno
        }.unique { a, b -> a.username <=> b.username
        }.collect { usu ->
            // Todas las opiniones de esos alumnos
            usu.opiniones
        }.flatten().findAll { opinion ->
            // Saco las opiniones del curso actual
            opinion.curso != curso
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
            if (!Collections.disjoint(alumno.carreras, op.alumno.carreras)) {
                parecido.puntaje *= Constantes.MISMA_CARRERA
            }
            //Si el opinador ya curso otra materia con el alumno
            if (cantidadDeMateriasCursadasIguales(alumno, op.alumno) >= 1) {
                parecido.puntaje *= Constantes.CURSO_OTRA
            }
            //Si la opinion tiene muchos dislikes vale menos y si es mas valorado suma mas
            Integer diferencia = op.calificacionesPositivas - op.calificacionesNegativas
            def factores = diferencia / Constantes.FACTOR_DIFERENCIA
            if (factores > 1) {
                if (diferencia < 0) {
                    parecido.puntaje *= factores*Constantes.FACTOR_NEGATIVO
                }else{
                    parecido.puntaje *= factores*Constantes.FACTOR_POSITIVO
                }
            }
            parecido
        }.each { Parecido parecido ->
            if (!(parecido.curso.nombre in cursosId)) {
                cursosId << parecido.curso.nombre
                listaFinal << parecido
            } else {
                listaFinal.find { Parecido parecidoEnLista ->
                    parecidoEnLista.curso.nombre = parecido.curso.nombre
                }.puntaje += parecido.puntaje
            }
        }
        listaFinal

    }

    def private static obtenerMateriasParecidasCursablesPorAlumno(Curso curso, Alumno alumno) {
        List<Parecido> parecidas = obtenerMateriasParecidas(curso, alumno)
        parecidas.findAll { Parecido parecida ->
            //Cursable = Correlativas + pertene a la carrera + no la curso
            (EstadoAlumnoCurso.estadoAlumnoCurso(alumno, parecida.materia) == EstadoAlumnoCurso.EstadoEnum.CURSABLE)

        }
    }

    def private static cantidadDeMateriasCursadasIguales(Alumno alumnoA, Alumno alumnoB) {
        List<Curso> listaCursosB = alumnoB.opiniones.collect { Opinion opinion ->
            //cursos del B
            opinion.curso.nombre
        }
        List<Curso> listaCursosA = alumnoA.opiniones.collect { Opinion opinion ->
            //cursos del A
            opinion.curso.nombre
        }
        return listaCursosA.intersect(listaCursosB).size()

    }

    def private static asignarCategoriaParecido(Parecido parecido) {
        if (parecido.puntaje > Constantes.PISO_RECOMENDABLE) {
            parecido.categoria = Constantes.RECOMENDABLE
        } else if (Constantes.PISO_RECOMENDABLE >= parecido.puntaje && parecido.puntaje >= Constantes.PISO_PROBABLE) {
            parecido.categoria = Constantes.PROBABLE
        } else {
            parecido.categoria = Constantes.NO_PROBABLE
        }
        parecido
    }

    def static obtenerRecomendacionesSegunAlumno(Alumno alumno, Curso curso) {
        obtenerMateriasParecidasCursablesPorAlumno(curso, alumno).collect { Parecido parecido ->
            asignarCategoriaParecido(parecido)
        }
    }
}
