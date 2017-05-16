package forofiuba

class Catedra {
    String nombre;
    Materia materia;
    static hasMany = [Cursos: Curso]

    static constraints = {
    }
}
