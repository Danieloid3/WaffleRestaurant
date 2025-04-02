package talle2.wafflerestaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import talle2.wafflerestaurant.Entities.Cliente;
import talle2.wafflerestaurant.Repositories.ProductoRepository;
import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import talle2.wafflerestaurant.Entities.Producto;
import talle2.wafflerestaurant.Repositories.UsuarioRepository;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class WafflerestaurantApplicationTests {

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testCreateProducto() {
        Faker faker = new Faker();
        Producto producto = new Producto();
        producto.setNombre("Waffle frutal");
        producto.setPrecio(9000.0);
        producto.setDescripcion("Delicioso waffle cubierto con frutas frescas de temporada y miel de maple");
        //url de imagen de alimentos
        producto.setImagen("waffleFrutal.png");




        Producto productoGuardado = productoRepo.save(producto);

        Assertions.assertThat(productoGuardado).isNotNull();
        Assertions.assertThat(productoGuardado.getId()).isGreaterThan(0);
    }

    @Test
    void testCreateCliente() {
        Faker faker = new Faker();
        Cliente cliente = new Cliente();
        cliente.setNombre(faker.name().firstName());
        cliente.setApellido(faker.name().lastName());
        cliente.setEmail(faker.internet().emailAddress());
        cliente.setPassword(passwordEncoder.encode("password"));

        Cliente clienteGuardado = usuarioRepo.save(cliente);

        Assertions.assertThat(clienteGuardado).isNotNull();
        Assertions.assertThat(clienteGuardado.getId()).isGreaterThan(0);
    }
}