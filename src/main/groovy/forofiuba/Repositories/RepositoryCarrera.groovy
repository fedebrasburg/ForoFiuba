package forofiuba.Repositories

import forofiuba.Carrera;

import java.util.List;

class RepositoryCarrera {
        public  static List<Carrera> getAllCarreras(){
            return Carrera.getAll();
        }
        public  static List<Carrera> findAllByNombreInList( List<String> carreras) {
            return Carrera.findAllByNombreInList(carreras);
        }
}
