package talle2.wafflerestaurant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import talle2.wafflerestaurant.Entities.Pedido;
import talle2.wafflerestaurant.Entities.Usuario;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    // Este ya está correcto
    List<Pedido> findByUsuario(Usuario usuario);

    // Usar JPQL para evitar problemas con los nombres de campo
    @Query("SELECT p FROM Pedido p WHERE p.estado_pedido = ?1")
    List<Pedido> findByEstadoPedido(boolean estado);

    // Usar JPQL para el conteo
    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.id_pedido = ?1")
    long countByIdPedido(int idPedido);
}