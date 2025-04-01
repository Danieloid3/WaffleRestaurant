package talle2.wafflerestaurant.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login/process")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/inicio", true)
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .permitAll())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup", "/signup/guardar", "/css/**", "/js/**", "/images/**", "/menu").permitAll()
                        .requestMatchers("/admin/**", "/productos/crear", "/productos/editar/**", "/productos/eliminar/**").hasRole("ADMIN")
                        .requestMatchers("/pedidos/**").hasRole("CLIENT")
                        .anyRequest().authenticated())
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/acceso-denegado"))
                .build();
    }
}