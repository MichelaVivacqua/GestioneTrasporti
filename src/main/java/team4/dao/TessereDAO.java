package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team4.entities.Tessera;

import java.util.Calendar;
import java.util.Date;

public class TessereDAO {
    private EntityManager em;

    public TessereDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Tessera t) {
        try {
            EntityTransaction e = em.getTransaction();
            e.begin();

            em.persist(t.getUtente());
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

    public void rinnovaTessera(long id) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Tessera tessera = em.find(Tessera.class, id);
            if (tessera != null) {
                // salvo la data di oggi in una variabile now
                Date now = new Date();
                // prendo la data di scadenza della tessera per il confronto
                Date scadenza = tessera.getDataDiScadenza();
                if (now.after(scadenza)) {
                    // La tessera è scaduta, allora aggiorna la data di scadenza
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(now);
                    calendar.add(Calendar.YEAR, 1);
                    tessera.setDataDiScadenza(calendar.getTime());
                    em.merge(tessera);
                    transaction.commit();
                    System.out.println("Tessera rinnovata con successo.");
                } else {
                    System.out.println("La tessera non è ancora scaduta.");
                }
            } else {
                System.out.println("Tessera non trovata.");
            }
        } catch (Exception e) {
            System.out.println("Errore durante il rinnovo della tessera: " + e.getMessage());
        }
    }


}
