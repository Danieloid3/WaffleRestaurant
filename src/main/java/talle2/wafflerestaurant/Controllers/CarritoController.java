package talle2.wafflerestaurant.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import talle2.wafflerestaurant.Entities.Carrito;
import talle2.wafflerestaurant.Entities.Producto;
import talle2.wafflerestaurant.Entities.Usuario;
import talle2.wafflerestaurant.Services.PedidoService;
import talle2.wafflerestaurant.Services.ProductoService;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private Carrito carrito;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public String verCarrito(Model model) {
        model.addAttribute("carrito", carrito);
        return "carrito/ver-carrito";
    }

    @PostMapping("/agregar/{id}")
    public String agregarProducto(@PathVariable int id, @RequestParam(required = false) String origen, RedirectAttributes ra) {
        try {
            Producto producto = productoService.get(id);
            carrito.agregarProducto(producto);
            ra.addFlashAttribute("message", "Producto agregado al carrito");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al agregar el producto");
        }

        // Si viene del carrito, redirigir al carrito
        if (origen != null && origen.equals("carrito")) {
            return "redirect:/carrito";
        }

        return "redirect:/productos/menu";
    }

    @PostMapping("/restar/{id}")
    public String restarProducto(@PathVariable int id, RedirectAttributes ra) {
        try {
            Producto producto = productoService.get(id);
            carrito.restarProducto(producto);
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al modificar la cantidad");
        }
        return "redirect:/carrito";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id, RedirectAttributes ra) {
        try {
            Producto producto = productoService.get(id);
            carrito.eliminarProducto(producto);
            ra.addFlashAttribute("message", "Producto eliminado del carrito");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar el producto");
        }
        return "redirect:/carrito";
    }

    @PostMapping("/confirmar")
    public String confirmarPedido(@AuthenticationPrincipal Usuario usuario, RedirectAttributes ra) {
        if (carrito.getItems().isEmpty()) {
            ra.addFlashAttribute("error", "El carrito está vacío");
            return "redirect:/carrito";
        }

        try {
            // Crear pedido desde el carrito
            pedidoService.crearPedidoDesdeCarrito(carrito, usuario);

            // Limpiar carrito después de crear el pedido
            carrito.limpiarCarrito();

            ra.addFlashAttribute("message", "¡Pedido realizado con éxito!");
            return "redirect:/pedidos/confirmacion";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al procesar el pedido");
            return "redirect:/carrito";
        }
    }

    @PostMapping("/limpiar")
    public String limpiarCarrito(RedirectAttributes ra) {
        carrito.limpiarCarrito();
        ra.addFlashAttribute("message", "Carrito vacío");
        return "redirect:/carrito";
    }
}