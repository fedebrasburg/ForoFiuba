package forofiuba

class CursoCompartido implements  Comparable {
    String cursoNombre
    Cuatrimestre cuatrimestre

    int compareTo(Object o) {
        CursoCompartido cursoCompartido=(CursoCompartido)o
        if(cursoNombre!=cursoCompartido.cursoNombre){
            return  cursoNombre.compareTo(cursoCompartido.cursoNombre)
        }else {
            return  cuatrimestre.compareTo(cursoCompartido.cuatrimestre)
        }

    }
}