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

    @ManyToMany
    @JoinTable(
            name = "tratta_biglietto",
            joinColumns = @JoinColumn(name = "tratta_id"),
            inverseJoinColumns = @JoinColumn(name = "biglietto_id")
    )
    private Set<Abbonamento> abbonamenti = new HashSet<>();

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", durata=" + durata +
                ", partenza='" + partenza + '\'' +
                ", arrivo='" + arrivo + '\'' +
                '}';
    }

    public Tratta() {}

    public Tratta( int durata, String partenza, String arrivo) {
        this.durata = durata;
        this.partenza = partenza;
        this.arrivo = arrivo;
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

    public Set<Abbonamento> getAbbonamenti() {
        return abbonamenti;
    }

    public void setAbbonamenti(Set<Abbonamento> abbonamenti) {
        this.abbonamenti = abbonamenti;
    }
}
