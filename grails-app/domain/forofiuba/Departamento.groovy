package forofiuba

class Departamento {
    String nombre;
    Facultad facultad;
    static hasMany = [Materias: Materia]
    static constraints = {
    }
}
