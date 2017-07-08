package forofiuba

class Cuatrimestre implements Comparable  {
    int cuatrimestre
    int anio

    static constraints = {
        anio nullable:false, range: 1960..2100, blank: false
        cuatrimestre nullable:false, range: 1..2, blank: false
    }

    int compareTo(Object o) {

        Cuatrimestre otroCuatrimestre=(Cuatrimestre)o
        if(otroCuatrimestre.anio!=anio){
            return ((int)anio).compareTo((int)otroCuatrimestre.anio)
        }else {
            return ((int)cuatrimestre).compareTo((int)otroCuatrimestre.cuatrimestre)
        }
    }
    static Cuatrimestre getCuatrimestreActual(){
        Cuatrimestre cuatrimestre=new Cuatrimestre();
        cuatrimestre.anio=Calendar.getInstance().get(Calendar.YEAR);
        cuatrimestre.cuatrimestre= (Calendar.getInstance().get(Calendar.MONTH)<8) ? 1:2
        return cuatrimestre
    }
    int distancia(Cuatrimestre otroCuatrimestre){
        int distancia=0
        distancia=Math.abs(this.anio-otroCuatrimestre.anio+1)
        distancia+=Math.abs(this.cuatrimestre-otroCuatrimestre.cuatrimestre)
        return distancia
    }
}
