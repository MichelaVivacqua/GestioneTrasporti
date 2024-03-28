package team4.dao;

import jakarta.persistence.EntityManager;
import team4.entities.Manutenzione;

import java.util.List;

public class ManutenzioniDAO {

    private EntityManager em;

    public ManutenzioniDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo per salvare una nuova manutenzione
    public void save(Manutenzione manutenzione) {
        em.getTransaction().begin();
        em.persist(manutenzione);
        em.getTransaction().commit();
    }

    // Metodo per aggiornare una manutenzione esistente
    public Manutenzione update(Manutenzione manutenzione) {
        em.getTransaction().begin();
        Manutenzione updatedManutenzione = em.merge(manutenzione);
        em.getTransaction().commit();
        return updatedManutenzione;
    }

    // Metodo per trovare una manutenzione per ID
    public Manutenzione findById(int id) {
        return em.find(Manutenzione.class, id);
    }

    // Metodo per eliminare una manutenzione
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

    // Metodo per controllare se un mezzo è attualmente in manutenzione
    public boolean isMezzoInManutenzione(Long idMezzo) {
        List<Manutenzione> manutenzioni = em.createQuery(
                        "SELECT m FROM Manutenzione m WHERE m.mezzo.id_Mezzo = :idMezzo AND m.data_fine IS NULL ORDER BY m.data_inizio DESC",
                        Manutenzione.class)
                .setParameter("idMezzo", idMezzo)
                .setMaxResults(1)
                .getResultList();
        return !manutenzioni.isEmpty();
    }

    // Metodo per ottenere l'ultima manutenzione di un mezzo
    public Manutenzione getUltimaManutenzione(Long idMezzo) {
        List<Manutenzione> manutenzioni = em.createQuery(
                        "SELECT m FROM Manutenzione m WHERE m.mezzo.id_Mezzo = :idMezzo ORDER BY m.data_inizio DESC",
                        Manutenzione.class)
                .setParameter("idMezzo", idMezzo)
                .setMaxResults(1)
                .getResultList();
        return manutenzioni.isEmpty() ? null : manutenzioni.get(0);
    }
}
