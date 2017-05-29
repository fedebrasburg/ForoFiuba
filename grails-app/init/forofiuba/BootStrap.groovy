package forofiuba

class BootStrap {

    def init = { servletContext ->
        def roleAdmin = new Role(authority: 'ROLE_ADMIN').save(flush: true, failOnError: true)
        def roleUser = new Role(authority: 'ROLE_USER').save(flush: true, failOnError: true)

        def user = new User(username: 'user', password: 'password', enabled: true).save()
        def admin = new User(username: 'admin', password: 'password', enabled: true).save()

        new UserRole( user: user, role: roleUser).save(flush: true, failOnError: true)
        UserRole.create admin, roleAdmin
    }
    def destroy = {
    }
}
