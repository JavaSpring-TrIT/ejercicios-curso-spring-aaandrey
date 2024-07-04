package cr.ac.una.andrey.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import cr.ac.una.andrey.domain.Curso;
import cr.ac.una.andrey.repository.CursoRepository;
import cr.ac.una.andrey.service.CursoService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CursoServiceImpl implements CursoService {
 
    private final CursoRepository cursoRepository;

    CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Curso findById(Long id) throws Exception {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            return curso.get();
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public Curso save(Curso curso) throws Exception {

        if (Objects.nonNull(curso.getId()) && cursoRepository.existsById(curso.getId())) {
            throw new IllegalArgumentException();
        }

        return cursoRepository.save(curso);
    }

    @Override
    public Collection<Curso> findAll() {
        Collection<Curso> cursos = cursoRepository.findAll();

        if (Objects.isNull(cursos) || cursos.isEmpty()) {
            throw new EmptyResultDataAccessException(0);
        }

        return cursos;
    }

    @Override
    public Curso update(Curso curso) throws Exception {
        if (Objects.isNull(curso.getId())) {
            throw new IllegalArgumentException();
        }
   
        Optional<Curso> CursoOpcional = cursoRepository.findById(curso.getId());

        if (CursoOpcional.isPresent()) {
            Curso CursoOriginal = CursoOpcional.get();
            CursoOriginal.setNombre(curso.getNombre());
            CursoOriginal.setDuracion(curso.getDuracion());
            CursoOriginal.setPrecio(curso.getPrecio());

            return cursoRepository.save(CursoOriginal);

        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try {
            if (cursoRepository.existsById(id)) {
                cursoRepository.deleteById(id);
            } else {
                throw new NotFoundException();
            }
        } catch (DataAccessException ex) {
            System.out.println("delete3 InvalidDataAccessResourceUsageException Andrey " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public Curso findOneByCodCurso(String codCurso) throws Exception {
        Curso curso = cursoRepository.findOneByCodCurso(codCurso);
        if (curso != null) {
            return curso;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public Curso findOneByNombre(String nombre) {
        Curso curso = cursoRepository.findOneByNombre(nombre);
        return curso;
    }

    @Override
    public List<Curso> findByPrecioGreaterThanAndPrecioLessThan(BigDecimal precioDesde, BigDecimal precioHasta) {
        List<Curso> cursos = cursoRepository.findByPrecioGreaterThanAndPrecioLessThan(precioDesde, precioHasta);

        if (Objects.isNull(cursos) || cursos.isEmpty()) {
            throw new EmptyResultDataAccessException(0);
        }

        return cursos;
    }
}
