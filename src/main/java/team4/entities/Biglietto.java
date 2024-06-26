package team4.entities;
import java.time.LocalDate;

import jakarta.persistence.*;
import team4.enums.DurataTitolo;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "mezzo_Di_Vidimazione_Id")
    private Mezzo mezzoDiVidimazione;

    @ManyToOne
    @JoinColumn(name = "emesso_Da_Id")
    private Rivenditore_Autorizzato emessoDa;

    private LocalDate dataDiEmissione;
    private LocalDate dataDiVidimazione;

//
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tesseraId")
    private Tessera tessera;

    public Biglietto(Mezzo mezzoDiVidimazione, Rivenditore_Autorizzato emessoDa, LocalDate dataDiEmissione, LocalDate dataDiVidimazione, Tessera tessera) {
        this.mezzoDiVidimazione = mezzoDiVidimazione;
        this.emessoDa = emessoDa;
        this.dataDiEmissione = dataDiEmissione;
        this.dataDiVidimazione = dataDiVidimazione;
        this.tessera = tessera;
    }

    public Biglietto(Rivenditore_Autorizzato emessoDa, LocalDate dataDiEmissione, LocalDate dataDiVidimazione, Tessera tessera) {
        this.mezzoDiVidimazione = null;
        this.emessoDa = emessoDa;
        this.dataDiEmissione = dataDiEmissione;
        this.dataDiVidimazione = dataDiVidimazione;
        this.tessera = tessera;
    }

    public Biglietto(Mezzo mezzoDiVidimazione, Rivenditore_Autorizzato emessoDa, LocalDate dataDiEmissione, Tessera tessera) {

        this.mezzoDiVidimazione = mezzoDiVidimazione;
        this.emessoDa = emessoDa;
        this.dataDiEmissione = dataDiEmissione;
        this.tessera = tessera;


    }

    public Biglietto() {}

    public Biglietto(Rivenditore_Autorizzato botteghino, LocalDate now, LocalDate now1) {

    }


    public Mezzo getMezzoDiVidimazione() {
        return mezzoDiVidimazione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMezzoDiVidimazione(Mezzo mezzoDiVidimazione) {
        this.mezzoDiVidimazione = mezzoDiVidimazione;
    }

    public Rivenditore_Autorizzato getEmessoDa() {
        return emessoDa;
    }

    public void setEmessoDa(Rivenditore_Autorizzato emessoDa) {
        this.emessoDa = emessoDa;
    }

    public LocalDate getDataDiEmissione() {
        return dataDiEmissione;
    }

    public void setDataDiEmissione(LocalDate dataDiEmissione) {
        this.dataDiEmissione = dataDiEmissione;
    }

    public LocalDate getDataDiVidimazione() {
        return dataDiVidimazione;
    }

    public void setDataDiVidimazione(LocalDate dataDiVidimazione) {
        this.dataDiVidimazione = dataDiVidimazione;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }
}


