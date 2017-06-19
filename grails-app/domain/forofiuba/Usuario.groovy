package forofiuba

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes = 'username')
@ToString(includes = 'username', includeNames = true, includePackage = false)
class Usuario implements Serializable {

    private static final long serialVersionUID = 1

    transient springSecurityService

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    final static Date MIN_DATE = Calendar.instance.with { add(YEAR, -12); it }.time
    final static Date MAX_DATE = Calendar.instance.with { add(YEAR, -100); it }.time
    String nombre
    String genero
    String telefono
    Date fechaDeNacimiento

    static hasMany = [opiniones: Opinion, carreras: Carrera]
    static constraints = {
        username blank: false, unique: true,email: true,nullable: false
        nombre nullable: false, blank: false
        genero nullable: true
        fechaDeNacimiento nullable: true
        opiniones nullable: true
        carreras nullable: true
        telefono nullable: true
        password blank: false, password: true,nullable: false
    }



    Set<Rol> getAuthorities() {
        UsuarioRol.findAllByUser(this)*.role
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
//        table '`Usuario`'
  //      password column: '`password`'
    }

    boolean puedeOpinar(Curso curso){
        curso.catedra.materia.correlativas.every{ materia ->
            opinoSobre(materia)
        }
    }

    private boolean opinoSobre(Materia materia){
        opiniones.find{opinion->
            opinion.materia == materia
        }
    }

    def materiasFaltantes(Curso curso){
        curso.catedra.materia.correlativas.findAll{materia->
            !opinoSobre(materia)
        }
    }

    def getCompas(){
        Opinion.findAllByUsuario(this).unique { a, b -> a.curso.id <=> b.curso.id }.collectEntries{ opinion ->
            def compas = Opinion.findAllByCurso(opinion.curso).findAll{posibleMatch ->
                (posibleMatch.year == opinion.year && posibleMatch.cuatrimestre == opinion.cuatrimestre &&  posibleMatch.usuario.id != opinion.usuario.id)
            }.collect { posibleMatch ->
                posibleMatch.usuario
            }
            def cursoCompartido = new CursoCompartido()
            cursoCompartido.cuatrimestre = opinion.curso.nombre
            cursoCompartido.year = opinion.year
            cursoCompartido.cursoNombre = opinion.curso.nombre
            [(cursoCompartido) : compas]
        }.findAll {key,value->
            value != []
        }
    }

}
