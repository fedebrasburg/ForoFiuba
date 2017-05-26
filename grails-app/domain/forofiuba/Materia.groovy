package forofiuba

class Materia {
    String nombre,descripcion;
    Departamento departamento;
    static belongsTo = Departamento
    static hasMany = [catedras: Catedra]
    static constraints = {
        nombre nullable: false,blank:false
        descripcion nullable: true
        departamento nullable: false
        catedras nullable: true
    }

    def static createMateria(String nombre, String descripcion, String departamentoId){
        def m = new Materia()
        m.nombre = nombre
        m.descripcion = descripcion
        m.departamento = Departamento.get(departamentoId)
        m.save(flush: true, failOnError: true)
    }


}
