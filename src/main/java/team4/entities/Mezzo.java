package team4.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Mezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "capienza")
    private int capienza;

    @Column(name = "in_manutenzione")
    private boolean inManutenzione;

    @Column(name = "data_manutenzione")
    private LocalDate dataManutenzione;

    @ManyToMany
    @JoinTable(
            name = "mezzo_tratta",
            joinColumns = @JoinColumn(name = "mezzo_id"),
            inverseJoinColumns = @JoinColumn(name = "tratta_id")
    )
    private Set<Tratta> tratteServite;

    public Mezzo() {
        this.tratteServite = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public boolean isInManutenzione() {
        return inManutenzione;
    }

    public void setInManutenzione(boolean inManutenzione) {
        this.inManutenzione = inManutenzione;
    }

    public LocalDate getDataManutenzione() {
        return dataManutenzione;
    }

    public void setDataManutenzione(LocalDate dataManutenzione) {
        this.dataManutenzione = dataManutenzione;
    }

    public Set<Tratta> getTratteServite() {
        return tratteServite;
    }

    public void setTratteServite(Set<Tratta> tratteServite) {
        this.tratteServite = tratteServite;
    }
}
