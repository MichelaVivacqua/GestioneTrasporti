package team4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import team4.dao.BigliettoDAO;
import team4.entities.*;
import team4.entities.Distributore;
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
       
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        TrattaDAO trattaDAO= new TrattaDAO(em);


//        Tratta tratta = new Tratta(2,"Milano","Roma",);
//        trattaDAO.saveTratta(tratta);

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


        // Ottenere il conteggio dei biglietti e degli abbonamenti emessi da un distributore in un dato periodo di tempo
        Distributore distributore = new Distributore();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(distributore); // Salva l'istanza di Distributore nel database
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();


        }

// Esempio di utilizzo di un  distributore
        LocalDate startDate = LocalDate.of(2024, 3, 1); // Data di inizio del periodo
        LocalDate endDate = LocalDate.of(2024, 3, 31); // Data di fine del periodo

        long bigliettiEmessi = bigliettoDAO.countBigliettiEmessiPerDistributore(distributore, startDate, endDate);
        long abbonamentiEmessi = bigliettoDAO.countAbbonamentiEmessiPerDistributore(distributore, startDate, endDate);

        System.out.println("Numero di biglietti emessi: " + bigliettiEmessi);
        System.out.println("Numero di abbonamenti emessi: " + abbonamentiEmessi);
        String numeroTessera = "123456789"; // Sostituisci con il numero di tessera dell'utente controllato
        boolean abbonamentoValido = bigliettoDAO.verificaValiditaAbbonamento(numeroTessera);
        System.out.println("L'abbonamento dell'utente è valido? " + abbonamentoValido);

        em.close();


        TessereDAO tessereDAO = new TessereDAO(em);
        Tessera tessera1 = new Tessera("Michela","Vivacqua", LocalDate.parse("2024-03-26"));
        tessereDAO.save(tessera1);

        MezzoDAO mezzoDAO = new MezzoDAO(em);

//        Mezzo mezzo1 = new Mezzo(58,true,LocalDate.parse("2024-03-26"),
//                tratta
//        );
    }
}
