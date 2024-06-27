package una.ac.cr.andrey.ejercicio2.service;

import java.util.List;

import una.ac.cr.andrey.ejercicio2.model.Asignatura;

public interface AsignaturaService {
    
    List<Asignatura> save(Asignatura asignatura);
    
    void saveCurso(Asignatura asignatura);

    List<Asignatura> findAll();

    Asignatura findOneByNombre(String nombre);
}
