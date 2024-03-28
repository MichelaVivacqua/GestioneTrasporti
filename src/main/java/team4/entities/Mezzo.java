package team4.entities;

import jakarta.persistence.*;
import team4.enums.TipoMezzo;

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

    @ManyToOne
    @JoinColumn(name = "tratta_id") // Questa è la correzione
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

    @Override
    public String toString() {
        return "Mezzo{" +
                ", capienza=" + capienza +
                ", trattaServita=" + trattaServita + //TODO AGGIUNGERE TOSTRING TIPOMEZZO E STATOMEZZO
                ", numeroDiVolte=" + numeroDiVolte +
                ", tempoEffettivo=" + tempoEffettivo + //TODO AGGIUNGERE TOSTRING MANUTENZIONE IN CORSO O ULTIMA MANUTENZIONE
                '}';
    }
}
