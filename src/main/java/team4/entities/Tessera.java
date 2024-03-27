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
    private Date dataDiEmissione;

    @Column(name = "Data_scandeza")
    private Date dataDiScadenza;

    @OneToOne
    @JoinColumn(name = "utente_id") // La colonna in cui verrà memorizzato l'ID dell'utente nella tabella delle tessere
    private Utente utente;


    public Tessera() {
        this.dataDiEmissione= new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.dataDiEmissione);
        calendar.add(Calendar.YEAR, 1);
        this.dataDiScadenza = calendar.getTime();
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

    public Date getDataDiEmissione() {
        return dataDiEmissione;
    }

    public void setDataDiEmissione(Date dataDiEmissione) {
        this.dataDiEmissione = dataDiEmissione;
    }

        public Date getDataDiScadenza() {
            return dataDiScadenza;
        }

        public void setDataDiScadenza(Date dataDiScadenza) {
            this.dataDiScadenza = dataDiScadenza;
        }

        public Utente getUtente() {
            return utente;
        }

        public void setUtente(Utente utente) {
            this.utente = utente;
        }
}

