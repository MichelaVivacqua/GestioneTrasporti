package team4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team4.dao.BigliettoDAO;
import team4.dao.DistributoriDAO;
import team4.dao.MezzoDAO;
import team4.dao.TrattaDAO;
import team4.entities.*;
import team4.enums.DurataTitolo;
import team4.enums.TipoMezzo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static team4.Navigations.Navigations.*;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");
    public static EntityManager em = emf.createEntityManager();



    public static void main(String[] args) {


        DistributoriDAO distributoriDAO= new DistributoriDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);


        //ESEMPI DI DISTRIBUTORI
        DistributoreAutomatico distributoreAutomatico= new DistributoreAutomatico(true);
        DistributoreAutomatico distributoreAutomatico2= new DistributoreAutomatico(false);
        Distributore botteghino = new Distributore();

        distributoriDAO.save(botteghino);
        distributoriDAO.save(distributoreAutomatico);
        distributoriDAO.save(distributoreAutomatico2);

        //ESEMPI DI TRATTE
        Tratta veronaNapoli = new Tratta(350, "Verona", "Napoli");
        Tratta napoliTorino = new Tratta(400, "Napoli", "Torino");
        Tratta pescaraRoma = new Tratta(200, "Pescara", "Roma");

        trattaDAO.saveTratta(veronaNapoli);
        trattaDAO.saveTratta(napoliTorino);
        trattaDAO.saveTratta(pescaraRoma);

        //ESEMPI DI MEZZI
        Mezzo mezzo1 = new Mezzo(TipoMezzo.AUTOBUS.ordinal(), veronaNapoli, 4, 50);
        Mezzo mezzo2 = new Mezzo();


        mezzoDAO.save(mezzo1);

//     Emissione di un abbonamento settimanale
        trattaDAO.saveTratta(veronaNapoli);


//        Abbonamento abbonamentoSettimanale = new Abbonamento(DurataTitolo.MENSILE,nuovoMezzo,distributore,LocalDate.now(),tessereDAO.findById(1));
        // Imposta i dettagli dell'abbonamento...
//        bigliettoDAO.emettiAbbonamento(abbonamentoSettimanale);


        em.close();
        emf.close();
        scanner.close();
    }
}





//TODO Rimuvoi i metodi dal dao perchè non serve nel salvataggio della tratta il tempo e il num di volte dato che con il mezzo fai tutto


//        Tratta abruzzoVerona = new Tratta(5, "Abruzzo", "Verona");
//        trattaDAO.saveTratta(milano_roma,10,2);
//        trattaDAO.saveTratta(veronaNapoli,2,5);
//        trattaDAO.saveTratta(abruzzoVerona,6,10);
//        System.out.println( trattaDAO.findTrattaById(7));
//        System.out.println(trattaDAO.findTrattaByPartenza("Milano"));
//        //! CREAZIONE MEZZO
//            Mezzo bus = new Mezzo(80, false, LocalDate.parse("26-03-2024", formatter), milano_roma, 1, 1);
//            mezzoDAO.save(bus);
//
//        System.out.println(mezzoDAO.findById(20));


//                 Ottenere il conteggio dei biglietti e degli abbonamenti emessi da un distributore in un dato periodo di tempo
//
//
//
//
////        Emissione di un biglietto
//        Biglietto biglietto = new Biglietto(bus,distributore,LocalDate.parse("27-03-2024",formatter),LocalDate.parse("28-03-2024",formatter),tessera1);
//        // Imposta i dettagli del biglietto...
//
//        bigliettoDAO.emettiBiglietto(biglietto);
//        bigliettoDAO.vidimaBiglietto(biglietto,bus);
//
//
//
//        bigliettoDAO.findById(1);


//

//
//        // Emissione di un abbonamento mensile
//        Abbonamento abbonamentoMensile = new Abbonamento();
//        // Imposta i dettagli dell'abbonamento...
//        bigliettoDAO.emettiAbbonamentoMensile(abbonamentoMensile);


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


// Utilizzo del metodo countBigliettiVidimatiPerMezzo con l'istanza salvata di Mezzo
//        long bigliettiVidimatiPerMezzo = bigliettoDAO.countBigliettiVidimatiPerMezzo(bus, startDate, endDate);
//        System.out.println("Numero di biglietti vidimati su questo mezzo: " + bus + bigliettiVidimatiPerMezzo);
////
////     // Conteggio dei biglietti vidimati in totale in un periodo di tempo
//        long bigliettiVidimatiTotali = bigliettoDAO.countBigliettiVidimatiTotali(startDate, endDate);
//        System.out.println("Numero di biglietti vidimati in totale: " + bigliettiVidimatiTotali);

//        -------------RINNOVO TESSERA SCADUTA---------------
//       tessereDAO.rinnovaTessera(1);

//
//    }
//}
