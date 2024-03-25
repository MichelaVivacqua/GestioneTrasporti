package team4.entities;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "titolo_di_viaggio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Biglietto implements Controllabile{

    @Id
    @GeneratedValue
    @Column(name = "id_titolo_di_viaggio")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo mezzo; //FK Mezzo di trasporto in cui si timbra il biglietto

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @Column(name = "emesso_da")
    private long emessoDa;
    @Column(name = "data_di_vidimazione")

    private LocalDate dataDiVidimazione;

    @ManyToOne
    @JoinColumn(name = "id_tessera", nullable = false)
    @Column(name = "tessera")
    private Tessera tessera;

    @ManyToOne
    @JoinColumn(name = "id_distributore", nullable = false)
    @Column(name = "distributore")
    private Distributore dist;


    public Biglietto() {}

    public Biglietto(Mezzo mezzo, long emessoDa, LocalDate dataDiVidimazione, Tessera tessera) {
        this.mezzo = mezzo;
        this.emessoDa = emessoDa;
        this.dataDiVidimazione = dataDiVidimazione;
        this.tessera = tessera;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo){
        this.mezzo = mezzo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getEmessoDa() {
        return emessoDa;
    }

    public void setEmessoDa(long emessoDa) {
        this.emessoDa = emessoDa;
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


    @Override
    public boolean isValido() {
        return false;
    }
}
