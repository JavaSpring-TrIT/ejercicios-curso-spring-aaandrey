package cr.ac.una.andrey.controller;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.una.andrey.domain.Curso;
import cr.ac.una.andrey.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/cursos")
public class CursoController {

    private final CursoService service;

    /**
     * Constructor de la clase {@code BaseController}.
     *
     * @param service servicio utilizado por el controlador
     */
    public CursoController(CursoService service) {
        this.service = service;
    }

    /**
     * Maneja la solicitud HTTP POST para crear una nueva entidad de tipo Curso en
     * la base de datos.
     *
     * @param entity entidad Curso que se va a crear en la BD.
     * @param result contiene el resultado de las validaciones del modelo y errores
     *               si los hay.
     * @return Collection<Curso>
     * @throws IllegalArgumentException por errores en el BindingResult debido a las
     *                                  validaciones del modelo.
     * @throws Exception                si ocurre un error durante el proceso de
     *                                  guardar la entidad.
     */
    @Operation(summary = "Guarda el registro de curso", description = "Almacena el registro de curso")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Curso> save(
            @Parameter(description = "Objeto con la información del curso") @RequestBody Curso curso,
            BindingResult result) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("Username: " + userDetails.getUsername());
        System.out.println("Authorities: " + userDetails.getAuthorities());

        Curso cursoNuevo = this.service.save(curso);
        Collection<Curso> data = this.service.findAll();
        return data;
    }

    /**
     * Maneja la solicitud HTTP GET para obtener una entidad de tipo <E> según una
     * identificación
     *
     * @param id identificación de la entidad.
     * @return ResponseEntity objeto {@code Response} con el estado de la operación.
     * @throws Exception si ocurre un error durante el proceso de obtener la
     *                   entidad.
     */
    @Operation(summary = "Busca el curso por medio de id", description = "Localiza el curso de acuerdo el id")
    @GetMapping(path = "/byId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Curso> findOneById(
            @Parameter(description = "id del curso que se desea buscar") @PathVariable(name = "id") Long id)
            throws Exception {

        Curso data = this.service.findById(id);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Message", "Se consulto correctamente");
        return new ResponseEntity<Curso>(data, responseHeaders, HttpStatus.ACCEPTED);
    }

    /**
     * Maneja la solicitud HTTP GET para obtener todos los registros de una entidad
     * <E>.
     *
     * @param na
     * @return Collection
     * @throws na
     */
    @Operation(summary = "Busca todos los cursos", description = "Lista todos los cursos")
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Curso> findAll() {

        Collection<Curso> data = this.service.findAll();
        return data;
    }

    /**
     * Maneja la solicitud HTTP PUT para actualizar una entidad <E>.
     *
     * @param entidad entidad <E> que se va a actualizar en la BD.
     * @param result  contiene el resultado de las validaciones del modelo y errores
     *                si los hay.
     * @return ResponseEntity objeto {@code Response} con el estado de la operación.
     * @throws IllegalArgumentException por errores en el BindingResult debido a las
     *                                  validaciones de modelo.
     * @throws Exception                si ocurre un error durante el proceso de
     *                                  actualizar la entidad.
     */
    @Operation(summary = "Actualiza el curso de acuerdo al que recibe", description = "Modifica el curso")
    @PutMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(
            @Parameter(description = "Curso que se desea modificar con la información nueva") @Valid @RequestBody Curso entity,
            BindingResult result) throws Exception {
        Curso data = this.service.update(entity);
    }

    /**
     * Maneja la solicitud HTTP DELETE para eliminar una entidad <E> según un id.
     *
     * @param id identificación de una entidad <E>.
     * @return Collection<Curso>
     * @throws Exception si ocurre un error durante el proceso de eliminar la
     *                   entidad.
     */
    @Operation(summary = "Borra el curso por medio de id", description = "Elimina el curso de acuerdo el id")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Curso> deleteOneById(
            @Parameter(description = "Id del curso a eliminar") @PathVariable(name = "id") Long id) throws Exception {

        this.service.delete(id);
        Collection<Curso> data = this.service.findAll();
        return data;
    }

    /**
     * Maneja la solicitud HTTP GET para obtener una entidad de tipo <E> según una
     * identificación
     *
     * @param codCurso de la entidad.
     * @return ResponseEntity objeto {@code Response} con el estado de la operación.
     * @throws Exception si ocurre un error durante el proceso de obtener la
     *                   entidad.
     */
    @Operation(summary = "Busca el curso por medio del codCurso", description = "Localiza el curso de acuerdo al codCurso")
    @GetMapping(path = "/{codCurso}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Curso> findOneByCodCurso(
            @Parameter(description = "CodCurso del curso a buscar") @PathVariable(name = "codCurso") String codCurso)
            throws Exception {

        Curso data = this.service.findOneByCodCurso(codCurso);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Message", "Se consulto por codigo correctamente");
        return new ResponseEntity<Curso>(data, responseHeaders, HttpStatus.ACCEPTED);
    }

    /**
     * Maneja la solicitud HTTP GET para obtener una entidad de tipo <E> según una
     * identificación
     *
     * @param nombre de la entidad.
     * @return ResponseEntity objeto {@code Response} con el estado de la operación.
     * @throws Exception si ocurre un error durante el proceso de obtener la
     *                   entidad.
     */
    @Operation(summary = "Busca el curso por medio del nombre", description = "Localiza el curso de acuerdo al nombre")
    @GetMapping(path = "/byNombre/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Curso> findOneByNombre(
            @Parameter(description = "Nombre del curso a buscar") @PathVariable(name = "nombre") String nombre)
            throws Exception {

        Curso data = this.service.findOneByNombre(nombre);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Message", "Se consulto por nombre correctamente");
        return new ResponseEntity<Curso>(data, responseHeaders, HttpStatus.ACCEPTED);
    }

    /**
     * Maneja la solicitud HTTP GET para obtener todos los registros de una entidad
     * <E>.
     *
     * @param na
     * @return Collection
     * @throws na
     */
    @Operation(summary = "Busca el curso de acuerdo al precio, recibe el precio desde y hasta", description = "Localiza el curso de acuerdo al precio, recibe el precio desde y hasta")
    @GetMapping(path = "/{precioDesde}/{precioHasta}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Curso> findAllByPrecio(
            @Parameter(description = "PrecioDesde del curso a buscar") @PathVariable(name = "precioDesde") BigDecimal precioDesde,
            @Parameter(description = "PrecioHasta del curso a buscar") @PathVariable(name = "precioHasta") BigDecimal precioHasta) {

        Collection<Curso> data = this.service.findByPrecioGreaterThanAndPrecioLessThan(precioDesde, precioHasta);
        return data;
    }
}
