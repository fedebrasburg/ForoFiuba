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
    final static double MIN_CONFIABLES=2.2;
    final static double MAX_CONFIABLES=3.8;
    final static double VALOR_CANTIDAD_DE_OPINIONES=1,VALOR_CALIFICACION_DE_SUS_OPINIONES=1,VALOR_CANTIDAD_CALIFICACIONES=0.4,VALOR_CONFIABILIDAD=5,VALOR_REGULARIDAD=1.5;
    String nombre
    String genero
    String telefono
    Date fechaDeNacimiento

    static hasMany = [opiniones: Opinion, carreras: Carrera,calificaciones: CalificacionOpinion]
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

    public int getCantidadCalificaciones(){
        return  this.calificaciones.size()
    }
    public double getKarmaCantidadCalificaciones(){
        return VALOR_CANTIDAD_CALIFICACIONES*getCantidadCalificaciones()
    }
    public int getCantidadOpiniones(){
        return  this.opiniones.size()
    }
    public double getKarmaCantidadOpiniones(){
        return VALOR_CANTIDAD_DE_OPINIONES*getCantidadOpiniones()
    }
    public  int getPromedioOpiniones(){
        if(!opiniones){return 0}
        return this.opiniones.sum{Opinion opinion->opinion.puntuacion}/getCantidadOpiniones()
    }
    public boolean esConfiable(){
        return ((MIN_CONFIABLES<getPromedioOpiniones())&&(getPromedioOpiniones()<MAX_CONFIABLES))
    }
    public double getKarmaConfiable(){
        return (esConfiable()?1:-1)*VALOR_CONFIABILIDAD
    }
    public int getCantidadCalificacionesOpinionesPositivas(){
        if(!opiniones){return 0}
        return opiniones.sum{it.getCalificacionesPositivas()}
    }
    public int getCantidadCalificacionesOpinionesNegativas(){
        if(!opiniones){return 0}
        return opiniones.sum{ it.getCalificacionesNegativas()}
    }
    public double getKarmaCalificacionOpiniones(){
        return VALOR_CALIFICACION_DE_SUS_OPINIONES*(getCantidadCalificacionesOpinionesPositivas()-getCantidadCalificacionesOpinionesNegativas())
    }
    public int getRegularidad(){
        if(!opiniones){return 0}
        Cuatrimestre cuatrimestre=opiniones.min {Opinion opinion->opinion.cuatrimestre}.cuatrimestre
        Cuatrimestre cuatrimestreActual=Cuatrimestre.getCuatrimestreActual()
        int cantidadDeCuatrimestres=cuatrimestre.distancia(cuatrimestreActual);
        return getCantidadOpiniones()/cantidadDeCuatrimestres;
    }
    public getKarmaRegularidad(){
        return getRegularidad()*VALOR_REGULARIDAD
    }
    public int getKarma(){
        return  (getKarmaCalificacionOpiniones()+getKarmaCantidadCalificaciones()+getKarmaCantidadOpiniones()+getKarmaConfiable()+getKarmaRegularidad());
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
        puedeOpinar(curso.catedra.materia)
    }

    boolean puedeOpinar(Materia materia){
        materia.correlativas.every{ correlativa ->
            opinoSobre(correlativa)
        }
    }

    private opinoSobre(Materia materia){
        opiniones.any{opinion->
            opinion.materia.id == materia.id
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
                (posibleMatch.cuatrimestre == opinion.cuatrimestre &&  posibleMatch.usuario.id != opinion.usuario.id)
            }.collect { posibleMatch ->
                posibleMatch.usuario
            }
            def cursoCompartido = new CursoCompartido()
            cursoCompartido.cuatrimestre = opinion.cuatrimestre
            cursoCompartido.cursoNombre = opinion.curso.nombre
            [(cursoCompartido) : compas]
        }.findAll {key,value->
            value != []
        }
    }
    boolean cursoCon(def cursoCompartidos,Cuatrimestre cuatrimestre,String cursoNombre){
        CursoCompartido posibleCursoCompartidos=new CursoCompartido("cursoNombre":cursoNombre,"cuatrimestre":cuatrimestre)
        boolean cursoCon =  cursoCompartidos.keySet().any{cursoCompartido->
            cursoCompartido==((posibleCursoCompartidos))
        }
        return cursoCon
    }

}
