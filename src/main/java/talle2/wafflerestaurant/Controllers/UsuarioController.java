package talle2.wafflerestaurant.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import talle2.wafflerestaurant.Entities.Cliente;
import talle2.wafflerestaurant.Entities.Usuario;
import talle2.wafflerestaurant.Services.UsuarioService;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/login")
    public String login() {
        return "LogIn/LogIn";
    }

    // Eliminar este método ya que Spring Security se encargará de la validación
    // @PostMapping("/login/validar")
    // public String validar(Usuario usuario) { ... }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("title", "Crear cliente");
        return "SignUp/SignUp";
    }

    @PostMapping("/signup/guardar")
    public String guardar(Cliente cliente, Model model) {
        // Comprobar si el usuario ya existe
        if (service.existsByEmail(cliente.getEmail())) {
            model.addAttribute("error", "El correo electrónico ya está registrado");
            // No creamos un nuevo objeto Cliente, usamos el mismo que recibimos
            model.addAttribute("cliente", cliente);
            model.addAttribute("title", "Crear cliente");
            return "SignUp/SignUp"; // Volver al formulario con los datos ingresados
        }

        service.save(cliente);
        return "redirect:/login";
    }

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "error/acceso-denegado";
    }

    @GetMapping("/")
    public String index() {
        return "landing";
    }
}