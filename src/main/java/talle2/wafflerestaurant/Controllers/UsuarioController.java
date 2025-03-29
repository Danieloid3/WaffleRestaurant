package talle2.wafflerestaurant.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import talle2.wafflerestaurant.Entities.Cliente;
import talle2.wafflerestaurant.Entities.Usuario;
import talle2.wafflerestaurant.Services.UsuarioService;

@Controller
public class UsuarioController {
@Autowired private UsuarioService Service;
    @GetMapping("/login")
    public String login() {
        return "LogIn/LogIn";
    }

    @GetMapping("/login/validar")
    public String validar(Usuario usuario, RedirectAttributes ra) {
        if(Service.validarUsuario(usuario.getEmail(), usuario.getPassword())){
            return "redirect:/inicio";
        }
        ra.addFlashAttribute("message", "Usuario o contraseña incorrectos");
        return "redirect:/login";
    }



    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("title", "Crear cliente");

        return "SignUp/SignUp";
    }

    @PostMapping("/signup/guardar")
    public String guardar(Usuario usuario) {
        Service.save(usuario);
        return "redirect:/login";
    }

    @GetMapping("/inicio")
    public String inicio() {
        return "Inicio/Inicio";
    }



}
