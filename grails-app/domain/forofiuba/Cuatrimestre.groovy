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
}
