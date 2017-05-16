package forofiuba

class Materia {
    String nombre,descripcion;
    Departamento departamento;
    static hasMany = [Catedras: Catedra]
    static constraints = {
    }
}
