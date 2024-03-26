package team4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team4.dao.BigliettoDAO;
import team4.entities.Abbonamento;
import team4.entities.Biglietto;
import team4.entities.Mezzo;
import team4.entities.Tratta;
import team4.dao.TessereDAO;
import team4.entities.Tessera;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");
        EntityManager em = emf.createEntityManager();
        TessereDAO tessereDAO = new TessereDAO(em);
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


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            Tessera tessera1 = new Tessera("Jonathan ", "Joestar", LocalDate.parse("30-12-2003", formatter));
            tessereDAO.save(tessera1);
            System.out.println("Tessera salvata con successo.");
        } catch (DateTimeParseException e) {
            System.out.println("Attenzione! Errore durante il parsing della data, formato errato.");

        } catch (Exception e) {
            System.out.println("Errore durante il salvataggio della tessera.");

        }
    }
}
