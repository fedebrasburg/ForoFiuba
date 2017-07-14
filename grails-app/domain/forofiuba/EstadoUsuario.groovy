package forofiuba

class EstadoUsuario {

    static constraints = {
    }

    public  static EstadoEnum  estadoUsuario(Usuario usuario,Materia materia){
        if (!materia.materiaPerteneceACarrerasUsuario(usuario)){
            return EstadoEnum.NOESTAENELPLAN;
        }
        if(usuario.opinoSobre(materia)){
            return EstadoEnum.CURSADO
        }
        if(usuario.puedeOpinar(materia)){
            return EstadoEnum.CURSABLE
        }
         return EstadoEnum.FALTANCORRELATIVAS
    }

    public static enum  EstadoEnum{
        NOESTAENELPLAN("No esta en tus planes"),
        CURSADO("Cursada"),
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
