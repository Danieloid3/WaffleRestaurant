package talle2.wafflerestaurant.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Data  // Genera getters, setters, toString(), equals(), y hashCode()
@NoArgsConstructor  // Genera un constructor vacío
@AllArgsConstructor  // Genera un constructor con todos los atributos
@Entity  // Indica que esta clase es una entidad JPA
@Table(name = "factura")  // Especifica el nombre de la tabla en la base de datos
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_factura;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = false)
    private Date fecha_factura;

    @Column(name = "estado", nullable = false)
    private boolean estado_factura;

     // Método para generar una factura
     public void generarFactura(int id_cliente, int id_pedido, int id_pago) {
        // Lógica para generar la factura con los ids de cliente, pedido y pago
        System.out.println("Factura generada para el cliente: " + id_cliente);
    }
}
