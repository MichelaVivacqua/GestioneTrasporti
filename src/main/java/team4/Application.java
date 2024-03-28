package team4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team4.dao.*;
import team4.dao.Rivenditore_Autorizzato;
import team4.entities.*;
import team4.enums.DurataTitolo;
import team4.enums.TipoMezzo;

import java.time.LocalDate;

import static team4.Navigations.Navigations.*;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");
    public static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {


        Rivenditore_Autorizzato rivenditoreAutorizzato = new Rivenditore_Autorizzato(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        TesseraDAO tesseraDAO = new TesseraDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);

        //ESEMPI DI UTENTI
        Utente vincenzo = new Utente("Vincenzo", "Costantini", true); // Pro green-pass
        Utente marco = new Utente("Marco", "Bianchi", false); // Pericoloso anarchico non tesserato
        Utente giulia = new Utente("Giulia", "Rossi", false); // Inizialmente non tesserata

        utenteDAO.save(vincenzo);
        utenteDAO.save(marco);
        utenteDAO.save(giulia);

        //ESEMPIO DI TESSERA
        Tessera tesseraVincenzo = new Tessera();
        Tessera tesseraGiulia = new Tessera();

        tesseraVincenzo.setUtente(vincenzo);
        tesseraGiulia.setUtente(giulia);
        tesseraVincenzo.setDataDiEmissione(LocalDate.now().minusDays(50));
        tesseraGiulia.setDataDiEmissione(LocalDate.now().minusDays(150));

        tesseraGiulia.autoUpdateDataDiScadenza();
        tesseraVincenzo.autoUpdateDataDiScadenza();

        tesseraDAO.save(tesseraVincenzo);
        tesseraDAO.save(tesseraGiulia);

        //ESEMPI DI DISTRIBUTORI
        team4.entities.Rivenditore_Autorizzato distributoreAutomatico= new RivenditoreAutorizzatoAutomatico(true);
        RivenditoreAutorizzatoAutomatico distributoreAutomatico2= new RivenditoreAutorizzatoAutomatico(false);
        team4.entities.Rivenditore_Autorizzato botteghino = new team4.entities.Rivenditore_Autorizzato();

        rivenditoreAutorizzato.save(botteghino);
        rivenditoreAutorizzato.save(distributoreAutomatico);
        rivenditoreAutorizzato.save(distributoreAutomatico2);

        //ESEMPI DI TRATTE
        Tratta veronaNapoli = new Tratta(350, "Verona", "Napoli");
        Tratta napoliTorino = new Tratta(400, "Napoli", "Torino");
        Tratta pescaraRoma = new Tratta(200, "Pescara", "Roma");
        Tratta milanoRoma = new Tratta(500, "Milano", "Roma");

        trattaDAO.saveTratta(veronaNapoli);
        trattaDAO.saveTratta(napoliTorino);
        trattaDAO.saveTratta(pescaraRoma);
        trattaDAO.saveTratta(milanoRoma);

        //ESEMPI DI MEZZI
        Mezzo mezzo1 = new Mezzo(TipoMezzo.AUTOBUS.ordinal(), veronaNapoli, 4, 50);
        Mezzo mezzo2 = new Mezzo();
        mezzo2.setTipoMezzo(TipoMezzo.TRAM);
        mezzo2.setTrattaServita(pescaraRoma); // Assumendo che Tratta sia corrett
        mezzo2.setNumeroDiVolte(2);
        mezzo2.setTempoEffettivo(120);
        Mezzo mezzo3 = new Mezzo(90, napoliTorino, 1, 100);

        mezzoDAO.save(mezzo1);
        mezzoDAO.save(mezzo2);
        mezzoDAO.save(mezzo3);

//     Emissione di un abbonamento settimanale
        trattaDAO.saveTratta(veronaNapoli);

        // Creazione delle entità Abbonamento
        Abbonamento abbonamento1 = new Abbonamento();
        Abbonamento abbonamento2 = new Abbonamento();
        Biglietto bigliettoGiulia = new Biglietto(distributoreAutomatico, LocalDate.now(), LocalDate.now(), tesseraGiulia);

        abbonamento1.setMezzoDiVidimazione(mezzo1);
        abbonamento1.setDurata(DurataTitolo.MENSILE);
        abbonamento1.setEmessoDa(botteghino);
        abbonamento1.setTessera(tesseraVincenzo);
        abbonamento1.setDataDiEmissione(LocalDate.now().minusDays(10));
        abbonamento1.setDataDiVidimazione(LocalDate.now());
        abbonamento1.addTratta(veronaNapoli);
        abbonamento1.addTratta(napoliTorino);
        abbonamento1.addTratta(pescaraRoma);

        abbonamento2.setMezzoDiVidimazione(mezzo1);
        abbonamento2.setDurata(DurataTitolo.SETTIMANALE);
        abbonamento2.setEmessoDa(distributoreAutomatico2);
        abbonamento2.setTessera(tesseraGiulia);
        abbonamento2.setDataDiEmissione(LocalDate.now().minusDays(10));
        abbonamento2.setDataDiVidimazione(LocalDate.now().minusDays(8));
        abbonamento2.addTratta(veronaNapoli);



        bigliettoDAO.emettiAbbonamento(abbonamento1);
        bigliettoDAO.emettiAbbonamento(abbonamento2);
        bigliettoDAO.emettiBiglietto(bigliettoGiulia);

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
