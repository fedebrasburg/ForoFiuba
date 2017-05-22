package forofiuba

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class Rol implements Serializable {

	private static final long serialVersionUID = 1

	String authority

	Rol(String auth){
		authority= auth;
	}
	static constraints = {
		authority blank: false, unique: true , inList: ["administrator","usuario"]
	}

	static mapping = {
		cache true
	}
}
