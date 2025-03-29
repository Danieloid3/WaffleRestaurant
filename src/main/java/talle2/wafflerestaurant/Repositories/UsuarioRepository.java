package talle2.wafflerestaurant.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import talle2.wafflerestaurant.Entities.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Long countById(Long id);
    Optional<Usuario> findByEmail(String email);


}
