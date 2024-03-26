package team4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team4.dao.BigliettoDAO;
import team4.entities.Abbonamento;
import team4.entities.Biglietto;
import team4.entities.Mezzo;
import team4.entities.Tratta;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");
        EntityManager em = emf.createEntityManager();
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);

        // Emissione di un biglietto
        Biglietto biglietto = new Biglietto();
        // Imposta i dettagli del biglietto...
        bigliettoDAO.emettiBiglietto(biglietto);

        // Emissione di un abbonamento settimanale
        Abbonamento abbonamentoSettimanale = new Abbonamento();
        // Imposta i dettagli dell'abbonamento...
        bigliettoDAO.emettiAbbonamentoSettimanale(abbonamentoSettimanale);

        // Emissione di un abbonamento mensile
        Abbonamento abbonamentoMensile = new Abbonamento();
        // Imposta i dettagli dell'abbonamento...
        bigliettoDAO.emettiAbbonamentoMensile(abbonamentoMensile);



    }
}
