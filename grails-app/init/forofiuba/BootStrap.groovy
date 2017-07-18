package forofiuba

class BootStrap {


    def init = { servletContext ->
        def roleAdmin = new Rol(authority: 'ROLE_ADMIN').save()
        def roleUser = new Rol(authority: 'ROLE_USER').save()
        def roleKarma = new Rol(authority: 'ROLE_KARMA').save()

        //def alumno = new Alumno(username: 'alumno', password: 'password', enabled: true).save()
        def admin = new Alumno(username: 'admin@opinafiuba.com', password: 'password','nombre':'admin', enabled: true)
        admin.karma = new Karma()
        admin.save()
        //new AlumnoRol(alumno: alumno, role: roleUser).save()
        AlumnoRol.create admin, roleAdmin
    }
    def destroy = {
    }
}
