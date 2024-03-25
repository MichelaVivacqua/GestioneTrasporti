package team4.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tessera")
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "durata")
    private int durata;

    @Column(name = "partenza")
    private String partenza;

    @Column(name = "capolinea")
    private String capolinea;

    @OneToMany(mappedBy = "mezzo")
    private Set<Mezzo> mezzo = new HashSet<>();
}
