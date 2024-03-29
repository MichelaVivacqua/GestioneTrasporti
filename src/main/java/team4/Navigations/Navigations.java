package team4.Navigations;

import team4.dao.*;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static team4.Application.em;
import static team4.Navigations.AdminNav.admin;
import static team4.Navigations.UtentiNav.utente;

public class Navigations {
    public static Scanner scanner = new Scanner(System.in);

    public static BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
    public static TrattaDAO trattaDAO = new TrattaDAO(em);
    public static MezzoDAO mezzoDAO = new MezzoDAO(em);
    public static UtenteDAO utenteDAO = new UtenteDAO(em);
    public static TesseraDAO tesseraDAO = new TesseraDAO(em);
    public static Rivenditore_AutorizzatoDAO rivenditoreAutorizzatoDAO = new Rivenditore_AutorizzatoDAO(em);

    public static AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void menu() {

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Utente");
            System.out.println("2. Amministratore");
            System.out.println("3. Esci");
            System.out.print("Seleziona un'opzione: ");

            try {
            int scelta = Integer.parseInt(scanner.nextLine());
            switch (scelta) {
                case 1:
                    utente();
                    break;
                case 2:
                    admin();
                    break;
                case 3:
                    System.out.println("Uscita dal programma.");
                    System.exit(0);
                default:
                    System.out.println("Scelta non valida, riprova");
                    break;
            }

            }catch (NumberFormatException ne){
                System.out.println("Opzione non valida, scrivi il numero corretto!");
//                ne.printStackTrace();
            }

        }
    }

}


//try {
//        switch (scelta) {
//        case 1:
//creaUtente();
//                        break;
//                                case 2:
//cercaUtente();
////                        }
//                        break;
//                                case 3:
//controllaValiditaTessera();
//                        break;
//                                case 4:
//                                System.out.println("Uscita...");
//                        break;
//default:
//        System.out.println("Scelta non valida. Riprova.");
//                        break;
//
//                                }
//                                } catch (Exception e) {
//        System.out.println("Input non valido. Riprova.");
//                scanner.next(); // Consuma l'input errato per evitare loop infinito
//            }