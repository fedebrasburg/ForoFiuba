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
}
