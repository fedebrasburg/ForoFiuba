package forofiuba

class Carrera {
    String nombre;
    static belongsTo = [facultad: Facultad]
    static hasMany = [usuarios: Usuario]
    static constraints = {
        nombre  nullable: false
        usuarios nullable: true
        facultad nullable:false ,unique:true
    }
}
