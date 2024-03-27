package team4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team4.dao.*;
import team4.entities.Utente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");


    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");
        EntityManager em = emf.createEntityManager();
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Scanner scanner = new Scanner(System.in);
        int scelta = 0;
        while (scelta != 4) {
            System.out.println("Menu:");
            System.out.println("1. Creazione Utente");
            System.out.println("2. Seleziona Utente");
            System.out.println("3. Opzione 3");
            System.out.println("4. Esci");

            System.out.print("Seleziona un'opzione: ");
            try {
                scelta = Integer.parseInt(scanner.nextLine());
                switch (scelta) {
                    case 1:
                        //TODO CREAZIONE UTENTE + TESSERA(AUTOMATICO)
                        System.out.println("Inserisci un nome");
                        String nome = scanner.nextLine();
                        System.out.println("Inserisci un cognome");
                        String cognome = scanner.nextLine();

                        System.out.println("Vuoi creare una tessera? ");
                        boolean tessera = false;
                        while (scelta != 4) {
                            System.out.println("1. SI");
                            System.out.println("2. No");

                            int sceltaT = Integer.parseInt(scanner.nextLine());
                            switch (sceltaT) {
                                case 1:
                                    tessera = true;
                                    scelta = 4; // Per uscire dal loop
                                    break;
                                case 2:
                                    tessera = false;
                                    scelta = 4; // Per uscire dal loop
                                    break;
                                default:
                                    System.out.println("Scelta non valida. Riprova.");
                                    break;
                            }
                        }
                        Utente utente;
                        if (tessera) {
                            utente = new Utente(nome, cognome, true);
                        } else {
                            utente = new Utente(nome, cognome, false);
                        }
                        utenteDAO.save(utente);
                        System.out.println("UTENTE " + nome + " CREATO CON SUCCESSO");
                        break;
                    case 2:
                        System.out.println("Hai selezionato l'opzione 2");
                        break;
                    case 3:
                        System.out.println("Hai selezionato l'opzione 3");
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

        //! CREAZIONE TESSERA
        TessereDAO tessereDAO = new TessereDAO(em);

//        tessereDAO.findByIdAndDelete(1);
//        Tessera tessera1 = new Tessera(LocalDate.parse("25-03-2024", formatter));

//        try {
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


        //! CREAZIONE TRATTA
//        Tratta milano_roma = new Tratta(2, "Milano", "Roma");
//        Tratta veronaNapoli = new Tratta(4, "Verona", "Napoli");
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
        LocalDate startDate = LocalDate.of(2024, 3, 1); // Data di inizio del periodo
        LocalDate endDate = LocalDate.of(2024, 3, 31); // Data di fine del periodo

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


        em.close();
        emf.close();
    }
}
