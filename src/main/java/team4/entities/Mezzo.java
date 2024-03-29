package team4.entities;

import jakarta.persistence.*;
import team4.enums.TipoMezzo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
public class Mezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Mezzo;

    @Column(name = "capienza")
    private int capienza;

    @OneToMany(mappedBy = "mezzo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Manutenzione> manutenzioni;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "tratta_id")
    private Tratta trattaServita;

    @Column(name = "numero_di_volte")
    private int numeroDiVolte;

    @Column(name = "tempo_effettivo")
    private int tempoEffettivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_mezzo")
    private TipoMezzo tipoMezzo;

    public Mezzo() {}

    public Mezzo(int capienza, Tratta trattaServita, int numeroDiVolte, int tempoEffettivo) {
        this.capienza = capienza;
        this.manutenzioni = null;
        this.trattaServita = trattaServita;
        this.numeroDiVolte = numeroDiVolte;
        this.tempoEffettivo = tempoEffettivo;
        if (capienza>=80){
            this.tipoMezzo = TipoMezzo.AUTOBUS;
        } else {
            this.tipoMezzo = TipoMezzo.TRAM;
        }
    }
    public Mezzo(TipoMezzo tipoMezzo, Tratta trattaServita, int numeroDiVolte, int tempoEffettivo) {
        this.tipoMezzo = tipoMezzo;
        this.manutenzioni = null;
        this.trattaServita = trattaServita;
        this.numeroDiVolte = numeroDiVolte;
        this.tempoEffettivo = tempoEffettivo;
        if (this.tipoMezzo == TipoMezzo.AUTOBUS) {
            this.capienza = 80;
        }
        if (this.tipoMezzo == TipoMezzo.TRAM) {
            this.capienza = 60;
        }
    }

    public Mezzo(TipoMezzo tipoMezzo, List<Manutenzione> manutenzioni, Tratta trattaServita, int numeroDiVolte, int tempoEffettivo) {
        this.tipoMezzo = tipoMezzo;
        this.manutenzioni = manutenzioni;
        this.trattaServita = trattaServita;
        this.numeroDiVolte = numeroDiVolte;
        this.tempoEffettivo = tempoEffettivo;
        if (tipoMezzo == TipoMezzo.AUTOBUS) {
            this.capienza = 80;
        }
        if (tipoMezzo == TipoMezzo.TRAM) {
            this.capienza = 60;
        }
    }
    public Mezzo(int capienza, List<Manutenzione> manutenzioni, Tratta trattaServita, int numeroDiVolte, int tempoEffettivo) {
        this.capienza = capienza;
        this.manutenzioni = manutenzioni;
        this.trattaServita = trattaServita;
        this.numeroDiVolte = numeroDiVolte;
        this.tempoEffettivo = tempoEffettivo;
        if (capienza>=80){
            this.tipoMezzo = TipoMezzo.AUTOBUS;
        } else {
            this.tipoMezzo = TipoMezzo.TRAM;
        }
    }

    public int getNumeroDiVolte() {
        return numeroDiVolte;
    }

    public void setNumeroDiVolte(int numeroDiVolte) {
        this.numeroDiVolte = numeroDiVolte;
    }

    public int getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(int tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }

    public Long getId() {
        return id_Mezzo;
    }

    public void setId(Long id_Mezzo) {
        this.id_Mezzo = id_Mezzo;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public Tratta getTrattaServita() {
        return trattaServita;
    }

    public void setTrattaServita(Tratta trattaServita) {
        this.trattaServita = trattaServita;
    }

    public void rimuoviTratta() {
        if (this.trattaServita != null) {
            this.trattaServita.getMezziServenti().remove(this);
            this.trattaServita = null;
        }
        System.out.println("Mezzo rimosso dalla tratta");
    }


    public List<Manutenzione> getManutenzioni() {
        return manutenzioni;
    }

    public void setManutenzioni(List<Manutenzione> manutenzioni) {
        this.manutenzioni = manutenzioni;
    }

    public TipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }

    public void setTipoMezzo(TipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                ", capienza=" + capienza +
                ", trattaServita=" + trattaServita + //TODO AGGIUNGERE TOSTRING TIPOMEZZO E STATOMEZZO
                ", numeroDiVolte=" + numeroDiVolte +
                ", tempoEffettivo=" + tempoEffettivo + //TODO AGGIUNGERE TOSTRING MANUTENZIONE IN CORSO O ULTIMA MANUTENZIONE
                '}';
    }

    public String stringaPerPannello(){
        String trattaInfo = "Non in servizio su nessuna linea";
        if (this.getTrattaServita() != null) {
            trattaInfo = "In servizio sulla linea " + this.getTrattaServita().getPartenza() + "-" + this.getTrattaServita().getArrivo();
        }
        return this.tipoMezzo.toString() + " id " + this.id_Mezzo + " " + this.capienza + " posti | " + trattaInfo + subStringStatoMezzo();
    }


    private String subStringStatoMezzo() {
        LocalDate oggi = LocalDate.now();
        LocalDate dataUltimaManutenzione = null;
        LocalDate dataInizioUltimaManutenzione = null;
        boolean inManutenzione = false;

        for (Manutenzione manutenzione : this.manutenzioni) {
            if ((manutenzione.getData_inizio().isBefore(oggi) || manutenzione.getData_inizio().isEqual(oggi)) &&
                    (manutenzione.getData_fine() == null || manutenzione.getData_fine().isAfter(oggi))) {
                inManutenzione = true;
                dataInizioUltimaManutenzione = manutenzione.getData_inizio();
                break; // Interrompe il ciclo se trova una manutenzione attiva
            } else if (manutenzione.getData_fine() != null &&
                    (dataUltimaManutenzione == null || manutenzione.getData_fine().isAfter(dataUltimaManutenzione))) {
                dataUltimaManutenzione = manutenzione.getData_fine();
            }
        }

        if (inManutenzione) {
            long giorniDiManutenzione = ChronoUnit.DAYS.between(dataInizioUltimaManutenzione, oggi);
            return " | In manutenzione da " + giorniDiManutenzione + " giorni";
        } else if (dataUltimaManutenzione != null) {
            long giorniDaUltimaManutenzione = ChronoUnit.DAYS.between(dataUltimaManutenzione, oggi);
            return " | In servizio da " + giorniDaUltimaManutenzione + " giorni";
        } else {
            return " | Nessuna manutenzione registrata";
        }
    }

}
