package forofiuba

class Carrera {
    String nombre;
    static hasMany = [materias: Materia,alumnos: Usuario]
    static belongsTo = [Usuario]
    static constraints = {
        nombre nullable: false,unique: true
        materias nullable: false
    }

    def static getAllMateriasPorCarrera(){
        def dic = [:]
        Carrera.getAll().each{carrera ->
            dic[carrera] = carrera.materias
        }
        return dic
    }

}
