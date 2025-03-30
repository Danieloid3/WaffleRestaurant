package talle2.wafflerestaurant.Entities;
import jakarta.persistence.*;
import lombok.*;

@Getter // Anotaciones de Lombok
@Setter
@NoArgsConstructor
@AllArgsConstructor // Anotaciones de Lombok
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Estrategia de herencia
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING) // Columna que indica el tipo de usuario
@Entity
@Table(name="Usuario")

public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String password;
}