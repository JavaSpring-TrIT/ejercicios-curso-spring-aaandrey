package cr.ac.una.andrey.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import cr.ac.una.andrey.domain.Curso;

public interface CursoService {
    
    Curso save(Curso entity) throws Exception;

    Curso findById(Long id) throws Exception;

    Collection<Curso> findAll();

    Curso update(Curso entity) throws Exception;

    void delete(Long id) throws Exception;  

    Curso findOneByCodCurso(String codCurso) throws Exception;
    
    Curso findOneByNombre(String nombre);

    List<Curso> findByPrecioGreaterThanAndPrecioLessThan(BigDecimal precioDesde, BigDecimal precioHasta);
}
