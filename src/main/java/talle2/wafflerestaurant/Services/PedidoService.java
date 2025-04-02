package talle2.wafflerestaurant.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talle2.wafflerestaurant.Entities.*;
import talle2.wafflerestaurant.Repositories.PedidoRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido crearPedidoDesdeCarrito(Carrito carrito, Usuario usuario) {
        Pedido pedido = new Pedido();
        pedido.setFecha_pedido(new Date());
        pedido.setEstado_pedido(false); // Pendiente
        pedido.setTotal_pedido((float) carrito.getTotal());
        pedido.setUsuario(usuario);

        // Convertir items del carrito a productos del pedido
        pedido.setProductos(carrito.getItems().stream()
                .map(item -> item.getProducto())
                .collect(Collectors.toList()));

        // Guardar el pedido
        return pedidoRepository.save(pedido);
    }

    public Pedido obtenerPedido(int id) {
        return pedidoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Pedido no encontrado con ID: " + id));
    }

    public List<Pedido> obtenerPedidosPorUsuario(Usuario usuario) {
        return pedidoRepository.findByUsuario(usuario);
    }

    public List<Pedido> obtenerTodosPedidos() {
        return pedidoRepository.findAll();
    }

    public void cambiarEstadoPedido(int id, boolean estado) {
        Pedido pedido = obtenerPedido(id);
        pedido.setEstado_pedido(estado);
        pedidoRepository.save(pedido);
    }
}