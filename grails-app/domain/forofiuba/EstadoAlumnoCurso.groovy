package forofiuba

class EstadoAlumnoCurso {

    static constraints = {
    }

    public  static EstadoEnum estadoAlumnoCurso(Alumno alumno, Materia materia){
        if (!materia.materiaPerteneceACarrerasAlumno(alumno)){
            return EstadoEnum.NOESTAENELPLAN;
        }
        if(alumno.opinoSobre(materia)){
            return EstadoEnum.CURSADA
        }
        if(alumno.puedeOpinar(materia)){
            return EstadoEnum.CURSABLE
        }
         return EstadoEnum.FALTANCORRELATIVAS
    }

    public static enum  EstadoEnum{
        NOESTAENELPLAN("No esta en tus planes"),
        CURSADA("Cursada"),
        CURSABLE("Puedo cursar"),
        FALTANCORRELATIVAS("Faltan correlativas");
        private final String estadoMateria;

        private EstadoEnum(String estado) {
            estadoMateria = estado;
        }

        public String toString() {
            return this.estadoMateria;
        }
    }
}
