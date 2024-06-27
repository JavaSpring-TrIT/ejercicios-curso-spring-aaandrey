package una.ac.cr.andrey.ejercicio2.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asignatura {
    
    private String nombre;
    private String codCurso;
    private int duracion;
    private int asignaturas;
    private BigDecimal precio;

}
