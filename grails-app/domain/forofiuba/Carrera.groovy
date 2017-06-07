package forofiuba

class Carrera {
    String nombre;
    static hasMany = [materias: Materia,alumnos: Usuario]
    static belongsTo = [Usuario]
    static constraints = {
        nombre nullable: false,unique: true
        materias nullable: false
    }

}
