package una.ac.cr.andrey.ejercicio2.controller;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import una.ac.cr.andrey.ejercicio2.model.Asignatura;
import una.ac.cr.andrey.ejercicio2.service.AsignaturaService;


@RestController
@RequestMapping(path = "/asignaturas")
public class AsignacionController {

    private final AsignaturaService service;

    /**
     * Constructor de la clase {@code BaseController}.
     *
     * @param service servicio utilizado por el controlador
     */
    public AsignacionController(AsignaturaService service) {
        this.service = service;
    }

    /**
     * Maneja la solicitud HTTP GET para obtener todos los registros de una entidad
     * <E>.
     *
     * @param na
     * @return Collection
     * @throws na
     */
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Asignatura> findAll() {
        Collection<Asignatura> data = this.service.findAll();
        return data;
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Asignatura> save(@RequestBody Asignatura asignatura, BindingResult result) throws Exception {

        Collection<Asignatura> data = this.service.save(asignatura);
        return data;
    }

}
