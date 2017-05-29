package forofiuba

class RegistroController {

    def index(){
        render(view:"Registrar" )
    }
    def crearUsuario(){
        User user= User.createUser(params.email,params.password)
        Usuario.createUsuario(params.nombre,params.genero,params.email, params.telefono,params.fechaDeNacimiento,user)
        backhome()
    }
    def backhome(){
        redirect(controller: 'home', action:'index')
    }
    def login(){
        redirect(controller: 'login', action:'index')
    }
}
