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

    public void update(Utente u) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(u);
            transaction.commit();
            System.out.println("Utente con id: " + u.getId() + " aggiornato");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Errore durante l'aggiornamento dell'utente: " + e.getMessage());
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

    public boolean utenteHaBiglietto(Long utenteId) { // BIGLIETTO GENERICO NON VIDIMATO ASSOCIATO ALL'UTENTE
        String jpql = "SELECT COUNT(b) FROM Biglietto b " +
                "WHERE b.tessera.utente.id = :utenteId AND b.dataDiVidimazione IS NULL";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("utenteId", utenteId)
                .getSingleResult();
        return count > 0;
    }

    // RITORNA TRUE SE L'UTENTE HA UN ABBONAMENTO VALIDO PER LA LINEA DEL MEZZO DELL'ARGOMENTO
    public boolean utenteAutorizzatoSuMezzo(Long utenteId, Long mezzoId) {
        String jpql = "SELECT COUNT(a) FROM Abbonamento a " +
                "JOIN a.tratte t JOIN t.mezzi m " +
                "WHERE a.tessera.utente.id = :utenteId AND m.id = :mezzoId";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("utenteId", utenteId)
                .setParameter("mezzoId", mezzoId)
                .getSingleResult();
        return count > 0;
    }

    // RITORNA TRUE SE L'UTENTE HA UN ABBONAMENTO VALIDO PER LA LINEA DELL'ARGOMENTO
    public boolean userPuòSalireSuTratta(Long utenteId, Long trattaId) {
        // Verifica la presenza di un abbonamento valido per la tratta specificata
        String jpql = "SELECT COUNT(a) FROM Abbonamento a " +
                "JOIN a.tratte t " +
                "WHERE a.tessera.utente.id = :utenteId AND t.id = :trattaId";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("utenteId", utenteId)
                .setParameter("trattaId", trattaId)
                .getSingleResult();

        return count > 0; // Ritorna true se esiste almeno un abbonamento valido per la tratta
    }

}
