package talle2.wafflerestaurant.Security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Es una clase de configuración
@EnableWebSecurity // Habilita la seguridad web
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(httpForm -> {
                    httpForm
                            .loginPage("/login").permitAll();
                })
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/signup", "/css/**", "/js/**", "/background.jpg").permitAll();
                    registry.anyRequest().authenticated();
                })
                .build();

    }

}
