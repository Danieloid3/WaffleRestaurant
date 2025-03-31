package talle2.wafflerestaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import talle2.wafflerestaurant.Services.UsuarioService;

@SpringBootApplication
public class WafflerestaurantApplication {

    @Autowired
    private UsuarioService usuarioService;

    public static void main(String[] args) {
        SpringApplication.run(WafflerestaurantApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        usuarioService.createAdminIfNotExists();
    }
}