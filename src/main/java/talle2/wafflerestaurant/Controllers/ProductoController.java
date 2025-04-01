package talle2.wafflerestaurant.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import talle2.wafflerestaurant.Entities.Producto;
import talle2.wafflerestaurant.Services.ProductoService;
import java.util.List;

@Controller
public class ProductoController {
    @Autowired
    private ProductoService Service;

    @GetMapping ("/menu")
    public String list(Model model) {
        List<Producto> listaProductos = Service.listAll();
        model.addAttribute("productos", listaProductos);

        return "Productos/menu";
    }

}
