package forofiuba

class Catedra {
    String nombre,email;
    Materia materia;
    static belongsTo = Materia
    static hasMany = [cursos: Curso]

    static constraints = {
        nombre nullable: false,blank:false
        materia nullable: false
        email email:true , nullable: true

    }

    def static createCatedra(String nombre, String email, String materiaId){
        def c = new Catedra()
        c.nombre = nombre
        c.email = email
        c.materia = Materia.get(materiaId)
        c.save(flush: true, failOnError: true)
    }
}
