package team4.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mezzo")
public class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "capienza")
    private int capienza;

    @Column(name = "in_manutenzione")
    private boolean inManutenzione;

    @Column(name = "data_ultima_manutenzione")
    private LocalDate dataUltimaManutenzione;

    @ManyToOne
    @JoinColumn(name = "id_tratta_in_servizio", nullable = false)
    private Tratta tratta; //FK tratta in servizio

    @OneToMany(mappedBy = "mezzoVidimazione", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Biglietto> biglietti = new HashSet<>();

    public Mezzo() {}

    public Mezzo(int capienza, boolean inManutenzione, LocalDate dataUltimaManutenzione, Set<Biglietto> biglietti) {
        this.capienza = capienza;
        this.inManutenzione = inManutenzione;
        this.dataUltimaManutenzione = dataUltimaManutenzione;
        this.biglietti = biglietti;
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

    public LocalDate getDataUltimaManutenzione() {
        return dataUltimaManutenzione;
    }

    public void setDataUltimaManutenzione(LocalDate dataUltimaManutenzione) {
        this.dataUltimaManutenzione = dataUltimaManutenzione;
    }

    public Set<Biglietto> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(Set<Biglietto> biglietti) {
        this.biglietti = biglietti;
    }
}
