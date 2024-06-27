package una.ac.cr.andrey.ejercicio2.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import una.ac.cr.andrey.ejercicio2.model.Asignatura;
import una.ac.cr.andrey.ejercicio2.service.AsignaturaService;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {
    
    private final RestClient restClient;

    private final String url = "http://localhost:8088/cursos/";
    private final int valorDuracion = 50;
    private final int mayorDuracion = 10;
    private final int menorDuracion = 5;
    private final int formulaDuracion = 10;

    AsignaturaServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<Asignatura> save(Asignatura asignatura) {
        Asignatura asignaturaValidar = findOneByNombre(asignatura.getNombre());
        if(asignaturaValidar == null) {
            asignatura.setDuracion(asignatura.getAsignaturas() * formulaDuracion);
            asignatura.setCodCurso(asignatura.getNombre().substring(0, 2) + asignatura.getDuracion());
            saveCurso(asignatura);
        }
        
        return findAll();
    }

    @Override
    public void saveCurso(Asignatura asignatura) {
        restClient.post().uri(url).accept(MediaType.APPLICATION_JSON).body(asignatura).retrieve().toBodilessEntity();        
    }
    

    @Override
    public List<Asignatura> findAll() {
        List<Asignatura> asignaturas = Arrays.asList(restClient.get().uri(url).retrieve().body(Asignatura[].class));
        asignaturas.forEach(asignatura -> asignatura.setAsignaturas(asignatura.getDuracion() >= valorDuracion ? mayorDuracion : menorDuracion));
        return asignaturas;
    }

    @Override
    public Asignatura findOneByNombre(String nombre) {
        Asignatura asignatura = restClient.get().uri(url + "byNombre/{nombre}", nombre).retrieve().body(Asignatura.class);        
        return asignatura;
    }
}
