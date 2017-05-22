package forofiuba

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class UsuarioLoginRol implements Serializable {

	private static final long serialVersionUID = 1

	UsuarioLogin usuarioLogin
	Rol rol

	@Override
	boolean equals(other) {
		if (other instanceof UsuarioLoginRol) {
			other.usuarioLoginId == usuarioLogin?.id && other.rolId == rol?.id
		}
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (usuarioLogin) builder.append(usuarioLogin.id)
		if (rol) builder.append(rol.id)
		builder.toHashCode()
	}

	static UsuarioLoginRol get(long usuarioLoginId, long rolId) {
		criteriaFor(usuarioLoginId, rolId).get()
	}

	static boolean exists(long usuarioLoginId, long rolId) {
		criteriaFor(usuarioLoginId, rolId).count()
	}

	private static DetachedCriteria criteriaFor(long usuarioLoginId, long rolId) {
		UsuarioLoginRol.where {
			usuarioLogin == UsuarioLogin.load(usuarioLoginId) &&
			rol == Rol.load(rolId)
		}
	}

	static UsuarioLoginRol create(UsuarioLogin usuarioLogin, Rol rol) {
		def instance = new UsuarioLoginRol(usuarioLogin: usuarioLogin, rol: rol)
		instance.save()
		instance
	}

	static boolean remove(UsuarioLogin u, Rol r) {
		if (u != null && r != null) {
			UsuarioLoginRol.where { usuarioLogin == u && rol == r }.deleteAll()
		}
	}

	static int removeAll(UsuarioLogin u) {
		u == null ? 0 : UsuarioLoginRol.where { usuarioLogin == u }.deleteAll()
	}

	static int removeAll(Rol r) {
		r == null ? 0 : UsuarioLoginRol.where { rol == r }.deleteAll()
	}

	static constraints = {
		rol validator: { Rol r, UsuarioLoginRol ur ->
			if (ur.usuarioLogin?.id) {
				UsuarioLoginRol.withNewSession {
					if (UsuarioLoginRol.exists(ur.usuarioLogin.id, r.id)) {
						return ['userRole.exists']
					}
				}
			}
		}
	}

	static mapping = {
		id composite: ['usuarioLogin', 'rol']
		version false
	}
}
