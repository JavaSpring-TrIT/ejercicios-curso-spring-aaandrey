package cr.ac.una.andrey.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Andrey Vasquez Cespedes
 */
@Entity
@Table(name = "CURSOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso  {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CODCURSO")
    private String codCurso;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DURACION")
    private int duracion;
    @Column(name = "PRECIO")
    private BigDecimal precio;


}
