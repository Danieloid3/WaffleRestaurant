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
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;

    private String nombre;
    private float precio;
    private String descripcion;

    // Relación Muchos a Muchos con Pedido
    @ManyToMany(mappedBy = "productos")
    private List<Pedido> pedidos;
}
