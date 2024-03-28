package team4.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Distributore")
@DiscriminatorColumn(name = "tipo_Distributore", discriminatorType = DiscriminatorType.STRING)
public class Rivenditore_Autorizzato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Rivenditore_Autorizzato() {

    }

    public Long getId() {
        return id;
    }

}
