package talle2.wafflerestaurant.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import talle2.wafflerestaurant.Entities.*;
import talle2.wafflerestaurant.Services.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private Carrito carrito;

    @GetMapping("")
    public String listarPedidos(@AuthenticationPrincipal Usuario usuario, Model model) {
        // Redirigir a la vista de mis pedidos para usuarios normales
        return "redirect:/pedidos/mis-pedidos";
    }

    @PostMapping("/crear")
    public String crearPedido(@AuthenticationPrincipal Usuario usuario, RedirectAttributes ra) {
        if (carrito.getItems().isEmpty()) {
            ra.addFlashAttribute("error", "El carrito está vacío");
            return "redirect:/carrito";
        }

        try {
            // Crear pedido desde el carrito
            Pedido pedido = pedidoService.crearPedidoDesdeCarrito(carrito, usuario);

            // Crear factura asociada al pedido
            Factura factura = new Factura();
            factura.setFecha_factura(new Date());
            factura.setEstado_factura(false); // No pagada
            factura.setPedido(pedido);
            facturaService.guardarFactura(factura);

            // Limpiar carrito después de crear el pedido
            carrito.limpiarCarrito();

            // Redirigir a la página de confirmación con el ID del pedido
            return "redirect:/pedidos/confirmacion/" + pedido.getId_pedido();
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al procesar el pedido: " + e.getMessage());
            return "redirect:/carrito";
        }
    }

    @GetMapping("/confirmacion/{id}")
    public String mostrarConfirmacion(@PathVariable int id, Model model) {
        try {
            Pedido pedido = pedidoService.obtenerPedido(id);
            Factura factura = pedido.getFactura();

            model.addAttribute("pedido", pedido);
            model.addAttribute("factura", factura);
            return "pedidos/confirmacion";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping("/mis-pedidos")
    public String misPedidos(@AuthenticationPrincipal Usuario usuario, Model model) {
        List<Pedido> pedidos = pedidoService.obtenerPedidosPorUsuario(usuario);
        model.addAttribute("pedidos", pedidos);
        return "pedidos/mis-pedidos";
    }

    @GetMapping("/admin/pedidos")
    public String administrarPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.obtenerTodosPedidos();
        model.addAttribute("pedidos", pedidos);
        return "pedidos/admin-pedidos";
    }

    @GetMapping("/admin/detalle/{id}")
    public String verDetallePedido(@PathVariable int id, Model model) {
        try {
            Pedido pedido = pedidoService.obtenerPedido(id);

            // Verificar que el pedido tenga factura
            if (pedido.getFactura() == null) {
                throw new RuntimeException("El pedido no tiene factura asociada");
            }

            model.addAttribute("pedido", pedido);
            return "pedidos/admin-detalle-pedido";
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener el pedido: " + e.getMessage());
            return "redirect:/pedidos/admin/pedidos";
        }
    }

    @PostMapping("/admin/cambiar-estado/{id}")
    public String cambiarEstadoPedido(@PathVariable int id, @RequestParam boolean estado, RedirectAttributes ra) {
        try {
            pedidoService.cambiarEstadoPedido(id, estado);
            ra.addFlashAttribute("message", "Estado del pedido actualizado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al actualizar el estado del pedido: " + e.getMessage());
        }
        return "redirect:/pedidos/admin/pedidos";
    }
}