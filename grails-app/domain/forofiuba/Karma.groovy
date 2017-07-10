package forofiuba

class Karma {
    final static double MIN_CONFIABLES=2.2;
    final static double MAX_CONFIABLES=3.8;
    final static int VALOR_CANTIDAD_DE_OPINIONES=3,VALOR_CALIFICACION_DE_SUS_OPINIONES=2,VALOR_CANTIDAD_CALIFICACIONES=1,VALOR_CONFIABILIDAD=10,VALOR_REGULARIDAD=2;
    Usuario usuario
    static belongsTo = Usuario
    static constraints = {
    }
    
    public int getCantidadCalificaciones(){
        return  usuario.calificaciones.size()
    }
    public int getKarmaCantidadCalificaciones(){
        return VALOR_CANTIDAD_CALIFICACIONES*getCantidadCalificaciones()
    }
    public int getCantidadOpiniones(){
        return  usuario.opiniones.size()
    }
    public int getKarmaCantidadOpiniones(){
        return VALOR_CANTIDAD_DE_OPINIONES*getCantidadOpiniones()
    }
    public  double getPromedioOpiniones(){
        if(!usuario.opiniones){return 0}
        return usuario.opiniones.sum{Opinion opinion->opinion.puntuacion}/getCantidadOpiniones()
    }
    public boolean esConfiable(){
        return ((MIN_CONFIABLES<getPromedioOpiniones())&&(getPromedioOpiniones()<MAX_CONFIABLES))
    }
    public int getKarmaConfiable(){
        return (esConfiable()?1:-1)*VALOR_CONFIABILIDAD
    }
    public int getCantidadCalificacionesOpinionesPositivas(){
        if(!usuario.opiniones){return 0}
        return usuario.opiniones.sum{it.getCalificacionesPositivas()}
    }
    public int getCantidadCalificacionesOpinionesNegativas(){
        if(!usuario.opiniones){return 0}
        return usuario.opiniones.sum{ it.getCalificacionesNegativas()}
    }
    public int getKarmaCalificacionOpiniones(){
        return VALOR_CALIFICACION_DE_SUS_OPINIONES*(getCantidadCalificacionesOpinionesPositivas()-getCantidadCalificacionesOpinionesNegativas())

    }
    public int getRegularidad(){
        if(!usuario.opiniones){return 0}
        Cuatrimestre cuatrimestre=usuario.opiniones.min {Opinion opinion->opinion.cuatrimestre}.cuatrimestre
        Cuatrimestre cuatrimestreActual=Cuatrimestre.getCuatrimestreActual()
        int cantidadDeCuatrimestres=cuatrimestre.distancia(cuatrimestreActual);
        return getCantidadOpiniones()/cantidadDeCuatrimestres;
    }
    public getKarmaRegularidad(){
        return getRegularidad()*VALOR_REGULARIDAD
    }
    public int calcularKarma(){
        int karma=  (getKarmaCalificacionOpiniones()+getKarmaCantidadCalificaciones()+getKarmaCantidadOpiniones()+getKarmaConfiable()+getKarmaRegularidad());
        if(karma<0){
            return 0
        }
        return karma
    }
}
