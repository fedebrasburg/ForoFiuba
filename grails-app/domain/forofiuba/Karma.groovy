package forofiuba

class Karma {
    final static double MIN_CONFIABLES=1.9;
    final static double MAX_CONFIABLES=4.1;
    final static int VALOR_CANTIDAD_DE_OPINIONES=3,VALOR_CALIFICACION_DE_SUS_OPINIONES=2,VALOR_CANTIDAD_CALIFICACIONES=1,VALOR_CONFIABILIDAD=10,VALOR_REGULARIDAD=2;
    Usuario usuario
    static belongsTo = Usuario
    static constraints = {
    }

    private int getCantidadCalificaciones(){
        if(!usuario.calificaciones)return 0
        return  usuario.calificaciones.size()
    }
    private int getKarmaCantidadCalificaciones(){
        return VALOR_CANTIDAD_CALIFICACIONES*getCantidadCalificaciones()
    }
    private int getCantidadOpiniones(){
        if(!usuario.opiniones)return 0
        return  usuario.opiniones.size()
    }
    private int getKarmaCantidadOpiniones(){
        return VALOR_CANTIDAD_DE_OPINIONES*getCantidadOpiniones()
    }
    private  double getPromedioOpiniones(){
        if(!usuario.opiniones){return 0}
        return usuario.opiniones.sum{Opinion opinion->opinion.puntuacion}/getCantidadOpiniones()
    }
    private boolean esConfiable(){
        return ((MIN_CONFIABLES<getPromedioOpiniones())&&(getPromedioOpiniones()<MAX_CONFIABLES))
    }
    private int getKarmaConfiable(){
        return (esConfiable()?1:-1)*VALOR_CONFIABILIDAD
    }
    private int getCantidadCalificacionesOpinionesPositivas(){
        if(!usuario.opiniones){return 0}
        return usuario.opiniones.sum{it.getCalificacionesPositivas()}
    }
    private int getCantidadCalificacionesOpinionesNegativas(){
        if(!usuario.opiniones){return 0}
        return usuario.opiniones.sum{ it.getCalificacionesNegativas()}
    }
    private int getKarmaCalificacionOpiniones(){
        return VALOR_CALIFICACION_DE_SUS_OPINIONES*(getCantidadCalificacionesOpinionesPositivas()-getCantidadCalificacionesOpinionesNegativas())

    }
    private int getRegularidad(){
        if(!usuario.opiniones){return 0}
        Cuatrimestre cuatrimestre=usuario.opiniones.min {Opinion opinion->opinion.cuatrimestre}.cuatrimestre
        Cuatrimestre cuatrimestreActual=Cuatrimestre.getCuatrimestreActual()
        int cantidadDeCuatrimestres=cuatrimestre.distancia(cuatrimestreActual);
        return getCantidadOpiniones()/cantidadDeCuatrimestres;
    }
    private getKarmaRegularidad(){
        return getRegularidad()*VALOR_REGULARIDAD
    }
    public int calcularKarma(){
        int karmaCalculado=  (getKarmaCalificacionOpiniones()+getKarmaCantidadCalificaciones()+getKarmaCantidadOpiniones()+getKarmaConfiable()+getKarmaRegularidad());
        if(karmaCalculado<=-1) {
            karmaCalculado=0
        }
        return karmaCalculado
    }
}
