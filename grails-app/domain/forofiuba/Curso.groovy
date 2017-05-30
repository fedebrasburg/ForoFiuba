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

    static boolean deleteCurso(String cursoId){
        if (!Opinion.findAllByCurso(Curso.get(cursoId)).isEmpty()){
            return false
        }
        def c = Curso.get(cursoId)
        c.delete(flush: true,failOnError: true)
        true
    }
}
