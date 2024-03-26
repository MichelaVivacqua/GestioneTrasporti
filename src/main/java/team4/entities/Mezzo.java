package team4.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Mezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "capienza")
    private int capienza;

    @Column(name = "in_manutenzione")
    private boolean inManutenzione;

    @Column(name = "data_manutenzione")
    private LocalDate dataManutenzione;

    @ManyToOne
    @JoinColumn(name = "tratta_id") // FK nel DB
    private Tratta trattaServita;

    public Mezzo() {}

    public Mezzo(int capienza, boolean inManutenzione, LocalDate dataManutenzione, Tratta trattaServita) {
       this.capienza = capienza;
        this.inManutenzione = inManutenzione;
        this.dataManutenzione = dataManutenzione;
        this.trattaServita = trattaServita;
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

    public Tratta getTrattaServita() {
        return trattaServita;
    }

    public void setTrattaServita(Tratta trattaServita) {
        this.trattaServita = trattaServita;
    }
}
