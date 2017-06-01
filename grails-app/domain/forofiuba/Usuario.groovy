package forofiuba


class Usuario {
    final static Date MIN_DATE = Calendar.instance.with { add(YEAR, -12); it }.time
    final static Date MAX_DATE = Calendar.instance.with { add(YEAR, -100); it }.time
    String nombre
    String genero
    String email
    String telefono
    Date fechaDeNacimiento
    User user

    static hasMany = [opiniones: Opinion, carreras: Carrera]
    static constraints = {
        genero(inList: ["H", "M", "U"], nullable: true)
        nombre nullable: false, blank: false
        fechaDeNacimiento nullable: true
        opiniones nullable: true
        carreras nullable: true
        email email: true, nullable: false, unique: true
        telefono nullable: true
        user nullable: false, unique: true
    }

    def
    static createUsuario(String nombre, String genero, String email, String telefono, Date fechaDeNacimiento, User user) {
        def usuario = new Usuario()
        usuario.nombre = nombre
        usuario.genero = genero
        usuario.email = email
        usuario.telefono = telefono
        usuario.fechaDeNacimiento = fechaDeNacimiento
        usuario.user = user
        user.usuario = usuario
        usuario.save(flush: true, failOnError: true)
    }

}
