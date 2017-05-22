package forofiuba



class Usuario {
    final static Date MIN_DATE = Calendar.instance.with { add( YEAR, -12 ) ; it }.time
    final static Date MAX_DATE = Calendar.instance.with { add( YEAR, -100 ) ; it }.time
    String nombre
    String genero
    String email
    String telefono
    Date fechaDeNacimiento
    String passwordHash

    static hasMany = [opiniones: Opinion,carreras:Carrera]
    static constraints = {
        genero( inList: ["H", "M", "U"], nullable:true)
        nombre nullable: false,blank:false
        fechaDeNacimiento nullable: true ,min:MIN_DATE ,max:MAX_DATE
        opiniones nullable: true
        carreras nullable: true
        email email:true , nullable: false, unique:true
        passwordHash blank: false, nullable: false
        telefono nullable: true
    }

}
