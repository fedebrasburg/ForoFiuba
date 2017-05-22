package forofiuba

class Carrera {
    String nombre;
    Facultad  facultad;
    static belongsTo = [Facultad,Usuario]
    static constraints = {
        nombre  nullable: false
        facultad nullable:false ,unique:true
    }
}
