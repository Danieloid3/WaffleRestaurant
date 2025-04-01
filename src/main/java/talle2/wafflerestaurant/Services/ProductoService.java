package talle2.wafflerestaurant.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talle2.wafflerestaurant.Entities.Producto;
import talle2.wafflerestaurant.Repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService
{
    //crud
    @Autowired
    ProductoRepository repo;

   public List<Producto> listAll() {
        return (List<Producto>) repo.findAll();
    }

    public void save(Producto producto) {
        repo.save(producto);
    }

    public Producto get(long id) throws ProductoNotFoundException {
        Optional<Producto> producto = repo.findById(id);
        if (producto.isPresent()) {
            return producto.get();
        } else {
            throw new ProductoNotFoundException("No se ha encontrado el producto con id: " + id);
        }
    }

    public void delete(long id) throws ProductoNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new ProductoNotFoundException("No se ha encontrado el producto con id: " + id);
        } else {
            repo.deleteById(id);
        }
    }


}
