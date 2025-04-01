package talle2.wafflerestaurant.Services;


public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(String message) {
        super(message);
    }
}
