package talle2.wafflerestaurant.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import talle2.wafflerestaurant.Entities.*;
import talle2.wafflerestaurant.Services.*;

@Controller
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/realizar/{idFactura}")
    public String formularioPago(@PathVariable int idFactura, Model model) {
        try {
            Factura factura = facturaService.obtenerFactura(idFactura);
            model.addAttribute("factura", factura);
            model.addAttribute("pago", new Pago());
            return "pagos/realizar-pago";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PostMapping("/procesar")
    public String procesarPago(@ModelAttribute Pago pago, @RequestParam int idFactura,
                               @AuthenticationPrincipal Usuario usuario, RedirectAttributes ra) {
        try {
            Factura factura = facturaService.obtenerFactura(idFactura);

            // Configurar el pago
            pago.setFactura(factura);
            pago.setMonto(factura.getPedido().getTotal_pedido());

            // Guardar el pago
            pagoService.guardarPago(pago);

            ra.addFlashAttribute("message", "¡Pago realizado con éxito! Esperando aprobación");
            return "redirect:/pedidos/mis-pedidos";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al procesar el pago: " + e.getMessage());
            return "redirect:/pagos/realizar/" + idFactura;
        }
    }

    @GetMapping("/admin")
    public String administrarPagos(Model model) {
        model.addAttribute("pagos", pagoService.obtenerTodosPagos());
        return "pagos/admin-pagos";
    }

    @PostMapping("/admin/aprobar/{id}")
    public String aprobarPago(@PathVariable int id, RedirectAttributes ra) {
        try {
            Pago pago = pagoService.obtenerPago(id);

            // Cambiar estado del pago (si necesitas añadir un campo para esto)
            // pago.setEstadoPago(true);
            pagoService.guardarPago(pago);

            // Actualizar estado de la factura
            Factura factura = pago.getFactura();
            facturaService.cambiarEstadoFactura(factura.getId_factura(), true);

            // Actualizar estado del pedido
            Pedido pedido = factura.getPedido();
            pedidoService.cambiarEstadoPedido(pedido.getId_pedido(), true);

            ra.addFlashAttribute("message", "Pago aprobado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al aprobar el pago: " + e.getMessage());
        }
        return "redirect:/pagos/admin";
    }

    @PostMapping("/admin/rechazar/{id}")
    public String rechazarPago(@PathVariable int id, RedirectAttributes ra) {
        try {
            Pago pago = pagoService.obtenerPago(id);

            // Eliminar el pago (o marcarlo como rechazado)
            pagoService.eliminarPago(id);

            // También se puede marcar como rechazado si prefieres mantener el registro
            //pago.setEstadoPago(false);
            // pagoService.guardarPago(pago);

            ra.addFlashAttribute("message", "Pago rechazado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al rechazar el pago: " + e.getMessage());
        }
        return "redirect:/pagos/admin";
    }
}