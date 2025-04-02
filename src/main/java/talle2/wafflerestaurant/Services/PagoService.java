package talle2.wafflerestaurant.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talle2.wafflerestaurant.Entities.Pago;
import talle2.wafflerestaurant.Repositories.PagoRepository;

import java.util.List;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public Pago guardarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    public Pago obtenerPago(int id) {
        return pagoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Pago no encontrado con ID: " + id));
    }

    public List<Pago> obtenerTodosPagos() {
        return pagoRepository.findAll();
    }

    public void eliminarPago(int id) {
        pagoRepository.deleteById(id);
    }
}