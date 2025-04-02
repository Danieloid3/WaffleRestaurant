package talle2.wafflerestaurant.Entities;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class CarritoItem {
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public CarritoItem(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
    }

    public void incrementarCantidad() {
        this.cantidad++;
        this.subtotal = this.producto.getPrecio() * this.cantidad;
    }
}