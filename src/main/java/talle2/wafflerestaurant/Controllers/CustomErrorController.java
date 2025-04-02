package talle2.wafflerestaurant.Controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/social")
    public String social() {
        return "extras/social";
    }

    @GetMapping("/ejemplo")
    public String ejemplo() {
        return "extras/ejemplo";
    }


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Obtener el código de estado
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // Obtener el mensaje de error
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        // Obtener la excepción
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        model.addAttribute("status", status);
        model.addAttribute("message", message != null ? message : "Error desconocido");

        if (throwable != null) {
            model.addAttribute("exception", throwable.getClass().getName());
            model.addAttribute("trace", throwable.getMessage());
        }

        return "error";
    }
}