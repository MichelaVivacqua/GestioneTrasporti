package team4.entities;

import jakarta.persistence.*;
import team4.enums.TipoDistributore;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_Distributore", discriminatorType = DiscriminatorType.STRING)
public class Distributore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Distributore() {

    }

    public Long getId() {
        return id;
    }

}
