package forofiuba

class Facultad {
    String nombre,telfono,direccion,descripcion;
    static hasMany = [Departamentos: Departamento]

    static constraints = {
    }
}
