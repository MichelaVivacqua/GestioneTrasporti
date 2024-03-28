package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team4.entities.Tratta;

import java.util.List;

public class TrattaDAO {

    private EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void saveTratta(Tratta t) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(t);
            tx.commit();
            System.out.println("Tratta con id:  " + t.getId() + " con partenza da " + t.getPartenza() + " creata!");
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public void deleteTratta(Tratta tratta) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(tratta);
            tx.commit();
            System.out.println("Tratta eliminata");
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public Tratta findTrattaById(long id) {
        return em.find(Tratta.class, id);
    }

    public void findTrattaByIdAndDelete(long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Tratta found = em.find(Tratta.class, id);
            if (found != null) {
                em.remove(found);
                tx.commit();
                System.out.println("Tratta eliminata");
            } else {
                System.out.println("Tratta non trovata");
            }
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public Tratta findTrattaByPartenza(String partenza) {
        List<Tratta> resultList = em.createQuery("SELECT t FROM Tratta t WHERE t.partenza = :partenza", Tratta.class)
                .setParameter("partenza", partenza)
                .getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
