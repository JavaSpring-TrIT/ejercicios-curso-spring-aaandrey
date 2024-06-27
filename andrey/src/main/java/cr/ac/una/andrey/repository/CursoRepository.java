package cr.ac.una.andrey.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cr.ac.una.andrey.domain.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findOneByCodCurso(String codCurso);
    
    Curso findOneByNombre(String nombre);

    List<Curso> findByPrecioGreaterThanAndPrecioLessThan(BigDecimal precioDesde, BigDecimal precioHasta);
}