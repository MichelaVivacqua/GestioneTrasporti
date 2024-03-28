package team4.Navigations;

import team4.entities.Tessera;
import team4.entities.Utente;

import static team4.Navigations.Navigations.*;
import static team4.Navigations.TesseraNav.*;

public class UtentiNav {


    public static void utente() {
        System.out.println("1. CreaUtente");
        System.out.println("2. Seleziona Utente");
        System.out.println("3. Esci");
        System.out.print("Seleziona un'opzione: ");

        int sceltaU = Integer.parseInt(scanner.nextLine());
        switch (sceltaU) {
            case 1:
                creaUtente();
                break;
            case 2:
                cercaUtente();

            case 3:
                utente();
                break;
            default:
                System.out.println("Opzione non valida");
                break;
        }
    }


    public static void creaUtente() {
        System.out.println("Inserisci un nome");
        String nome = scanner.nextLine();
        System.out.println("Inserisci un cognome");
        String cognome = scanner.nextLine();
        boolean tessera = chiediCreazioneTessera();
        Utente utente = new Utente(nome, cognome, tessera);
        utenteDAO.save(utente);
        System.out.println("UTENTE " + nome + " CREATO CON SUCCESSO");
    }

    public static void cercaUtente() {
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
               return;
            default:
                System.out.println("Scelta non valida. Riprova.");
                break;
        }
    }

    public static void cercaPerIdUtente() {
        System.out.println("Inserisci l'Id dell'utente");
        int utenteId = Integer.parseInt(scanner.nextLine());
        System.out.println(utenteDAO.findById(utenteId));
        Utente utente = utenteDAO.findById(utenteId);
        if (utente != null) {
            System.out.println(utente);
            if (utente.getTessera() == null) {
                if (chiediCreazioneTesseraSelezioneUtente()) {
                    Tessera tessera = new Tessera();
                    tessera.setUtente(utente);
                    tessereDAO.save(tessera);
                    System.out.println("Tessera creata e associata all'utente");
                    utente.setPossiedeTessera(true);
                    utenteDAO.update(utente);
                } else {
                    System.out.println("Nessuna tessera creata");
                }
            } else {
                System.out.println("L'utente possiede già una tessera associata");
            }
        } else {
            System.out.println("Utente non trovato");
        }

    }
}
