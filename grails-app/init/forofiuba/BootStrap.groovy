package forofiuba

class BootStrap {


    def init = { servletContext ->
        def roleAdmin = new Rol(authority: 'ROLE_ADMIN').save()
        def roleUser = new Rol(authority: 'ROLE_USER').save()

        //def user = new Usuario(username: 'user', password: 'password', enabled: true).save()
        def admin = new Usuario(username: 'admin@forofiuba.com', password: 'password','nombre':'admin', enabled: true).save()
        //new UsuarioRol(user: user, role: roleUser).save()
        UsuarioRol.create admin, roleAdmin
    }
    def destroy = {
    }
}
