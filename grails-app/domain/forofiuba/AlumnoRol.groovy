package forofiuba


import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache = true, includeNames = true, includePackage = false)
class AlumnoRol implements Serializable {

    private static final long serialVersionUID = 1

    Alumno alumno
    Rol role

        public static void createKarmaAlumnos(List<Alumno> alumnosKarma){
        Rol rolKarma=Rol.findByAuthority("ROLE_KARMA")
        List<AlumnoRol> listAlumnosKarma= AlumnoRol.findAllByRole(rolKarma)
        if(!listAlumnosKarma.containsAll(alumnosKarma)){
            deleteAllKarmaRol()
            alumnosKarma.each { Alumno alumno-> createKarmaRol(alumno)}
        }
        listAlumnosKarma= AlumnoRol.findAllByRole(rolKarma)

        listAlumnosKarma
    }
    public static void createKarmaRol(Alumno alumno){
        Rol rolKarma=Rol.findByAuthority("ROLE_KARMA")
        new AlumnoRol(alumno:  alumno,role:  rolKarma).save(flush:true)
    }
    public static void deleteAllKarmaRol(){
        Rol rolKarma=Rol.findByAuthority("ROLE_KARMA")
        List<AlumnoRol> listAlumnosKarma= AlumnoRol.findAllByRole(rolKarma)
        AlumnoRol.deleteAll(listAlumnosKarma)
    }
    @Override
    boolean equals(other) {
        if (other instanceof AlumnoRol) {
            other.userId == alumno?.id && other.roleId == role?.id
        }
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (alumno) builder.append(alumno.id)
        if (role) builder.append(role.id)
        builder.toHashCode()
    }

    static AlumnoRol get(long userId, long roleId) {
        criteriaFor(userId, roleId).get()
    }

    static boolean exists(long userId, long roleId) {
        criteriaFor(userId, roleId).count()
    }

    private static DetachedCriteria criteriaFor(long userId, long roleId) {
        AlumnoRol.where {
            alumno == Alumno.load(userId) &&
                    role == Rol.load(roleId)
        }
    }

    static AlumnoRol create(Alumno user, Rol role) {
        def instance = new AlumnoRol(alumno: user, role: role)
        instance.save()
        instance
    }

    static boolean remove(Alumno u, Rol r) {
        if (u != null && r != null) {
            AlumnoRol.where { alumno == u && role == r }.deleteAll()
        }
    }

    static int removeAll(Alumno u) {
        u == null ? 0 : AlumnoRol.where { alumno == u }.deleteAll()
    }

    static int removeAll(Rol r) {
        r == null ? 0 : AlumnoRol.where { role == r }.deleteAll()
    }

    static constraints = {
        role validator: { Rol r, AlumnoRol ur ->
            if (ur.alumno?.id) {
                AlumnoRol.withNewSession {
                    if (AlumnoRol.exists(ur.alumno.id, r.id)) {
                        return ['userRole.exists']
                    }
                }
            }
        }
    }

    static mapping = {
        id composite: ['alumno', 'role']
        version false
    }
}
