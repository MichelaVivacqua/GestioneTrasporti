package team4.entities;

import jakarta.persistence.*;
import team4.enums.TipoMezzo;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Manutenzione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_manutenzione;

    @Column(name = "data_inizio")
    private LocalDate data_inizio;

    @Column(name = "data_fine")
    private LocalDate data_fine;

    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = false)
    private Mezzo mezzo;

    public Manutenzione() {}

    public Manutenzione(LocalDate data_inizio, LocalDate data_fine, Mezzo mezzo_manutenuto) {
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.mezzo = mezzo_manutenuto;
    }

    public Manutenzione(LocalDate data_inizio, Mezzo mezzo) {
        this.data_inizio = data_inizio;
        this.mezzo = mezzo;
        this.data_fine = null;
    }

    public Manutenzione( Mezzo mezzo) {
        this.data_inizio = LocalDate.now();
        this.mezzo = mezzo;
        this.data_fine = null;
        mezzo.rimuoviTratta();
    }

    public int getId_manutenzione() {
        return id_manutenzione;
    }

    public void setId_manutenzione(int id_manutenzione) {
        this.id_manutenzione = id_manutenzione;
    }

    public LocalDate getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(LocalDate data_inizio) {
        this.data_inizio = data_inizio;
    }

    public LocalDate getData_fine() {
        return data_fine;
    }

    public void setData_fine(LocalDate data_fine) {
        this.data_fine = data_fine;
    }

    public Mezzo getMezzo_manutenuto() {
        return mezzo;
    }

    public void setMezzo_manutenuto(Mezzo mezzo_manutenuto) {
        this.mezzo = mezzo_manutenuto;
    }

    public boolean isMezzoInManutenzione(EntityManager em, Long idMezzo) {
        String jpql = "SELECT m FROM Manutenzione m WHERE m.mezzo.id_Mezzo = :idMezzo AND m.data_fine IS NULL ORDER BY m.data_inizio DESC";
        List<Manutenzione> manutenzioni = em.createQuery(jpql, Manutenzione.class)
                .setParameter("idMezzo", idMezzo)
                .setMaxResults(1)
                .getResultList();
        return !manutenzioni.isEmpty();
    }

    public Manutenzione getUltimaManutenzione(EntityManager em, Long idMezzo) {
        String jpql = "SELECT m FROM Manutenzione m WHERE m.mezzo.id_Mezzo = :idMezzo ORDER BY m.data_inizio DESC";
        List<Manutenzione> manutenzioni = em.createQuery(jpql, Manutenzione.class)
                .setParameter("idMezzo", idMezzo)
                .setMaxResults(1)
                .getResultList();
        return manutenzioni.isEmpty() ? null : manutenzioni.get(0);
    }


}
