package talle2.wafflerestaurant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import talle2.wafflerestaurant.Entities.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {}