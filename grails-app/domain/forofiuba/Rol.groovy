package forofiuba

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes = 'authority')
@ToString(includes = 'authority', includeNames = true, includePackage = false)
class Rol implements Serializable {

    private static final long serialVersionUID = 1

    String authority

    static constraints = {
        authority blank: false, unique: true
    }
    static Rol rolAlumno(){
        return Rol.findByAuthority("ROLE_USER")
    }
    static mapping = {
        cache true
    }
}
