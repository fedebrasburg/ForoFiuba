package forofiuba

class Curso {
    String nombre,email;
    Catedra catedra;
    static belongsTo = Catedra
    static hasMany = [opiniones: Opinion]
    static constraints = {
        catedra nullable:false
        nombre nullable: false,blank:false
        opiniones nullable: true
        email email:true , nullable: true
    }

    def static createCurso(String nombre, String email, String catedraId){
        def c = new Curso()
        c.nombre = nombre
        c.email = email
        c.catedra = Catedra.get(catedraId)
        c.save(flush: true, failOnError: true)

    }
}
