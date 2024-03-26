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

    @Override
    public boolean isValido() {
        return false; // DA IMPLEMENTARE
    }
}
