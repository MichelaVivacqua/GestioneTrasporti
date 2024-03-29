package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team4.entities.Tessera;

import java.time.LocalDate;

public class TesseraDAO {
    private EntityManager em;

    public TesseraDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tessera t) {
        EntityTransaction e = em.getTransaction();
        try {
            e.begin();
            // Rimuovi la seguente riga se l'utente è già persistito altrove.
            // em.persist(t.getUtente());
            em.persist(t);
            e.commit();
            System.out.println("Tessera " + t.getId() + " creata per l'utente!");
        } catch (Exception ex) {
            if (e.isActive()) {
                e.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void saveTesseraAndUtenteAnnesso(Tessera t) {
        EntityTransaction e = em.getTransaction();
        try {
            e.begin();
            em.persist(t.getUtente());
            em.persist(t);
            e.commit();
            System.out.println("Tessera " + t.getId() + " creata per l'utente!");
        } catch (Exception ex) {
            if (e.isActive()) {
                e.rollback();
            }
            ex.printStackTrace();
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
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Tessera tessera = em.find(Tessera.class, id);
            if (tessera != null) {
                LocalDate now = LocalDate.now();
                LocalDate scadenza = tessera.getDataDiScadenza();
                if (now.isAfter(scadenza)) {
                    LocalDate nuovaScadenza = now.plusYears(1);
                    tessera.setDataDiScadenza(nuovaScadenza);
                    em.merge(tessera);
                    transaction.commit();
                    System.out.println("Tessera rinnovata con successo.");
                } else {
                    System.out.println("La tessera non è ancora scaduta.");
                }
            } else {
                System.out.println("Tessera non trovata.");
            }
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Errore durante il rinnovo della tessera: " + e.getMessage());
        }
    }

//CONTROLLO BOARDING PER TRATTA
    public boolean controlloValiditàTitoloByTesseraPerTratta(Long tesseraId, Long trattaId) {
        String jpql = "SELECT COUNT(a) FROM Abbonamento a " +
                "JOIN a.tratte t " +
                "WHERE a.tessera.id = :tesseraId AND t.id = :trattaId";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("tesseraId", tesseraId)
                .setParameter("trattaId", trattaId)
                .getSingleResult();
        return count > 0;
    }

    //CONTROLLO BOARDING PER MEZZO
    public boolean controlloValiditàTitoloByTesseraPerMezzo(Long tesseraId, Long mezzoId) {
        String jpql = "SELECT COUNT(a) FROM Abbonamento a " +
                "JOIN a.tratte t JOIN t.mezzi m " +
                "WHERE a.tessera.id = :tesseraId AND m.id = :mezzoId";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("tesseraId", tesseraId)
                .setParameter("mezzoId", mezzoId)
                .getSingleResult();
        return count > 0;
    }

}
