package forofiuba

class Curso {
    String nombre,email;
    static belongsTo = [catedra: Catedra]
    static hasMany = [opiniones: Opinion]
    static constraints = {
        catedra nullable:false
        nombre nullable: false,blank:false
        opiniones nullable: true
        email email:true , nullable: true
    }
}
