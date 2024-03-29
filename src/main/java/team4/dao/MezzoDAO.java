package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team4.entities.Biglietto;
import team4.entities.Manutenzione;
import team4.entities.Mezzo;

import java.util.List;

public class MezzoDAO {
    private EntityManager em;

    public MezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Mezzo m) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(m);
            tx.commit();
            System.out.println("Mezzo id: " + m.getId() + " creato!");
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public void update(Mezzo mezzo) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(mezzo);
            et.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Mezzo> findMezziForTratta(Long trattaId) {
        TypedQuery<Mezzo> query = em.createQuery(
                "SELECT m FROM Mezzo m WHERE m.trattaServita.id = :trattaId", Mezzo.class);
        query.setParameter("trattaId", trattaId);
        return query.getResultList();
    }

    public Mezzo findById(long id) {
        return em.find(Mezzo.class, id);
    }

    public void findByIdAndDelete(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Mezzo found = em.find(Mezzo.class, id);
            if (found != null) {
                em.remove(found);
                tx.commit();
                System.out.println("Mezzo eliminato");
            } else {
                System.out.println("Mezzo non trovato");
            }
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public void deleteMezzo(Mezzo m) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Mezzo deleteMezzo = em.merge(m); // Assicurati che l'entità sia gestita
            em.remove(deleteMezzo);
            tx.commit();
            System.out.println("Mezzo eliminato");
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
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
    public List<Mezzo> listaMezzi() {
        return em.createQuery("SELECT m FROM Mezzo m", Mezzo.class)
                .getResultList();
    }

}
