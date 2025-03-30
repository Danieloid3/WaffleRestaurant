import talle2.wafflerestaurant.Entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producto") 
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;
    
    private String nombre;
    private float precio;
    private String descripcion;

}
