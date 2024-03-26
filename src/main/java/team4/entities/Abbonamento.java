package team4.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

enum DURATA_TITOLO{
    SETTIMANALE, MENSILE
}

@Entity
public class Abbonamento extends Biglietto implements Controllabile {
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_durata")
    private DURATA_TITOLO tipo;
    private DURATA_TITOLO durata;

    public Abbonamento() {

    }


    public void setDurata(DURATA_TITOLO durata) {
        this.durata = durata;
    }

    public Abbonamento(Mezzo mezzo, Distributore emessoDa, LocalDate dataDiVidimazione, LocalDate dataDiEmissione, Tessera tessera) {
        super(mezzo, emessoDa, dataDiVidimazione, dataDiEmissione, tessera);
        this.durata = durata;
    }
    public DURATA_TITOLO getDurata() {
        return durata;
    }

    @Override
    public boolean isValido() {
        return false; // DA IMPLEMENTARE
    }
}
