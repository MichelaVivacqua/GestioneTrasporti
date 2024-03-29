package team4.entities;

import jakarta.persistence.*;
import team4.enums.DurataTitolo;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Abbonamento extends Biglietto implements Controllabile {
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_durata")
   private DurataTitolo durata;

    @ManyToMany
    @JoinTable(
            name = "abbonamento_tratta",
            joinColumns = @JoinColumn(name = "abbonamento_id"),
            inverseJoinColumns = @JoinColumn(name = "tratta_id")
    )
    private Set<Tratta> tratte = new HashSet<>();


    public Abbonamento() {}

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

    public void addTratta(Tratta tratta) {
        this.tratte.add(tratta);
        tratta.getAbbonamenti().add(this);
    }

    public void removeTratta(Tratta tratta) {
        this.tratte.remove(tratta);
        tratta.getAbbonamenti().remove(this);
    }


    public Set<Tratta> getTratte() {
        return tratte;
    }

    public void setTratte(Set<Tratta> tratte) {
        this.tratte = tratte;
    }

    @Override
    public boolean isValido() {
        return false;
    }
}
