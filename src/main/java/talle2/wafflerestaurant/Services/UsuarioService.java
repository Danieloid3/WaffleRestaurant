package talle2.wafflerestaurant.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import talle2.wafflerestaurant.Entities.Cliente;
import talle2.wafflerestaurant.Entities.Usuario;
import talle2.wafflerestaurant.Repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(Usuario usuario) {
        // Asignar role según tipo
        if (usuario instanceof Cliente) {
            usuario.setRole("ROLE_CLIENT");
        }

        // Encriptar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    public void createAdminIfNotExists() {
        if (usuarioRepository.findByEmail("admin@waffle.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Administrador");
            admin.setApellido("Sistema");
            admin.setEmail("admin@waffle.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole("ROLE_ADMIN");
            usuarioRepository.save(admin);
            System.out.println("Administrador creado exitosamente");
        }
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }

}