package team4.entities;

import jakarta.persistence.*;
import team4.enums.StatoMezzo;
import team4.enums.TipoMezzo;

import java.time.LocalDate;

@Entity
public class Mezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Mezzo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoMezzo tipoMezzo;

    @Column(name = "capienza")
    private int capienza;

//    @Column(name = "in_manutenzione")
//    private boolean inManutenzione;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato")
    private StatoMezzo stato;

    @Column(name = "data_manutenzione")
    private LocalDate dataManutenzione;

    @ManyToOne
    @JoinColumn(name = "tratta_id") // FK nel DB

    private Tratta trattaServita;

    @Column(name = "numero_di_volte")
    private int numeroDiVolte;

    @Column(name = "tempo_effettivo")
    private int tempoEffettivo;

    public Mezzo() {}

    public Mezzo(TipoMezzo tipoMezzo,int capienza, StatoMezzo stato, LocalDate dataManutenzione, Tratta trattaServita, int numeroDiVolte, int tempoEffettivo) {
        this.tipoMezzo = tipoMezzo;
        this.capienza = capienza;
        this.stato = stato;

        if (stato==StatoMezzo.IN_MANUTENZIONE){
        this.dataManutenzione = dataManutenzione;

        }else {
            this.dataManutenzione= null;
        }
        this.trattaServita = trattaServita;
        this.tempoEffettivo= tempoEffettivo;
        this.numeroDiVolte= numeroDiVolte;
    }

    public Mezzo(TipoMezzo tipoMezzo, int capienza, StatoMezzo stato, LocalDate dataManutenzione) {
        this.tipoMezzo = tipoMezzo;
        this.capienza = capienza;
        this.stato = stato;
        this.dataManutenzione = dataManutenzione;
    }

    public TipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }

    public void setTipoMezzo(TipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
    }

    public StatoMezzo getStato() {
        return stato;
    }

    public void setStato(StatoMezzo stato) {
        this.stato = stato;
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

//    public boolean isInManutenzione() {
//        return inManutenzione;
//    }

//    public void setInManutenzione(boolean inManutenzione) {
//        this.inManutenzione = inManutenzione;
//    }

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

    @Override
    public String toString() {
        return "Mezzo{" +
                "tipoMezzo=" + tipoMezzo +
                ", capienza=" + capienza +
                ", stato=" + stato +
                ", dataManutenzione=" + dataManutenzione +
                ", trattaServita=" + trattaServita +
                ", numeroDiVolte=" + numeroDiVolte +
                ", tempoEffettivo=" + tempoEffettivo +
                '}';
    }
}
