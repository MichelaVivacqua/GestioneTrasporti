package team4.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

enum DURATA_TITOLO{
    SETTIMANALE, MENSILE
}

public class Abbonamento extends Biglietto implements Controllabile {
    @Enumerated(EnumType.STRING)
    private DURATA_TITOLO durata;

    public Abbonamento() {}


    public void setDurata(DURATA_TITOLO durata) {
        this.durata = durata;
    }

    public Abbonamento(Mezzo mezzo, long emessoDa, LocalDate dataDiVidimazione, long tessera, DURATA_TITOLO durata) {
        super(mezzo, emessoDa, dataDiVidimazione, tessera);
        this.durata = durata;
    }

    public DURATA_TITOLO getDurata() {
        return durata;
    }

    @Override
    public boolean isValido() {
        return false;// DA SCRIVERE
    }
}
