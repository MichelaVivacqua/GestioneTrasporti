package team4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team4.dao.*;
import team4.entities.Utente;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");
    private static EntityManager em = emf.createEntityManager();
    private static BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
    private static TrattaDAO trattaDAO = new TrattaDAO(em);
    private static MezzoDAO mezzoDAO = new MezzoDAO(em);
    private static UtenteDAO utenteDAO = new UtenteDAO(em);
    private static TessereDAO tessereDAO = new TessereDAO(em);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int scelta = 0;
        while (scelta != 4) {
                menu();
                scelta = Integer.parseInt(scanner.nextLine());
            try {
                switch (scelta) {
                    case 1:
                        creaUtente();
                        break;
                    case 2:
                        cercaUtente();
//                        }
                        break;
                    case 3:
                        controllaValiditaTessera();
                        break;
                    case 4:
                        System.out.println("Uscita...");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                        break;

                }
            } catch (Exception e) {
                System.out.println("Input non valido. Riprova.");
                scanner.next(); // Consuma l'input errato per evitare loop infinito
            }
        }
    }
    // Chiudi lo scanner alla fine
//        scanner.close();

    // Chiudi l'EntityManager
//        em.close();
    //!----------------------------------------------------------------------------------
    private static void menu(){
        System.out.println("Menu:");
        System.out.println("1. Utente");
        System.out.println("2. Amministratore");
        System.out.println("3. Esci");
        System.out.print("Seleziona un'opzione: ");
    }
    private static void utente(){
        System.out.println("1. CreaUtente");
        System.out.println("2. ");
        System.out.println("3. Esci");
        System.out.print("Seleziona un'opzione: ");
    }


    private static void creaUtente() {
        System.out.println("Inserisci un nome");
        String nome = scanner.nextLine();
        System.out.println("Inserisci un cognome");
        String cognome = scanner.nextLine();
        boolean tessera = chiediCreazioneTessera();
        Utente utente = new Utente(nome, cognome, tessera);
        utenteDAO.save(utente);
        System.out.println("UTENTE " + nome + " CREATO CON SUCCESSO");
    }

    private static boolean chiediCreazioneTessera() {
        System.out.println("Vuoi creare una tessera?");
        System.out.println("1. SI");
        System.out.println("2. No");
        int sceltaT = Integer.parseInt(scanner.nextLine());
        return sceltaT == 1;
    }

    private static void cercaUtente() {
        System.out.println("Cerca per mezzo di:");
        System.out.println("1. Tessera");
        System.out.println("2. Id Utente");
        System.out.println("3. Torna indietro");
        int sceltaCerca = Integer.parseInt(scanner.nextLine());
        switch (sceltaCerca) {
            case 1:
                cercaPerTessera();
                break;
            case 2:
                cercaPerIdUtente();
                break;
            case 3:
                // Torna al menu principale
                break;
            default:
                System.out.println("Scelta non valida. Riprova.");
                break;
        }
    }

    private static void cercaPerTessera() {
        System.out.println("Inserisci il numero di tessera");
        int tesseraId = Integer.parseInt(scanner.nextLine());
        System.out.println(tessereDAO.findById(tesseraId));
    }

    private static void cercaPerIdUtente() {
        System.out.println("Inserisci l'Id dell'utente");
        int utenteId = Integer.parseInt(scanner.nextLine());
        System.out.println(utenteDAO.findById(utenteId));
    }

    private static void controllaValiditaTessera() {
        System.out.println("Inserisci qui l'id della tua tessera");
        String id = scanner.nextLine();
        try {
            long tesseraId = Long.parseLong(id);
            tessereDAO.rinnovaTessera(tesseraId);
        } catch (NumberFormatException e) {
            System.out.println("ID della tessera non valido. Riprova.");
        }
    }

}
//!----------------------------------------------------------------------------------
//!TRATTA1 //TRATTA2
//        Tratta milano_roma = new Tratta(2, "Milano", "Roma");
//        Tratta veronaNapoli = new Tratta(4, "Verona", "Napoli");
//        trattaDAO.saveTratta(veronaNapoli);


//        Mezzo nuovoMezzo = new Mezzo(TipoMezzo.AUTOBUS,80,StatoMezzo.IN_MANUTENZIONE, LocalDate.parse("27-03-2024", formatter),veronaNapoli,4,3);
//        mezzoDAO.save(nuovoMezzo);


//TODO Rimuvoi i metodi dal dao perchè non serve nel salvataggio della tratta il tempo e il num di volte dato che con il mezzo fai tutto


//        Tratta abruzzoVerona = new Tratta(5, "Abruzzo", "Verona");
//        trattaDAO.saveTratta(milano_roma,10,2);
//        trattaDAO.saveTratta(veronaNapoli,2,5);
//        trattaDAO.saveTratta(abruzzoVerona,6,10);
//        System.out.println( trattaDAO.findTrattaById(7));
//        System.out.println(trattaDAO.findTrattaByPartenza("Milano"));
//        trattaDAO.findTrattaByIdAndDelete(7);
//        //! CREAZIONE MEZZO
//            Mezzo bus = new Mezzo(80, false, LocalDate.parse("26-03-2024", formatter), milano_roma, 1, 1);
//            mezzoDAO.save(bus);
//
//        System.out.println(mezzoDAO.findById(20));


//                 Ottenere il conteggio dei biglietti e degli abbonamenti emessi da un distributore in un dato periodo di tempo
//        DistributoriDAO distributoriDAO= new DistributoriDAO(em);
//        Distributore distributore = new Distributore();
//        distributoriDAO.save(distributore);
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
//        // Emissione di un abbonamento settimanale
//        Abbonamento abbonamentoSettimanale = new Abbonamento();
//        // Imposta i dettagli dell'abbonamento...
//        bigliettoDAO.emettiAbbonamentoSettimanale(abbonamentoSettimanale);
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

//        em.close();
//        emf.close();
//    }
//}
