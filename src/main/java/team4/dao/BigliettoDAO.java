package team4.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team4.entities.Biglietto;
import team4.entities.Abbonamento;


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
}



