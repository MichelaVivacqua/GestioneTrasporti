package team4.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import team4.entities.Biglietto;
import team4.entities.Abbonamento;
import team4.entities.Distributore;
import team4.entities.Mezzo;

import java.time.LocalDate;


public class BigliettoDAO {
    private EntityManager em;

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

    // Metodo per l'emissione di un abbonamento settimanale
    public void emettiAbbonamentoSettimanale(Abbonamento abbonamento) {
        try {
            EntityTransaction t = this.em.getTransaction();
            t.begin();
            this.em.persist(abbonamento);
            t.commit();
            System.out.println("Abbonamento settimanale emesso!");
        } catch (Exception e) {
            System.out.println("Errore durante l'emissione dell'abbonamento settimanale: " + e.getMessage());
        }
    }

    // Metodo per l'emissione di un abbonamento mensile
    public void emettiAbbonamentoMensile(Abbonamento abbonamento) {
        try {
            EntityTransaction t = this.em.getTransaction();
            t.begin();
            this.em.persist(abbonamento);
            t.commit();
            System.out.println("Abbonamento mensile emesso!");
        } catch (Exception e) {
            System.out.println("Errore durante l'emissione dell'abbonamento mensile: " + e.getMessage());
        }
    }
    // Metodo per il conteggio dei biglietti emessi per un distributore in un dato periodo di tempo
    public long countBigliettiEmessiPerDistributore(Distributore distributore, LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Biglietto> root = query.from(Biglietto.class);

        query.select(cb.count(root));
        query.where(cb.and(
                cb.equal(root.get("emessoDa"), distributore),
                cb.between(root.get("dataDiEmissione"), startDate, endDate)
        ));

        return em.createQuery(query).getSingleResult();
    }

    // Metodo per il conteggio degli abbonamenti emessi per un distributore in un dato periodo di tempo
    public long countAbbonamentiEmessiPerDistributore(Distributore distributore, LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Abbonamento> root = query.from(Abbonamento.class);

        query.select(cb.count(root));
        query.where(cb.and(
                cb.equal(root.get("emessoDa"), distributore),
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
}



