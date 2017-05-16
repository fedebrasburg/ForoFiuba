package forofiuba

class Carrera {
    String nombre;
    Facultad facultad;
    static hasMany = [Usuarios: Usuario]
    static constraints = {
    }
}
