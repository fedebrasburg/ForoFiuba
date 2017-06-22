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
    public CreateOpinionCommand(){}
    public CreateOpinionCommand(String username, String password, String nombre,String genero,String telefono,  Date fechaDeNacimiento){
        this.username = username
        this.password = password
        this.nombre = nombre
        this.genero = genero
        this.telefono = telefono
        this.fechaDeNacimiento = fechaDeNacimiento
    }
    static constraints = {
        importFrom Usuario
    }
}
