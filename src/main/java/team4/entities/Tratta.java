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

    @OneToMany(mappedBy = "trattaServita")
    private Set<Mezzo> mezziServenti = new HashSet<>();

    public Tratta() {}

    public Tratta(Long id, int durata, String partenza, String arrivo, Set<Mezzo> mezziServenti) {
        this.id = id;
        this.durata = durata;
        this.partenza = partenza;
        this.arrivo = arrivo;
        this.mezziServenti = mezziServenti;
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

    public Set<Mezzo> getMezziServenti() {
        return mezziServenti;
    }

    public void setMezziServenti(Set<Mezzo> mezziServenti) {
        this.mezziServenti = mezziServenti;
    }
}