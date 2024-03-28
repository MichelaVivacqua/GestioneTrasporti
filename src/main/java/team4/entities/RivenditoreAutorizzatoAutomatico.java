package team4.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Automatico") // Indica il valore nella colonna discriminante per questa classe

public class RivenditoreAutorizzatoAutomatico extends Rivenditore_Autorizzato {
    @Column(name = "attivo") // Specifica il nome della colonna nel database
    private boolean attivo;
public RivenditoreAutorizzatoAutomatico(){}
    public RivenditoreAutorizzatoAutomatico(boolean attivo) {
        this.attivo= attivo;


    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }
}
