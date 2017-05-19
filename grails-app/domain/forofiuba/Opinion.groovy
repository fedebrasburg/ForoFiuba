package forofiuba

class Opinion {
    String opinion;
    Curso curso;
    Usuario usuario;
    static belongsTo = [Curso,Usuario]

    static constraints = {
        opinion nullable: false,blank: false
        curso nullable: false
        usuario nullable: false
    }

}
