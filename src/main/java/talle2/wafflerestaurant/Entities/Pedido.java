import talle2.wafflerestaurant.Entities.Cliente;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pedido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = false)
    private Date fecha_pedido;

    @Column(name = "estado", nullable = false)
    private boolean estado_pedido;

    private float total_pedido;

   // Método para generar un pedido
    public void generarPedido(int id_cliente, List<Integer> listaIdProducto) {
        // Lógica para generar el pedido con el id_cliente y la lista de productos
        System.out.println("Pedido generado para el cliente: " + id_cliente + " con productos: " + listaIdProducto);
    }

}
