package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team4.entities.Tessera;
import team4.entities.Tratta;

import java.util.List;

public class TrattaDAO {

    private EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }


    public void saveTratta(Tratta t) {
        try {
            EntityTransaction e = em.getTransaction();
            e.begin();
            em.persist(t);
            e.commit();
            System.out.println("Tratta con id:  " + t.getId() + " con partenza da " + t.getPartenza() + " creata!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void deleteTratta(Tratta tratta) {
        EntityTransaction e= em.getTransaction();
        e.begin();
        em.remove(tratta);
        e.commit();
    }

    public Tratta findTrattaById(long id) {
        return em.find(Tratta.class, id);
    }

    public void findTrattaByIdAndDelete(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Tratta found = em.find(Tratta.class, id);
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Tratta eliminata");
            } else System.out.println("Tratta non trovata");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public Tratta findTrattaByPartenza(String partenza) {
        TypedQuery<Tratta> query = em.createQuery("SELECT t FROM Tratta t WHERE t.partenza = :partenza", Tratta.class);
        query.setParameter("partenza", partenza);
        List<Tratta> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            return null;
        }
    }






}
