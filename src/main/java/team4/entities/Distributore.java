package team4.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "distributore")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_titolo", discriminatorType = DiscriminatorType.STRING)
public abstract class Distributore {

    @Id
    @OneToMany(mappedBy = "dist")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
