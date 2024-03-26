package team4.entities;
import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "mezzoDiVidimazioneId")
    private Mezzo mezzoDiVidimazione;

    @ManyToOne
    @JoinColumn(name = "emessoDaId")
    private Distributore emessoDa;

    private LocalDate dataDiEmissione;
    private LocalDate dataDiVidimazione;

    @ManyToOne
    @JoinColumn(name = "tesseraId")
    private Tessera tessera;

    public Mezzo getMezzoDiVidimazione() {
        return mezzoDiVidimazione;
    }

    public void setMezzoDiVidimazione(Mezzo mezzoDiVidimazione) {
        this.mezzoDiVidimazione = mezzoDiVidimazione;
    }

    public Distributore getEmessoDa() {
        return emessoDa;
    }

    public void setEmessoDa(Distributore emessoDa) {
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


