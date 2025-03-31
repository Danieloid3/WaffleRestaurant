package talle2.wafflerestaurant.Config;  // O el paquete donde quieras ponerla

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                // Aplica CORS a todos los endpoints
                .allowedOrigins("http://localhost:2000")  // Permite peticiones desde localhost:2000
                .allowedMethods("GET", "POST", "PUT", "DELETE")   // Métodos permitidos
                .allowedHeaders("*")                      // Permite todos los encabezados
                .allowCredentials(true);                  // Permite el uso de credenciales como cookies
    }
}
