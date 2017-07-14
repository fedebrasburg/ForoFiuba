package forofiuba

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache = true, includeNames = true, includePackage = false)
class UsuarioRol implements Serializable {

    private static final long serialVersionUID = 1

    Usuario user
    Rol role

    public static void createKarmaUsuarios(List<Usuario> usuariosKarma){
        Rol rolKarma=Rol.findByAuthority("ROLE_KARMA")
        List<UsuarioRol> listUsuarioKarma= UsuarioRol.findAllByRole(rolKarma)
        if(!listUsuarioKarma.containsAll(usuariosKarma)){
            deleteAllKarmaRol()
            usuariosKarma.each {Usuario usuario-> createKarmaRol(usuario)}
        }
        listUsuarioKarma= UsuarioRol.findAllByRole(rolKarma)
        listUsuarioKarma
    }
    public static void createKarmaRol(Usuario usuario){
        Rol rolKarma=Rol.findByAuthority("ROLE_KARMA")
        new UsuarioRol(user:  usuario,role:  rolKarma).save(flush:true)
    }
    public static void deleteAllKarmaRol(){
        Rol rolKarma=Rol.findByAuthority("ROLE_KARMA")
        List<UsuarioRol> listUsuarioKarma= UsuarioRol.findAllByRole(rolKarma)
        UsuarioRol.deleteAll(listUsuarioKarma)
    }
    @Override
    boolean equals(other) {
        if (other instanceof UsuarioRol) {
            other.userId == user?.id && other.roleId == role?.id
        }
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (user) builder.append(user.id)
        if (role) builder.append(role.id)
        builder.toHashCode()
    }

    static UsuarioRol get(long userId, long roleId) {
        criteriaFor(userId, roleId).get()
    }

    static boolean exists(long userId, long roleId) {
        criteriaFor(userId, roleId).count()
    }

    private static DetachedCriteria criteriaFor(long userId, long roleId) {
        UsuarioRol.where {
            user == Usuario.load(userId) &&
                    role == Rol.load(roleId)
        }
    }

    static UsuarioRol create(Usuario user, Rol role) {
        def instance = new UsuarioRol(user: user, role: role)
        instance.save()
        instance
    }

    static boolean remove(Usuario u, Rol r) {
        if (u != null && r != null) {
            UsuarioRol.where { user == u && role == r }.deleteAll()
        }
    }

    static int removeAll(Usuario u) {
        u == null ? 0 : UsuarioRol.where { user == u }.deleteAll()
    }

    static int removeAll(Rol r) {
        r == null ? 0 : UsuarioRol.where { role == r }.deleteAll()
    }

    static constraints = {
        role validator: { Rol r, UsuarioRol ur ->
            if (ur.user?.id) {
                UsuarioRol.withNewSession {
                    if (UsuarioRol.exists(ur.user.id, r.id)) {
                        return ['userRole.exists']
                    }
                }
            }
        }
    }

    static mapping = {
        id composite: ['user', 'role']
        version false
    }
}
