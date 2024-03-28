package team4.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import team4.enums.DurataTitolo;


import java.time.LocalDate;

@Entity
public class Abbonamento extends Biglietto implements Controllabile {
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_durata")
   private DurataTitolo durata;





    public Abbonamento() {

    }

    public void setDurata(DurataTitolo durata) {
        this.durata = durata;
    }

    public Abbonamento(DurataTitolo durata, Mezzo mezzo, Rivenditore_Autorizzato emessoDa, LocalDate dataDiEmissione, Tessera tessera) {
        super(mezzo, emessoDa, dataDiEmissione, tessera);
        this.durata = durata;
    }
    public DurataTitolo getDurata() {
        return durata;
    }

    @Override
    public boolean isValido() {
        return false; // DA IMPLEMENTARE
    }
}
