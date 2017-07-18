package forofiuba

import grails.validation.Validateable


class CreateAlumnoCommand implements Validateable{
    String username
    String password
    String nombre
    String genero
    String telefono
    Date fechaDeNacimiento

    static constraints = {
        importFrom Alumno
    }
}
