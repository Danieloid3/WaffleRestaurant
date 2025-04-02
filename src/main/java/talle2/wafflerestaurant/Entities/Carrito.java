package talle2.wafflerestaurant.Entities;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Carrito implements Serializable {
    private List<CarritoItem> items = new ArrayList<>();
    private double total = 0.0;

    public void agregarProducto(Producto producto) {
        // Buscar si ya existe un item con este producto
        for (CarritoItem item : items) {
            if (item.getProducto().getId() == producto.getId()) {
                item.incrementarCantidad();
                calcularTotal();
                return;
            }
        }

        // Si no existe, añadir nuevo item
        items.add(new CarritoItem(producto, 1));
        calcularTotal();
    }

    public void eliminarProducto(int productoId) {
        items.removeIf(item -> item.getProducto().getId() == productoId);
        calcularTotal();
    }

    public void eliminarProducto(Producto producto) {
        eliminarProducto(producto.getId());
    }

    public void actualizarCantidad(int productoId, int cantidad) {
        for (CarritoItem item : items) {
            if (item.getProducto().getId() == productoId) {
                if (cantidad <= 0) {
                    items.remove(item);
                } else {
                    item.setCantidad(cantidad);
                    item.setSubtotal(item.getProducto().getPrecio() * cantidad);
                }
                break;
            }
        }
        calcularTotal();
    }

    public void limpiarCarrito() {
        items.clear();
        total = 0;
    }

    private void calcularTotal() {
        total = items.stream().mapToDouble(CarritoItem::getSubtotal).sum();
    }

    public void restarProducto(Producto producto) {
        for (CarritoItem item : items) {
            if (item.getProducto().getId() == producto.getId()) {
                if (item.getCantidad() > 1) {
                    item.setCantidad(item.getCantidad() - 1);
                } else {
                    eliminarProducto(producto);
                }
                calcularTotal();
                return;
            }
        }
    }
}