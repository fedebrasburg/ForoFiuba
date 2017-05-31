package forofiuba

class Facultad {
    String nombre, descripcion, email, telefonos, direcciones;
    static hasMany = [deṕartamentos: Departamento, carreras: Carrera, direcciones: String, telefonos: String]
    static constraints = {
        nombre nullable: false, blank: false, unique: true
        telefonos nullable: false, blank: false
        direcciones nullable: false, blank: false
        descripcion nullable: true
        email email: true, nullable: false, unique: true

    }
}
