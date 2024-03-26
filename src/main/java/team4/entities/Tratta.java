package team4.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tratta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "durata")
    private int durata;

    @Column(name = "partenza")
    private String partenza;

    @Column(name = "arrivo")
    private String arrivo;

    @ManyToMany(mappedBy = "tratteServite")
    private Set<Mezzo> mezzi;

    public Tratta() {
        this.mezzi = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getArrivo() {
        return arrivo;
    }

    public void setArrivo(String arrivo) {
        this.arrivo = arrivo;
    }

    public Set<Mezzo> getMezzi() {
        return mezzi;
    }

    public void setMezzi(Set<Mezzo> mezzi) {
        this.mezzi = mezzi;
    }
}
