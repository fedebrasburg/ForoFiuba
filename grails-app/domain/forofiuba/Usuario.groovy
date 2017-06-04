package forofiuba

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.codehaus.groovy.control.messages.ExceptionMessage

@EqualsAndHashCode(includes = 'username')
@ToString(includes = 'username', includeNames = true, includePackage = false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    transient springSecurityService

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    static belongsTo = Usuario;
    final static Date MIN_DATE = Calendar.instance.with { add(YEAR, -12); it }.time
    final static Date MAX_DATE = Calendar.instance.with { add(YEAR, -100); it }.time
    String nombre
    String genero
    String telefono
    Date fechaDeNacimiento
    User user

    static hasMany = [opiniones: Opinion, carreras: Carrera]
    static constraints = {
        genero(inList: ["H", "M", "U"], nullable: true)
        nombre nullable: false, blank: false
        fechaDeNacimiento nullable: true
        opiniones nullable: true
        carreras nullable: true
        telefono nullable: true
        user nullable: false, unique: true
        password blank: false, password: true
        username blank: false, unique: true,email: true
    }

    static createUsuario(String nombre, String genero, String email, String telefono, Date fechaDeNacimiento, User user) {
        def usuario = new Usuario()
        usuario.nombre = nombre
        usuario.genero = genero
        usuario.email = email
        usuario.telefono = telefono
        usuario.fechaDeNacimiento = fechaDeNacimiento
        usuario.user = user
        user.usuario = usuario
        usuario.save(flush: true, failOnError: true)
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this)*.role
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }

    static transients = ['springSecurityService']


    static mapping = {
        table '`User`'
        password column: '`password`'
    }

    def static createUserRoleUser(String nombre, String password) {
        def user = new User()
        user.username = nombre
        user.password = password
        user.save(flush: true, failOnError: true)
        Role role=Role.findByAuthority("ROLE_USER")
        if(!role){
            raise ExceptionMessage("No encuentra el rol de usuario")
        }
        new UserRole(user: user, role: role).save(flush: true, failOnError: true)
        return  user
    }
}
