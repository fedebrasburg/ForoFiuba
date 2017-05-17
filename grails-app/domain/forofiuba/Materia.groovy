package forofiuba

class Materia {
    String nombre,descripcion;
    static belongsTo = [departamento: Departamento]
    static hasMany = [catedras: Catedra]
    static constraints = {
        nombre nullable: false,blank:false
        descripcion nullable: true
        departamento nullable: false
        catedras nullable: true
    }
}
