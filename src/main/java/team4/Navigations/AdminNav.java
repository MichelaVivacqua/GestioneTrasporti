package team4.Navigations;

import jakarta.persistence.EntityManager;
import team4.Application;
import team4.dao.MezzoDAO;
import team4.dao.Rivenditore_AutorizzatoDAO;
import team4.dao.TrattaDAO;
import team4.entities.*;
import team4.enums.TipoMezzo;

import java.util.List;
import java.util.Scanner;

import static team4.Navigations.Navigations.*;

public class AdminNav {
    private static final EntityManager em = Application.em;
    private static final MezzoDAO mezzoDAO = new MezzoDAO(em);
    private static final TrattaDAO trattaDAO = new TrattaDAO(em);
    private static final Rivenditore_AutorizzatoDAO RIVENDITORE_AUTORIZZATO = new Rivenditore_AutorizzatoDAO(em);


    public static void admin() {


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Verifica manutenzione tramite ID del mezzo");
            System.out.println("2. Crea tratta");
            System.out.println("3. Crea mezzo");
            System.out.println("4. Crea distributore");
            System.out.println("5. Rimuovi tratta");
            System.out.println("6. Rimuovi mezzo");
            System.out.println("0. Esci");
            System.out.print("Seleziona un'opzione: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Inserisci l'ID del mezzo: ");
                    long mezzoId = scanner.nextLong();
                    String dataManutenzione = String.valueOf(mezzoDAO.isMezzoInManutenzione(em, mezzoId));
                    System.out.println("La data di manutenzione per il mezzo con ID " + mezzoId + " è: " + dataManutenzione);
                    break;
                case 2:
                    creaTratta();
                    break;
                case 3:
                    creaMezzo();
                    break;
                case 4:
                    creaDistributore();
                    break;
                case 5:
                    rimuoviTratta();
                    break;
                case 6:
                    rimuoviMezzo();
                    break;
                case 7:
                    rimuoviDistributore();
                    break;
                case 0:
                    System.out.println("Uscita dal programma.");
                    System.exit(0);
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

    private static void creaTratta() {
        System.out.println("Inserisci la partenza della tratta: ");
        String partenza = scanner.next();
        System.out.println("Inserisci l'arrivo della tratta: ");
        String arrivo = scanner.next();
        System.out.println("Inserisci la durata della tratta: ");
        int durata = scanner.nextInt();
        Tratta tratta = new Tratta(durata, partenza, arrivo);
        trattaDAO.saveTratta(tratta);
        System.out.println("Tratta creata con successo.");
    }

    private static void creaMezzo() {
        System.out.println("Inserisci il tipo del mezzo: ");
        System.out.println("1. BUS");
        System.out.println("2. TRAM");
        int tipoMezzo = Integer.parseInt(scanner.nextLine());

        try {
            switch (tipoMezzo) {
                case 1:
                    TipoMezzo tpM = TipoMezzo.AUTOBUS;
                    List<Tratta> tratte = trattaDAO.listaTratte();
                    System.out.println("Inserisci l'ID della tratta servita: ");
                    int trattaId = Integer.parseInt(scanner.nextLine());
                    Tratta trattaServita = trattaDAO.findTrattaById(trattaId);
                    System.out.println("Inserisci il numero di volte: ");
                    int numeroDiVolte = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserisci il tempo effettivo: ");
                    int tempoEffettivo = Integer.parseInt(scanner.nextLine());
                    Mezzo mezzo = new Mezzo(tpM, trattaServita, numeroDiVolte, tempoEffettivo);
//                        mezzo.setTipoMezzo(tpM);
                    mezzoDAO.save(mezzo);
                    System.out.println("Mezzo creato con successo.");

                    break;

                case 2:

                    TipoMezzo tpM2 = TipoMezzo.TRAM;
                    System.out.println("Inserisci la capienza del mezzo: ");
                    int capienza2 = Integer.parseInt(scanner.nextLine());
                    List<Tratta> tratte2 = trattaDAO.listaTratte();
                    System.out.println("Inserisci l'ID della tratta servita: ");
                    long trattaId2 = Integer.parseInt(scanner.nextLine());
                    Tratta trattaServita2 = trattaDAO.findTrattaById(trattaId2);
                    System.out.println("Inserisci il numero di volte: ");
                    int numeroDiVolte2 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserisci il tempo effettivo: ");
                    int tempoEffettivo2 = Integer.parseInt(scanner.nextLine());
                    Mezzo mezzo2 = new Mezzo(capienza2, trattaServita2, numeroDiVolte2, tempoEffettivo2);
                    mezzo2.setTipoMezzo(tpM2);
                    mezzoDAO.save(mezzo2);
                    System.out.println("Mezzo creato con successo.");

                    break;
                case 3:
                    return;

                default:
                    System.out.println("Opzione non valida.");
            }

        } catch (NumberFormatException n) {
            System.out.println("Opzione non valida, scrivi il numero corretto!");
        }

    }

    private static void creaDistributore() {
        System.out.println("Inserisci il tipo di distributore: ");
        System.out.println("1. Automatico");
        System.out.println("2. Rivenditore Autorizzato");
        int sceltaDi = Integer.parseInt(scanner.nextLine());

        switch (sceltaDi) {
            case 1:

                System.out.println("Il distributore automatico è attivo? (true/false): ");
                boolean attivo = Boolean.parseBoolean(scanner.nextLine());
                if (attivo) {
                    RivenditoreAutorizzatoAutomatico rivenditoreAutomatico = new RivenditoreAutorizzatoAutomatico(true);
                    rivenditoreAutorizzatoDAO.save(rivenditoreAutomatico, true);
                } else {
                    RivenditoreAutorizzatoAutomatico rivenditoreAutomatico = new RivenditoreAutorizzatoAutomatico(false);
                    rivenditoreAutorizzatoDAO.save(rivenditoreAutomatico, false);
                }

                break;
            case 2:

                Rivenditore_Autorizzato rivenditoreAutorizzato = new Rivenditore_Autorizzato();
                rivenditoreAutorizzatoDAO.save(rivenditoreAutorizzato);
                break;

            default:
                System.out.println("Opzione non valida");
        }


    }


    //! DISSOCIARE TRATTA ABBONAMENTO   --> RIMUOVI BIGLIETTO
    //! DISSOCIARE BIGLIETTO        --> RIMUOVI MEZZO
    //! DISSOCIARE MEZZO     --> RIMUOVI TRATTA


    //! SETTO MEZZO DI VIDIMAZIONE ID SU NULL (BIGLIETTO)
    //! RIMUOVO IL MEZZO /
    //! SETTO TRATTA ID SU NULL (MEZZO)
    //!
    public static void rimuoviTratta() {
        System.out.println("INSERISCI ID TRATTA DA ELIMINARE");
        int trattaId = Integer.parseInt(scanner.nextLine());
        Tratta tratta = trattaDAO.findTrattaById(trattaId);      //! TROVO ID TRATTA
        System.out.println(tratta);
        //! TROVO L'ID DEL MEZZO ASSOCIATO
        //! TROVO MEZZO
        long mezzoId = trattaDAO.findMezzoIdByTrattaId(trattaId);
        Mezzo mezzo = mezzoDAO.findById(mezzoId);
        mezzo.setTrattaServita(null);       //! SETTO MEZZU SU NULL
        System.out.println(mezzo);
        mezzoDAO.update(mezzo);
        trattaDAO.update(tratta);
        System.out.println(mezzo);
        trattaDAO.deleteTratta(tratta);
        System.out.println("FINITO IL METODO CONTROLLA DB");

    }

    public static void rimuoviMezzo() {
        System.out.println("INSERISCI ID MEZZO DA ELIMINARE");
        int mezzoId = Integer.parseInt(scanner.nextLine());

        Mezzo mezzo = mezzoDAO.findById(mezzoId);       //! TROVO ID MEZZO
        System.out.println(mezzo);

        long bigliettoId= bigliettoDAO.findBigliettoByMezzoId(mezzoId);
        Biglietto biglietto= bigliettoDAO.findById(bigliettoId);

        biglietto.setMezzoDiVidimazione(null);
        System.out.println(biglietto);
        bigliettoDAO.update(biglietto);
        mezzoDAO.deleteMezzo(mezzo);


        System.out.println("FINITO IL METODO CONTROLLA DB");
    }

    public static void rimuoviDistributore() {
        System.out.println("INSERISCI ID DISTRIBUTORE DA ELIMINARE");
        int distributoreId= Integer.parseInt(scanner.nextLine());

        Rivenditore_Autorizzato rivenditoreAutorizzato= rivenditoreAutorizzatoDAO.findById(distributoreId);

        System.out.println(rivenditoreAutorizzato);

        long bigliettoId= bigliettoDAO.findBigliettoByDistributoreId(distributoreId);
        Biglietto biglietto= bigliettoDAO.findById(bigliettoId);
        biglietto.setEmessoDa(null);
        System.out.println(biglietto);
        bigliettoDAO.update(biglietto);
        rivenditoreAutorizzatoDAO.findByIdAndDelete(distributoreId);

        System.out.println("FINITO IL METODO CONTROLLA DB");

    }


}


