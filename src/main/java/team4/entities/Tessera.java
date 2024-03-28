package team4.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "tessera")
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Data_emissione")
    private LocalDate dataDiEmissione;

    @Column(name = "Data_scandeza")
    private LocalDate dataDiScadenza;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private Utente utente;


    public Tessera() {
        this.dataDiEmissione = LocalDate.now();
        this.dataDiScadenza = LocalDate.now().plusYears(1);
    }

//    public Tessera(LocalDate dataDiEmissione) {
//        this.dataDiEmissione = dataDiEmissione;
//        this.dataDiScadenza = dataDiEmissione.plusYears(1);
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        public Utente getUtente() {
            return utente;
        }

        public void autoUpdateDataDiScadenza() {
        this.dataDiScadenza = this.dataDiEmissione.plusYears(1);
        }

        public void setUtente(Utente utente) {
            this.utente = utente;
        }

    @Override
    public String toString() {
        return "Tessera{" +
                ", intestatario= " + utente.getNome() + " " + utente.getCognome() +
                " id=" + id +
                ", dataDiEmissione=" + dataDiEmissione +
                ", dataDiScadenza=" + dataDiScadenza +
                '}';
    }
}

