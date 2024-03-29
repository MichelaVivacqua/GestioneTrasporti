package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import team4.entities.*;
import team4.entities.Rivenditore_Autorizzato;
import team4.enums.DurataTitolo;

import java.time.LocalDate;
import java.util.List;


public class BigliettoDAO {
    private EntityManager em;

    private DurataTitolo durata;

    private Abbonamento abbonamento;


    public BigliettoDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo per l'emissione di un biglietto generico
    public void emettiBiglietto(Biglietto biglietto) {
        try {
            EntityTransaction t = this.em.getTransaction();
            t.begin();
            this.em.persist(biglietto);
            t.commit();
            System.out.println("Biglietto emesso!");
        } catch (Exception e) {
            System.out.println("Errore durante l'emissione del biglietto: " + e.getMessage());
        }
    }

    public Biglietto findById(long id) {
        return em.find(Biglietto.class, id);
    }


    // Metodo per l'emissione di un abbonamento settimanale
    public void emettiAbbonamento(Abbonamento abbonamento) {
        EntityTransaction t = this.em.getTransaction();
        try {
            t.begin();

            for (Tratta tratta : abbonamento.getTratte()) {
                if (!em.contains(tratta)) {
                    Tratta managedTratta = em.find(Tratta.class, tratta.getId());
                    if (managedTratta == null) {
                        em.persist(tratta);
                    } else {
                        tratta = managedTratta;
                    }
                }
            }

            this.em.persist(abbonamento);

            t.commit();
            System.out.println("Abbonamento emesso con successo.");
        } catch (Exception e) {
            if (t.isActive()) {
                t.rollback();
            }
            System.out.println("Errore durante l'emissione dell'abbonamento: " + e.getMessage());
        }
    }


    // Metodo per il conteggio dei biglietti emessi per un distributore in un dato periodo di tempo
    public long countBigliettiEmessiPerDistributore(Rivenditore_Autorizzato rivenditoreAutorizzato, LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Biglietto> root = query.from(Biglietto.class);

        query.select(cb.count(root));
        query.where(cb.and(
                cb.equal(root.get("emessoDa"), rivenditoreAutorizzato),
                cb.between(root.get("dataDiEmissione"), startDate, endDate)
        ));

        return em.createQuery(query).getSingleResult();
    }

    // Metodo per il conteggio degli abbonamenti emessi per un distributore in un dato periodo di tempo
    public long countAbbonamentiEmessiPerDistributore(Rivenditore_Autorizzato rivenditoreAutorizzato, LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Abbonamento> root = query.from(Abbonamento.class);

        query.select(cb.count(root));
        query.where(cb.and(
                cb.equal(root.get("emessoDa"), rivenditoreAutorizzato),
                cb.between(root.get("dataDiEmissione"), startDate, endDate)
        ));

        return em.createQuery(query).getSingleResult();
    }

    // Metodo per verificare la validità dell'abbonamento associato a una tessera
    public boolean verificaValiditaAbbonamento(String numeroTessera) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<Abbonamento> root = query.from(Abbonamento.class);

            query.select(cb.count(root));
            query.where(cb.and(
                    cb.equal(root.get("tessera").get("numeroTessera"), numeroTessera),
                    cb.greaterThanOrEqualTo(root.get("dataDiScadenza"), LocalDate.now())
            ));

            Long count = em.createQuery(query).getSingleResult();
            return count > 0;
        } catch (Exception e) {
            System.out.println("Errore durante la verifica della validità dell'abbonamento: " + e.getMessage());
            return false;
        }
    }


    // Metodo per vidimare un biglietto su un mezzo
    public void vidimaBiglietto(Biglietto biglietto, Mezzo mezzo) {
        try {
            EntityTransaction t = this.em.getTransaction();
            t.begin();

            // Annulla il biglietto impostando il mezzo di vidimazione
            biglietto.setMezzoDiVidimazione(mezzo);
            biglietto.setDataDiVidimazione(LocalDate.now());
            em.merge(biglietto);

            t.commit();
            System.out.println("Biglietto vidimato sul  mezzo!");
        } catch (Exception e) {
            System.out.println("Errore durante la vidimazione del biglietto: " + e.getMessage());
        }
    }

    // Metodo per contare i biglietti vidimati su un particolare mezzo in un periodo di tempo
    public long countBigliettiVidimatiPerMezzo(Mezzo mezzo, LocalDate startDate, LocalDate endDate) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<Biglietto> root = query.from(Biglietto.class);

            query.select(cb.count(root));
            query.where(cb.and(
                    cb.equal(root.get("mezzoDiVidimazione"), mezzo),
                    cb.between(root.get("dataDiVidimazione"), startDate, endDate)
            ));

            return em.createQuery(query).getSingleResult();
        } catch (Exception e) {
            System.out.println("Errore durante il conteggio dei biglietti vidimati per il mezzo: " + e.getMessage());
            return 0;
        }
    }

    // Metodo per contare i biglietti vidimati in totale in un periodo di tempo
    public long countBigliettiVidimatiTotali(LocalDate startDate, LocalDate endDate) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<Biglietto> root = query.from(Biglietto.class);

            query.select(cb.count(root));
            query.where(
                    cb.between(root.get("dataDiVidimazione"), startDate, endDate)
            );

            return em.createQuery(query).getSingleResult();
        } catch (Exception e) {
            System.out.println("Errore durante il conteggio totale dei biglietti vidimati: " + e.getMessage());
            return 0;
        }
    }
    public List<Tratta> getTratteByAbbonamento(Long abbonamentoId) {
        return em.createQuery("SELECT t FROM Abbonamento a JOIN a.tratte t WHERE a.id = :abbonamentoId", Tratta.class)
                .setParameter("abbonamentoId", abbonamentoId)
                .getResultList();
    }

    public Long  findBigliettoByMezzoId(long mezzoId){
            TypedQuery<Long> query = em.createQuery(
                    "SELECT m.id FROM Biglietto m WHERE m.mezzoDiVidimazione.id_Mezzo = :mezzoId", Long.class);
            query.setParameter("mezzoId", mezzoId);

            List<Long> results = query.getResultList();
            if (!results.isEmpty()) {
                return results.get(0);
            }

            return null;
        }

        public Long  findBigliettoByDistributoreId(long distributoreId){
            TypedQuery<Long> query = em.createQuery(
                    "SELECT m.id FROM Biglietto m WHERE m.emessoDa.id = :distributoreId", Long.class);
            query.setParameter("distributoreId", distributoreId);

            List<Long> results = query.getResultList();
            if (!results.isEmpty()) {
                return results.get(0);
            }

            return null;
        }



    public void update(Biglietto b) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(b);
            et.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteBiglietto(Biglietto b) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Biglietto deleteBiglietto = em.merge(b); // Assicurati che l'entità sia gestita
            em.remove(deleteBiglietto);
            tx.commit();
            System.out.println("Biglietto eliminato");
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }



    }



