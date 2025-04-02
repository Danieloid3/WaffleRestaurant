package talle2.wafflerestaurant.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor

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

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    //Relacion 1:1 con Fctura
    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    //cascade = CascadeType.ALL: Permite que si se elimina un Pedido, su Factura también se elimine automáticamente.
    private Factura factura;

    @ManyToMany
    @JoinTable(
            name = "pedido_producto",
            joinColumns = @JoinColumn(name = "id_pedido"),
            inverseJoinColumns = @JoinColumn(name = "id_producto") // Cambia de "id" a "id_producto"
    )
    private List<Producto> productos;

    //Método para generar un pedido
    public void generarPedido(int id_cliente, List<Integer> listaIdProducto) {
        // Lógica para generar el pedido con el id_cliente y la lista de productos
        System.out.println("Pedido generado para el cliente: " + id_cliente + " con productos: " + listaIdProducto);
    }

}
