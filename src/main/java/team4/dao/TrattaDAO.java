package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team4.entities.Abbonamento;
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


    public void update(Tratta t){


        EntityTransaction et= em.getTransaction();
        try {

            et.begin();
            em.merge(t);
            et.commit();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public List<Tratta> listaTratte() {
        List<Tratta> tratte = em.createQuery("SELECT t FROM Tratta t", Tratta.class).getResultList();
        for (Tratta tratta : tratte) {
            System.out.println(tratta);
        }
        return tratte;
    }
    public void deleteTratta(Tratta tratta) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Tratta trattaToRemove = em.merge(tratta); // Assicurati che l'entità sia gestita
            em.remove(trattaToRemove);
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

    public Long findMezzoIdByTrattaId(long trattaId) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT m.id_Mezzo FROM Mezzo m WHERE m.trattaServita.id = :trattaId", Long.class);
        query.setParameter("trattaId", trattaId);

        List<Long> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }

        return null;
    }

    public List<Abbonamento> getAbbonamentiByTratta(Long trattaId) {
        return em.createQuery("SELECT a FROM Abbonamento a JOIN a.tratte t WHERE t.id = :trattaId", Abbonamento.class)
                .setParameter("trattaId", trattaId)
                .getResultList();
    }
}
