package talle2.wafflerestaurant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import talle2.wafflerestaurant.Entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    public Long countById(Long id);

}
