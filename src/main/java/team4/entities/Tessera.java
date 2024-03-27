package team4.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tessera")
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "tessera")
    private Utente utente;

    @Column(name = "Data_emissione")
    private LocalDate dataDiEmissione;

    @Column(name = "Data_scandeza")
    private LocalDate dataDiScadenza;

    public Tessera() {
    }

    public Tessera(LocalDate dataDiEmissione) {
        this.dataDiEmissione = dataDiEmissione;
        this.dataDiScadenza = dataDiEmissione.plusYears(1);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public LocalDate getDataDiEmissione() {
        return dataDiEmissione;
    }

    public void setDataDiEmissione(LocalDate dataDiEmissione) {
        this.dataDiEmissione = dataDiEmissione;
    }

    public LocalDate getDataDiScadenza() {
        return dataDiScadenza;
    }

    public void setDataDiScadenza(LocalDate dataDiScadenza) {
        this.dataDiScadenza = dataDiScadenza;
    }
}
