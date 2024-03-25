package team4.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "mezzo")
public class Mezzo {
    @Id
    @OneToMany(mappedBy = "Mezzo") // La relazione inversa
    private List<Biglietto> biglietti;

    @GeneratedValue
    private Long id_mezzo;

    @Column(name = "capienza")
    private int capienza;

    @Column(name = "in_manutenzione")
    private boolean inManutenzione;

    @Column(name = "data_ultima_manutenzione")
    private LocalDate dataUltimaManutenzione;
    public List<Biglietto> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(List<Biglietto> biglietti) {
        this.biglietti = biglietti;
    }

    public Mezzo() {}

    public Mezzo(Long id_mezzo, int capienza, boolean inManutenzione, LocalDate dataUltimaManutenzione) {
        this.id_mezzo = id_mezzo;
        this.capienza = capienza;
        this.inManutenzione = inManutenzione;
        this.dataUltimaManutenzione = dataUltimaManutenzione;
    }

    public Long getId_mezzo() {
        return id_mezzo;
    }

    public void setId_mezzo(Long id_mezzo) {
        this.id_mezzo = id_mezzo;
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

    @Override
    public String toString() {
        return "Mezzo{" +
                "numero biglietti=" + biglietti.size() +
                ", id_mezzo=" + id_mezzo +
                ", capienza=" + capienza +
                ", inManutenzione=" + inManutenzione +
                ", dataUltimaManutenzione=" + dataUltimaManutenzione +
                '}';
    }
}
