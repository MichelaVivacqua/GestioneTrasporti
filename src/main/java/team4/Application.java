package team4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team4.dao.BigliettoDAO;
import team4.dao.MezzoDAO;
import team4.dao.TessereDAO;
import team4.dao.TrattaDAO;
import team4.entities.Mezzo;
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
        TrattaDAO trattaDAO = new TrattaDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Tratta milano_roma = new Tratta(2, "Milano", "Roma");
        Tratta veronaNapoli = new Tratta(4, "Verona", "Napoli");
        Tratta abruzzoVerona = new Tratta(5, "Abruzzo", "Verona");
//        trattaDAO.saveTratta(milano_roma,10,2);
//        trattaDAO.saveTratta(veronaNapoli,2,5);
//        trattaDAO.saveTratta(abruzzoVerona,6,10);
//        trattaDAO.deleteTratta(milano_roma);
//        System.out.println( trattaDAO.findTrattaById(7));
//        System.out.println(trattaDAO.findTrattaByPartenza("Milano"));
//        trattaDAO.findTrattaByIdAndDelete(1);

//            Mezzo bus = new Mezzo(80, true, LocalDate.parse("10-01-2024", formatter), milano_roma, 1, 1);
//            mezzoDAO.save(bus);





//        Emissione di un biglietto
//        Biglietto biglietto = new Biglietto();
//        // Imposta i dettagli del biglietto...
//        bigliettoDAO.emettiBiglietto(biglietto);
//
//        // Emissione di un abbonamento settimanale
//        Abbonamento abbonamentoSettimanale = new Abbonamento();
//        // Imposta i dettagli dell'abbonamento...
//        bigliettoDAO.emettiAbbonamentoSettimanale(abbonamentoSettimanale);
//
//        // Emissione di un abbonamento mensile
//        Abbonamento abbonamentoMensile = new Abbonamento();
//        // Imposta i dettagli dell'abbonamento...
//        bigliettoDAO.emettiAbbonamentoMensile(abbonamentoMensile);
//
//
//        // Ottenere il conteggio dei biglietti e degli abbonamenti emessi da un distributore in un dato periodo di tempo
//        DistributoriDAO distributoriDAO= new DistributoriDAO(em);
//        Distributore distributore = new Distributore();
//        distributoriDAO.save(distributore);


// Esempio di utilizzo di un  distributore
//        LocalDate startDate = LocalDate.of(2024, 3, 1); // Data di inizio del periodo
//        LocalDate endDate = LocalDate.of(2024, 3, 31); // Data di fine del periodo

//        long bigliettiEmessi = bigliettoDAO.countBigliettiEmessiPerDistributore(distributore, LocalDate.parse("01-03-2024",formatter), LocalDate.parse("31-03-2024",formatter));
//        long abbonamentiEmessi = bigliettoDAO.countAbbonamentiEmessiPerDistributore(distributore, LocalDate.parse("01-03-2024",formatter), LocalDate.parse("31-03-2024",formatter));
//
//        System.out.println("Numero di biglietti emessi: " + bigliettiEmessi);
//        System.out.println("Numero di abbonamenti emessi: " + abbonamentiEmessi);
//        String numeroTessera = "123456789"; // Sostituisci con il numero di tessera dell'utente controllato
//        boolean abbonamentoValido = bigliettoDAO.verificaValiditaAbbonamento(numeroTessera);
//        System.out.println("L'abbonamento dell'utente è valido? " + abbonamentoValido);


        TessereDAO tessereDAO = new TessereDAO(em);

//        try {
//
//            Tessera tessera1 = new Tessera("Jonhatan", "Joestar", LocalDate.parse("25-03-2024", formatter));
//            tessereDAO.save(tessera1);
//
//            System.out.println("Tessera salvata con successo.");
//
//        } catch (DateTimeParseException e) {
//
//            System.out.println("Attenzione! Errore durante il parsing della data, formato errato.");
//
//        } catch (Exception e) {
//
//            System.out.println("Errore durante il salvataggio della tessera.");
//
//        }


//        MezzoDAO mezzoDAO = new MezzoDAO(em);

//        Mezzo mezzo1 = new Mezzo(58,true,LocalDate.parse("2024-03-26"),
//                tratta
//        );

        em.close();
    }
}
