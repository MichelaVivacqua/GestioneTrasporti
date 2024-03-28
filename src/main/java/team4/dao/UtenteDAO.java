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

    public void update(Utente u){

        try{
            EntityTransaction e= em.getTransaction();
            e.begin();

            em.merge(u);

            e.commit();

            System.out.println("Utente con id: "+ u.getId() + " aggiornato");

        }catch (Exception e) {
            e.printStackTrace();

        }
    }

    public Utente findById(long id) {
        return em.find(Utente.class, id);
    }


    public void findByIdAndDelete(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Utente found = em.find(Utente.class, id);
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Utente eliminato");
            } else System.out.println("Utente non trovata");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
