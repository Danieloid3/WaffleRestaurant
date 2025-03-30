import talle2.wafflerestaurant.Entities.Cliente;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pago")
public class Pago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pago;
    
    private String tipoDePago;
    private float monto;

    // Método para generar un pago
    public void generarPago(int id_pedido) {
        // Lógica para generar el pago con el id_pedido
        System.out.println("Pago generado para el pedido: " + id_pedido);
    }

    // Método para actualizar una factura
    public void actualizarFactura(int id_factura) {
        // Lógica para actualizar la factura con el id_factura
        System.out.println("Factura actualizada: " + id_factura);
    }
    
}