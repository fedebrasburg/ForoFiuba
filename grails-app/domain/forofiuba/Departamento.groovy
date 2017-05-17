package forofiuba

class Departamento {
    String nombre;
    static belongsTo = [facultad: Facultad]
    static hasMany = [materias: Materia]
    String email;
    String telefono;
    static constraints = {
        nombre nullable: false,blank:false
        facultad nullable: false
        materias nullable:  false
        email email:true , nullable: true
        telefono nullable: true
    }
}
