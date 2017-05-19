package forofiuba

class Carrera {
    String nombre;
    Facultad  facultad;
    static belongsTo = [Facultad,Usuario]
    static hasMany = [usuarios: Usuario]
    static constraints = {
        nombre  nullable: false
        usuarios nullable: true
        facultad nullable:false ,unique:true
    }
}
