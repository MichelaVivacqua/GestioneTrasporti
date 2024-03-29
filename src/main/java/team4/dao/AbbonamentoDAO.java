package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team4.entities.Abbonamento;
import team4.entities.Mezzo;
import team4.entities.Rivenditore_Autorizzato;
import team4.entities.Tratta;

import java.time.LocalDate;
import java.util.List;

public class AbbonamentoDAO {
    private EntityManager em;

    public AbbonamentoDAO(EntityManager em) {
        this.em = em;
    }

    public void salvaAbbonamento(Abbonamento abbonamento) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (abbonamento.getId() == null) {
                em.persist(abbonamento);
            } else {
                em.merge(abbonamento);
            }
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public Abbonamento trovaPerId(Long id) {
        return em.find(Abbonamento.class, id);
    }

    public void aggiungiTrattaAdAbbonamento(Long abbonamentoId, Tratta tratta) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Abbonamento abbonamento = em.find(Abbonamento.class, abbonamentoId);
            if (abbonamento != null) {
                abbonamento.addTratta(tratta);
                em.merge(abbonamento);
            }
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public void update(Abbonamento abbonamento) {

        try {
           EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.merge(abbonamento);

            transaction.commit();
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<Abbonamento> findAbbonamentiByTratta(Tratta tratta) {
        TypedQuery<Abbonamento> query = em.createQuery(
                "SELECT a FROM Abbonamento a JOIN a.tratte t WHERE t = :tratta", Abbonamento.class);
        query.setParameter("tratta", tratta);
        return query.getResultList();
    }

    public void rimuoviTrattaDaAbbonamento(Long abbonamentoId, Tratta tratta) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Abbonamento abbonamento = em.find(Abbonamento.class, abbonamentoId);
            if (abbonamento != null) {
                abbonamento.removeTratta(tratta);
                em.merge(abbonamento);
            }
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public void eliminaAbbonamento(Long abbonamentoId) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Abbonamento abbonamento = em.find(Abbonamento.class, abbonamentoId);
            if (abbonamento != null) {
                em.remove(abbonamento);
            }
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public List<Tratta> getTratteByAbbonamentoId(Long abbonamentoId) {
        List<Tratta> tratte = em.createQuery(
                        "SELECT t FROM Tratta t JOIN t.abbonamenti a WHERE a.id = :abbonamentoId", Tratta.class)
                .setParameter("abbonamentoId", abbonamentoId)
                .getResultList();
        return tratte;
    }

    public List<Abbonamento> trovaAbbonamentiEmessiDaRivenditoreInPeriodo(Rivenditore_Autorizzato rivenditore, LocalDate inizio, LocalDate fine) {
        return em.createQuery(
                        "SELECT a FROM Abbonamento a WHERE a.emessoDa = :rivenditore AND a.dataDiEmissione BETWEEN :inizio AND :fine",
                        Abbonamento.class)
                .setParameter("rivenditore", rivenditore)
                .setParameter("inizio", inizio)
                .setParameter("fine", fine)
                .getResultList();
    }

    public List<Mezzo> trovaMezziPerTratta(Tratta tratta) {
        return em.createQuery(
                        "SELECT m FROM Mezzo m WHERE m.trattaServita = :tratta",
                        Mezzo.class)
                .setParameter("tratta", tratta)
                .getResultList();
    }
}
