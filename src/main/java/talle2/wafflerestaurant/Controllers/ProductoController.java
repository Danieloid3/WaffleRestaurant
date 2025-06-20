package talle2.wafflerestaurant.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import talle2.wafflerestaurant.Entities.Producto;
import talle2.wafflerestaurant.Services.ProductoService;
import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService Service;

    @GetMapping("/menu")
    public String list(Model model) {
        List<Producto> listaProductos = Service.listAll();
        model.addAttribute("productos", listaProductos);
        return "Productos/menu";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("title", "Crear producto");
        return "Productos/adminProductos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            Producto producto = Service.get(id);
            model.addAttribute("producto", producto);
            model.addAttribute("title", "Editar producto (id: " + id + ")");
            return "Productos/adminProductos";
        } catch (talle2.wafflerestaurant.Services.ProductoNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/productos/menu";
        }
    }

    @PostMapping("/guardar")
    public String guardar(Producto producto, RedirectAttributes ra) {
        Service.save(producto);
        ra.addFlashAttribute("message", "El producto ha sido guardado correctamente");
        return "redirect:/productos/menu";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        try {
            Service.delete(id);
            ra.addFlashAttribute("message", "El producto ha sido eliminado correctamente");
        } catch (talle2.wafflerestaurant.Services.ProductoNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/productos/menu";
    }
}