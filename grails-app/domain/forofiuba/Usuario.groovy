package forofiuba

class Usuario {
    String nombre;
    String genero;
    Date fechaDeNacimiento;
    static hasMany = [Opiniones: Opinion,Carreras:Carrera]

    def constraints = {
        genero( inList: ["H", "M", "U"])
    }

}
