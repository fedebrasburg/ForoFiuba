package forofiuba

class BootStrap {

    def init = { servletContext ->
        def roleAdmin = new Role(authority: 'ADMIN').save()
        def roleUser = new Role(authority: 'USER').save()

        def user = new User(username: 'user', password: 'password', enabled: true).save()
        def admin = new User(username: 'admin', password: 'password', enabled: true).save()

        new UserRole( user: user, role: roleUser)
        UserRole.create admin, roleUser
        UserRole.create admin, roleAdmin
    }
    def destroy = {
    }
}
