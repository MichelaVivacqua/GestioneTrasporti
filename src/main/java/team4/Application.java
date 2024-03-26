package team4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team4.dao.BigliettoDAO;
import team4.dao.MezzoDAO;
import team4.dao.TessereDAO;
import team4.dao.TrattaDAO;
import team4.entities.Abbonamento;
import team4.entities.Biglietto;
import team4.entities.Mezzo;
import team4.entities.Tessera;
import team4.entities.Tratta;

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
        TrattaDAO trattaDAO= new TrattaDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);


        Tratta tratta = new Tratta(2,"Milano","Roma",);
        trattaDAO.saveTratta(tratta);

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


        Tessera tessera1 = new Tessera("Michela","Vivacqua", LocalDate.parse("2024-03-26"));
        tessereDAO.save(tessera1);



//        Mezzo mezzo1 = new Mezzo(58,true,LocalDate.parse("2024-03-26"),
//                tratta
//        );
    }
}
