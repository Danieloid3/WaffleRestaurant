package talle2.wafflerestaurant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import talle2.wafflerestaurant.Entities.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}