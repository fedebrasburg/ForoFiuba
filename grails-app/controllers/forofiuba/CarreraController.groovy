package forofiuba

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class CarreraController {
    static scaffold = Carrera
}
