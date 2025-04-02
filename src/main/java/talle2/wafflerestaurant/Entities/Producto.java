package talle2.wafflerestaurant.Entities;

import java.util.List;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private Double precio;
    private String descripcion;
    private String imagen;

    // Relación Muchos a Muchos con Pedido
    @ManyToMany(mappedBy = "productos")
    private List<Pedido> pedidos;
}
