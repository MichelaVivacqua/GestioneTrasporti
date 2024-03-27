package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team4.entities.Tessera;
import team4.entities.Utente;

public class UtenteDAO {
    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Utente u) {
        try {
            EntityTransaction e = em.getTransaction();
            e.begin();

            if (u.getTessera() != null){
                em.persist(u.getTessera());
            }

            em.persist(u);
            e.commit();
            System.out.println("Utente " + u.getId() + " creato!");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
