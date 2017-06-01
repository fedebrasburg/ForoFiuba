package forofiuba

class BootStrap {


    def init = { servletContext ->
        def roleAdmin = new Role(authority: 'ROLE_ADMIN').save()
        //def roleUser = new Role(authority: 'ROLE_USER').save()

        //def user = new User(username: 'user', password: 'password', enabled: true).save()
        def admin = new User(username: 'admin', password: 'password', enabled: true).save()
        new Usuario(User:admin,nombre:'admin',email:'admin@forofiuba.com').save()
        //new UserRole(user: user, role: roleUser).save()
        UserRole.create admin, roleAdmin
    }
    def destroy = {
    }
}
