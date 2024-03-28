package team4.Navigations;

import jakarta.persistence.EntityManager;
import team4.Application;
import team4.dao.Rivenditore_AutorizzatoDAO;
import team4.dao.MezzoDAO;
import team4.dao.TrattaDAO;
import team4.entities.Mezzo;
import team4.entities.RivenditoreAutorizzatoAutomatico;
import team4.entities.Tratta;

import java.util.Scanner;

public class AdminNav {
    private static final EntityManager em = Application.em;
    private static final MezzoDAO mezzoDAO = new MezzoDAO(em);
    private static final TrattaDAO trattaDAO = new TrattaDAO(em);
    private static final Rivenditore_AutorizzatoDAO RIVENDITORE_AUTORIZZATO = new Rivenditore_AutorizzatoDAO(em);


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Verifica manutenzione tramite ID del mezzo");
            System.out.println("2. Crea tratta");
            System.out.println("3. Crea mezzo");
            System.out.println("4. Crea distributore");
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
                    creaTratta(scanner);
                    break;
                case 3:
                    creaMezzo(scanner);
                    break;
                case 4:
                    creaDistributore(scanner);
                    break;
                case 0:
                    System.out.println("Uscita dal programma.");
                    System.exit(0);
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }
    private static void creaTratta(Scanner scanner) {
        System.out.print("Inserisci la partenza della tratta: ");
        String partenza = scanner.next();
        System.out.print("Inserisci l'arrivo della tratta: ");
        String arrivo = scanner.next();
        System.out.print("Inserisci la durata della tratta: ");
        int durata = scanner.nextInt();

        Tratta tratta = new Tratta(durata,partenza,arrivo);
        trattaDAO.saveTratta(tratta);
        System.out.println("Tratta creata con successo.");
    }
    private static void creaMezzo(Scanner scanner) {
        System.out.print("Inserisci il tipo del mezzo: ");
        String tipoMezzoString = scanner.next();


        System.out.print("Inserisci la capienza del mezzo: ");
        int capienza = scanner.nextInt();

        System.out.print("Inserisci l'ID della tratta servita: ");
        long trattaId = scanner.nextLong();
        Tratta trattaServita = trattaDAO.findTrattaById(trattaId);

        System.out.print("Inserisci il numero di volte: ");
        int numeroDiVolte = scanner.nextInt();

        System.out.print("Inserisci il tempo effettivo: ");
        int tempoEffettivo = scanner.nextInt();

        Mezzo mezzo = new Mezzo(capienza, trattaServita, numeroDiVolte, tempoEffettivo);
        mezzoDAO.save(mezzo);
        System.out.println("Mezzo creato con successo.");
    }

    private static void creaDistributore(Scanner scanner) {
        System.out.print("Inserisci il tipo di distributore: ");
        String tipoDistributoreString = scanner.next();

        if (tipoDistributoreString.equalsIgnoreCase("AUTOMATICO")) {
            System.out.print("Il distributore automatico è attivo? (true/false): ");
            boolean attivo = scanner.nextBoolean();

            RivenditoreAutorizzatoAutomatico distributoreAutomatico = new RivenditoreAutorizzatoAutomatico(attivo);
            em.getTransaction().begin();
            em.persist(distributoreAutomatico);
            em.getTransaction().commit();

            System.out.println("Distributore automatico creato con successo.");
        } else if (tipoDistributoreString.equalsIgnoreCase("GENERICO")) {
            Rivenditore_Autorizzato distributore = new Rivenditore_Autorizzato(em);
            em.getTransaction().begin();
            em.persist(distributore);
            em.getTransaction().commit();

            System.out.println("Distributore generico creato con successo.");
        } else {
            System.out.println("Tipo di distributore non valido.");
        }
    }
}


