package talle2.wafflerestaurant.Entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("CLIENTE") // Valor que se guarda en la columna "tipo_usuario"
public class Cliente extends Usuario {

    private String direccion;

    public Cliente(String nombre, String apellido, String email, String password, String direccion) {
        super(null, nombre, apellido, email, password);
        this.direccion = null;
    }
}

