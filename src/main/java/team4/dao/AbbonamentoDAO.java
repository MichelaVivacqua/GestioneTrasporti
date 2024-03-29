package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team4.entities.Abbonamento;
import team4.entities.Tratta;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


}