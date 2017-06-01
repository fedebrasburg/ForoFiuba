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
    Usuario usuario
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    static belongsTo = Usuario;

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

    static constraints = {
        password blank: false, password: true
        username blank: false, unique: true
        usuario unique: true,nullable:true
    }

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
