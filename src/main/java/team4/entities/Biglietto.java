package team4.entities;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "biglietto")
public class Biglietto {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "durata")
    private int durata;

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo mezzo;//FK Mezzo di trasporto

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @Column(name = "emesso_da")

    private long emessoDa;
    @Column(name = "data_di_vidimazione")

    private LocalDate dataDiVidimazione;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @Column(name = "tessera")
    private long tessera;

    public Biglietto() {}

    public Biglietto(int durata, Mezzo mezzo, long emessoDa, LocalDate dataDiVidimazione, long tessera) {
        this.durata = durata;
        this.mezzo = mezzo;
        this.emessoDa = emessoDa;
        this.dataDiVidimazione = dataDiVidimazione;
        this.tessera = tessera;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo){
        this.mezzo = mezzo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public long getEmessoDa() {
        return emessoDa;
    }

    public void setEmessoDa(long emessoDa) {
        this.emessoDa = emessoDa;
    }

    public LocalDate getDataDiVidimazione() {
        return dataDiVidimazione;
    }

    public void setDataDiVidimazione(LocalDate dataDiVidimazione) {
        this.dataDiVidimazione = dataDiVidimazione;
    }

    public long getTessera() {
        return tessera;
    }

    public void setTessera(long tessera) {
        this.tessera = tessera;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", durata=" + durata +
                ", mezzo=" + mezzo +
                ", emessoDa=" + emessoDa +
                ", dataDiVidimazione=" + dataDiVidimazione +
                ", tessera=" + tessera +
                '}';
    }
}
