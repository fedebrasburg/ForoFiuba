package forofiuba

class Opinion {
    String opinion;
    static belongsTo = [curso: Curso,usuario:Usuario]

    static constraints = {
        opinion nullable: false,blank: false
        curso nullable: false
        usuario nullable: false
    }

}
