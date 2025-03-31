package talle2.wafflerestaurant.Entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("ADMINISTRADOR") // Valor en la columna "tipo_usuario"
public class Administrador extends Usuario {

    private String rol;

    public Administrador(String nombre, String apellido, String email, String password, String rol) {
        super(null, nombre, apellido, email, password, "ROLE_ADMIN");
        this.rol = rol;
    }
}