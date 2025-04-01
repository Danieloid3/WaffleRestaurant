package talle2.wafflerestaurant.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

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

    // Relación 1:1 con Factura
    @OneToOne
    @JoinColumn(name="id_factura", referencedColumnName = "id_factura", nullable = false, unique = true)
    private Factura factura;

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