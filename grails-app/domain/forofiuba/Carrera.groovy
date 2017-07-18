package forofiuba

class Carrera {
    String nombre;
    static hasMany = [materias: Materia,alumnos: Alumno]
    static belongsTo = [Alumno]
    static constraints = {
        nombre nullable: false,unique: true
        materias nullable: false
    }


    def static diccionarioMateriasPorCarrera(List<Carrera> carreras){
        def dic = [:]
        carreras.each{carrera ->
            dic[carrera] = carrera.materias
        }
        return dic
    }

}
