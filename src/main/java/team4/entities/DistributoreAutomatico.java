package team4.entities;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class DistributoreAutomatico extends Distributore {

    @Column(name = "isAttivo")
    private boolean attivo;


}
