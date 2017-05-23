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

    def static createOpinion(String cursoId, String usuarioId, String opinion){
        def o = new Opinion()
        o.opinion = opinion
        o.curso = Curso.get(cursoId)
        o.usuario = Usuario.get(usuarioId)
        o.save()
    }

}
