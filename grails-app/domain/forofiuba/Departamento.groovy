package forofiuba

class Departamento {
    String nombre;
    Facultad facultad;
    static belongsTo = Facultad;
    static hasMany = [materias: Materia]
    String email;
    String telefono;
    static constraints = {
        nombre nullable: false, blank: false
        facultad nullable: false
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

    def static createDepartamento(String nombre, Long facultadId, String email, String telefono){
        def d = new Departamento()
        d.nombre = nombre
        d.facultad = Facultad.get(facultadId)
        d.email = email
        d.telefono = telefono
        d.save(flush: true, failOnError: true)
    }
}
