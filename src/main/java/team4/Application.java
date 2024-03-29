package team4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team4.dao.*;
import team4.dao.Rivenditore_AutorizzatoDAO;
import team4.entities.*;
import team4.enums.DurataTitolo;
import team4.enums.TipoMezzo;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static team4.Navigations.Navigations.*;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");
    public static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        emettiBiglietto(scanner, trattaDAO, mezzoDAO);

        menu();

        Rivenditore_AutorizzatoDAO rivenditoreAutorizzato = new Rivenditore_AutorizzatoDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        TesseraDAO tesseraDAO = new TesseraDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        ManutenzioniDAO manutenzioniDAO = new ManutenzioniDAO(em);

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
        mezzo2.setTrattaServita(pescaraRoma);
        mezzo2.setNumeroDiVolte(2);
        mezzo2.setTempoEffettivo(120);
        Mezzo mezzo3 = new Mezzo(90, napoliTorino, 1, 100);

        LocalDate dataInizio1 = LocalDate.now().minusDays(20);
        LocalDate dataFine1 = LocalDate.now().minusDays(10);
        LocalDate dataInizio2 = LocalDate.now().minusDays(20);

        Manutenzione manutenzione1 = new Manutenzione(dataInizio1, dataFine1, mezzo1); // MANUTENZIONE FINITA
        Manutenzione manutenzione2 = new Manutenzione(mezzo2); // METODO MANUTENZIONE CON SOLO IL MEZZO COME ARGOMENTO CREA UNA MANUTENZIONE CON DATA CORRENTE E DATA FINE NULL

        // SALVARE PRIMA I MEZZI CHE LE MANUTENZIONI DIPENDENTI PER NON VIOLARE IL VINCOLO DI NON NULLITA'
        mezzoDAO.save(mezzo1);
        mezzoDAO.save(mezzo2);
        mezzoDAO.save(mezzo3);

        // SALVO LE MANUTENZIONI
        manutenzioniDAO.save(manutenzione1);
        manutenzioniDAO.save(manutenzione2);

        //TEST METODI DI VERIFICA MANUTENZIONE
        //NB!! PER FUNZIONARE SIA I MEZZI CHE LE MANUTENZIONI DEVONO ESSERE GIA SALVATI
        // HO FATTO I PRINTLN VERDI SENNO NON SI CAPISCE UN CAZZO
        System.out.println("\u001B[32m" + "Il mezzo2 è in manutenzione: " + manutenzioniDAO.isMezzoInManutenzione(mezzo2.getId()) + "\u001B[0m");
        System.out.println("\u001B[32m" + "Il mezzo1 è in manutenzione: " + manutenzioniDAO.isMezzoInManutenzione(mezzo1.getId()) + "\u001B[0m");
        System.out.println("\u001B[32m" + "Giorni dall'ultima manutenzione di mezzo1: " + manutenzioniDAO.getGiorniDallUltimaManutenzione(mezzo1.getId()) + "\u001B[0m");


        // CREAZIONE DI TITOLI DI VIAGGIO
        Abbonamento abbonamento1 = new Abbonamento();
        Abbonamento abbonamento2 = new Abbonamento();
        Biglietto bigliettoGiulia = new Biglietto(distributoreAutomatico, LocalDate.now(), LocalDate.now(), tesseraGiulia);
        Biglietto bigliettoMarco = new Biglietto(botteghino, LocalDate.now(), LocalDate.now());

        abbonamento1.setMezzoDiVidimazione(mezzo1);
        abbonamento1.setDurata(DurataTitolo.MENSILE);
        abbonamento1.setEmessoDa(botteghino);
        abbonamento1.setTessera(tesseraVincenzo);
        abbonamento1.setDataDiEmissione(LocalDate.now().minusDays(10));
        abbonamento1.setDataDiVidimazione(LocalDate.now());

        abbonamento2.setMezzoDiVidimazione(mezzo1);
        abbonamento2.setDurata(DurataTitolo.SETTIMANALE);
        abbonamento2.setEmessoDa(distributoreAutomatico2);
        abbonamento2.setTessera(tesseraGiulia);
        abbonamento2.setDataDiEmissione(LocalDate.now().minusDays(10));
        abbonamento2.setDataDiVidimazione(LocalDate.now().minusDays(8));
        abbonamento2.addTratta(veronaNapoli);


        bigliettoDAO.emettiBiglietto(bigliettoGiulia);
        abbonamentoDAO.salvaAbbonamento(abbonamento1);
        abbonamentoDAO.salvaAbbonamento(abbonamento2);

        abbonamentoDAO.aggiungiTrattaAdAbbonamento(abbonamento1.getId(), veronaNapoli);
        abbonamentoDAO.aggiungiTrattaAdAbbonamento(abbonamento1.getId(), napoliTorino);
        abbonamentoDAO.aggiungiTrattaAdAbbonamento(abbonamento1.getId(), pescaraRoma);


        // TEST METODI DI VERIFICA ABBONAMENTO
        System.out.println("\u001B[32m Vincenzo può salire su " + veronaNapoli.toString() + " ? "+ utenteDAO.userPuòSalireSuTratta(vincenzo.getId() ,veronaNapoli.getId()) + "\u001B[0m");

        em.close();
        emf.close();
        scanner.close();
    }
    public static void emettiBiglietto(Scanner scanner, TrattaDAO trattaDAO, MezzoDAO mezzoDAO) {

        List<Tratta> tratteDisponibili = trattaDAO.listaTratte();


        List<Mezzo> mezziDisponibili = mezzoDAO.listaMezzi();


        System.out.println("Tratte disponibili:");
        for (Tratta tratta : tratteDisponibili) {
            System.out.println(tratta);
        }


        System.out.println("Mezzi disponibili:");
        for (Mezzo mezzo : mezziDisponibili) {
            System.out.println(mezzo);
        }

        System.out.print("Seleziona l'ID della tratta: ");
        long trattaId = scanner.nextLong();
        System.out.print("Seleziona l'ID del mezzo: ");
        long mezzoId = scanner.nextLong();
    }


    };


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
