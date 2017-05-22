package forofiuba

class BootStrap {

    def init = { servletContext ->
        def userRole = new Rol('administrador').save()

        def me = new UsuarioLogin(username: 'andy@kpo.com', password:'andykpo').save()

        UsuarioLoginRol.create me, userRole

        UsuarioLoginRol.withSession {
            it.flush()
            it.clear()
        }
    }
    def destroy = {
    }
}
