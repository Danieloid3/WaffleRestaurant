package talle2.wafflerestaurant.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talle2.wafflerestaurant.Entities.Factura;
import talle2.wafflerestaurant.Entities.Pedido;
import talle2.wafflerestaurant.Repositories.FacturaRepository;

import java.util.Date;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public Factura obtenerFactura(int id) {
        return facturaRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Factura no encontrada con ID: " + id));
    }

    public Factura guardarFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    // Método faltante que está siendo llamado
    public Factura crearFacturaDesdeCarrito(Pedido pedido) {
        Factura factura = new Factura();
        factura.setFecha_factura(new Date());
        factura.setEstado_factura(false); // No pagada
        factura.setPedido(pedido);
        return facturaRepository.save(factura);
    }

    public void cambiarEstadoFactura(int id, boolean estado) {
        Factura factura = obtenerFactura(id);
        factura.setEstado_factura(estado);
        facturaRepository.save(factura);
    }
}