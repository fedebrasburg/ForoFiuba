package forofiuba

import forofiuba.Usuario
import grails.validation.Validateable


class   CreateUsuarioCommand implements Validateable{
    String username
    String password
    String nombre
    String genero
    String telefono
    Date fechaDeNacimiento

    static constraints = {
        importFrom Usuario
    }
}
