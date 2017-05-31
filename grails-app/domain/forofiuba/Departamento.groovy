package forofiuba

class Departamento {
    String nombre;
    Facultad facultad;
    static belongsTo = Facultad;
    static hasMany = [materias: Materia]
    String email;
    String telefono;
    static constraints = {
        nombre nullable: false, blank: false
        facultad nullable: false
        email email: true, nullable: true
        telefono nullable: true
    }
}
