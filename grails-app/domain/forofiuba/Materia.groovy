package forofiuba

class Materia {
    String nombre, descripcion;
    Departamento departamento;
    static belongsTo = [Departamento,Carrera]
    static hasMany = [catedras: Catedra,carreras: Carrera]
    static constraints = {
        nombre nullable: false, blank: false
        descripcion nullable: true
        departamento nullable: false
        catedras nullable: true
        carreras nullable: true
    }

    def static createMateria(String nombre, String descripcion, String departamentoId, List<String> carreras) {
        def m = new Materia()
        m.nombre = nombre
        m.descripcion = descripcion
        m.departamento = Departamento.get(departamentoId)
        m.carreras=Carrera.findAllByNombreInList(carreras)
        m.save(flush: true, failOnError: true)
        m.carreras.each{ Carrera carrera->
            carrera.materias.add(m)
            carrera.save(flush: true)
        }
    }

    static boolean deleteMateria(String materiaId) {
        if (!Catedra.findAllByMateria(Materia.get(materiaId)).isEmpty()) {
            return false
        }
        def m = Materia.get(materiaId)
        m.delete(flush: true, failOnError: true)
        true
    }

    def static getMaterias(String departamentoId) {
        Materia.findAllByDepartamento(Departamento.get(departamentoId))
    }


}
