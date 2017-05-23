package forofiuba

class BootStrap {

    def init = { servletContext ->
        def userRole = new Role(authority:'ROLE_USER').save()

        def me = new User(username:'a@a.com', password:'a').save()

        UserRole.create me, userRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }
    }
    def destroy = {
    }
}
