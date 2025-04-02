package talle2.wafflerestaurant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import talle2.wafflerestaurant.Entities.Factura;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    @Query("SELECT f FROM Factura f WHERE f.estado_factura = ?1")
    List<Factura> findByEstadoFactura(boolean estado);
}