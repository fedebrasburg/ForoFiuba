package forofiuba

class Departamento {
    String nombre;
    static hasMany = [materias: Materia]
    String email;
    String telefono;
    static constraints = {
        nombre nullable: false, blank: false
        email email: true, nullable: true
        telefono nullable: true
    }


    def static getDepartamentos() {
        Departamento.getAll()
    }

    static boolean deleteDepartamento(String departamentoId) {
        if (!Materia.findAllByDepartamento(Departamento.get(departamentoId)).isEmpty()) {
            return false
        }
        def d = Departamento.get(departamentoId)
        d.delete(flush: true, failOnError: true)
        true
    }

    def static createDepartamento(String nombre, String email, String telefono){
        def d = new Departamento()
        d.nombre = nombre
        d.email = email
        d.telefono = telefono
        d.save(flush: true, failOnError: true)
    }
}
