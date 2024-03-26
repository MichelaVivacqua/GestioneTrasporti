package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team4.entities.Tessera;

public class TessereDAO {
    private EntityManager em;

    public TessereDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Tessera t) {
        try {
            EntityTransaction e = em.getTransaction();
            e.begin();
            em.persist(t);
            e.commit();
            System.out.println("Utente " + t.getId() + " creato!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Tessera findById(long id) {
        return em.find(Tessera.class, id);
    }

    public void findByIdAndDelete(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Tessera found = em.find(Tessera.class, id);
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Tessera eliminata");
            } else System.out.println("Tessera non trovata");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
