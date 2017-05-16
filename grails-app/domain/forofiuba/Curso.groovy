package forofiuba

class Curso {
    String nombre;
    Catedra catedra;
    static hasMany = [Opiniones: Opinion]
    static constraints = {
    }
}
