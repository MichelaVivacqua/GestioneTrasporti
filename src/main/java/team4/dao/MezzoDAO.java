package team4.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team4.entities.Mezzo;
import team4.entities.Tessera;

public class MezzoDAO {
    private EntityManager em;

    public MezzoDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Mezzo m) {
        try {
            EntityTransaction e = em.getTransaction();
            e.begin();
            em.persist(m);
            e.commit();
            System.out.println("Mezzo id: " + m.getId() + " creato!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Mezzo findById(long id) {
        return em.find(Mezzo.class, id);
    }

    public void findByIdAndDelete(long id) {
        try {

            EntityTransaction t = em.getTransaction();
            t.begin();
            Mezzo found = em.find(Mezzo.class, id);
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Mezzo eliminato");
            } else System.out.println("Mezzo non trovato");

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }

}

