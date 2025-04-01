package talle2.wafflerestaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import talle2.wafflerestaurant.Entities.Cliente;
import talle2.wafflerestaurant.Repositories.ProductoRepository;
import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import talle2.wafflerestaurant.Entities.Producto;
import talle2.wafflerestaurant.Repositories.UsuarioRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class WafflerestaurantApplicationTests {
@Autowired
private ProductoRepository repo;
private UsuarioRepository userRepo;
    @Test
    public void testCreateProducto() {
        Faker faker = new Faker();
        Producto producto = new Producto();
        producto.setNombre(faker.food().ingredient());
        producto.setPrecio(faker.number().randomDouble(2, 1, 100));
        producto.setDescripcion(faker.food().spice());
        Producto productoGuardado = repo.save(producto);
        Assertions.assertThat(productoGuardado).isNotNull();
        Assertions.assertThat(productoGuardado.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateCliente() {
        Faker faker = new Faker();
        Cliente cliente = new Cliente();
        cliente.setNombre(faker.name().firstName());
        cliente.setApellido(faker.name().lastName());
        cliente.setEmail(faker.internet().emailAddress());
        Cliente clienteGuardado = userRepo.save(cliente);
        Assertions.assertThat(clienteGuardado).isNotNull();
        Assertions.assertThat(clienteGuardado.getId()).isGreaterThan(0);
    }

}
