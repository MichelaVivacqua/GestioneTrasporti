package team4.entities;

import jakarta.persistence.*;
import team4.enums.TipoDistributore;

@Entity
public class Distributore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_Distributore")
    private TipoDistributore distributore;


    @Column(name = "Attivo")
    private boolean attivo;


    public Distributore(TipoDistributore distributore, boolean attivo) {
        this.distributore = distributore;
        this.attivo = attivo;

    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public Long getId() {
        return id;
    }

    public TipoDistributore getDistributore() {
        return distributore;
    }

    public void setDistributore(TipoDistributore distributore) {
        this.distributore = distributore;
    }
}
