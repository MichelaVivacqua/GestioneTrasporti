package team4.dao;

import jakarta.persistence.EntityManager;
import team4.entities.Manutenzione;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ManutenzioniDAO {

    private EntityManager em;

    public ManutenzioniDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Manutenzione manutenzione) {
        em.getTransaction().begin();
        em.persist(manutenzione);
        em.getTransaction().commit();
    }

    public Manutenzione update(Manutenzione manutenzione) {
        em.getTransaction().begin();
        Manutenzione updatedManutenzione = em.merge(manutenzione);
        em.getTransaction().commit();
        return updatedManutenzione;
    }

    public Manutenzione findById(int id) {
        return em.find(Manutenzione.class, id);
    }

    public void delete(Manutenzione manutenzione) {
        em.getTransaction().begin();
        em.remove(manutenzione);
        em.getTransaction().commit();
    }

    public List<Manutenzione> findByMezzoId(Long mezzoId) {
        return em.createQuery("SELECT m FROM Manutenzione m WHERE m.mezzo.id_Mezzo = :mezzoId", Manutenzione.class)
                .setParameter("mezzoId", mezzoId)
                .getResultList();
    }

    public boolean isMezzoInManutenzione(Long idMezzo) {
        List<Manutenzione> manutenzioni = em.createQuery(
                        "SELECT m FROM Manutenzione m WHERE m.mezzo.id_Mezzo = :idMezzo AND m.data_fine IS NULL ORDER BY m.data_inizio DESC",
                        Manutenzione.class)
                .setParameter("idMezzo", idMezzo)
                .setMaxResults(1)
                .getResultList();
        return !manutenzioni.isEmpty();
    }

    public Manutenzione getUltimaManutenzione(Long idMezzo) {
        List<Manutenzione> manutenzioni = em.createQuery(
                        "SELECT m FROM Manutenzione m WHERE m.mezzo.id_Mezzo = :idMezzo ORDER BY m.data_inizio DESC",
                        Manutenzione.class)
                .setParameter("idMezzo", idMezzo)
                .setMaxResults(1)
                .getResultList();
        return manutenzioni.isEmpty() ? null : manutenzioni.get(0);
    }

    public boolean isInManutenzione(Long idMezzo) {
        String jpql = "SELECT m FROM Manutenzione m WHERE m.mezzo.id = :idMezzo AND m.data_fine IS NULL ORDER BY m.data_inizio DESC";
        List<Manutenzione> manutenzioni = em.createQuery(jpql, Manutenzione.class)
                .setParameter("idMezzo", idMezzo)
                .setMaxResults(1)
                .getResultList();
        return !manutenzioni.isEmpty();
    }

    public long getGiorniDallUltimaManutenzione(Long idMezzo) {
        String jpql = "SELECT m FROM Manutenzione m WHERE m.mezzo.id = :idMezzo AND m.data_fine IS NOT NULL ORDER BY m.data_fine DESC";
        List<Manutenzione> manutenzioni = em.createQuery(jpql, Manutenzione.class)
                .setParameter("idMezzo", idMezzo)
                .setMaxResults(1)
                .getResultList();
        if (manutenzioni.isEmpty()) {
            System.out.println("Manutenzione non trovata");
            return 0;
        } else {
            LocalDate dataFineUltimaManutenzione = manutenzioni.get(0).getData_fine();
            return ChronoUnit.DAYS.between(dataFineUltimaManutenzione, LocalDate.now());
        }
    }
}
