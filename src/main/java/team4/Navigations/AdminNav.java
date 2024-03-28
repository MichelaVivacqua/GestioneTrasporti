package team4.Navigations;

import jakarta.persistence.EntityManager;
import team4.Application;
import team4.dao.Rivenditore_Autorizzato;
import team4.dao.MezzoDAO;
import team4.dao.TrattaDAO;

import java.util.Scanner;

public class AdminNav {
    private static final EntityManager em = Application.em;
    private static final MezzoDAO mezzoDAO = new MezzoDAO(em);
    private static final TrattaDAO trattaDAO = new TrattaDAO(em);
    private static final Rivenditore_Autorizzato RIVENDITORE_AUTORIZZATO = new Rivenditore_Autorizzato(em);


    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("1. Trova data di manutenzione tramite ID del mezzo");
//            System.out.println("2. Crea tratta");
//            System.out.println("3. Crea mezzo");
//            System.out.println("4. Crea distributore");
//            System.out.println("0. Esci");
//
//            System.out.print("Seleziona un'opzione: ");
//            int choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//                    System.out.print("Inserisci l'ID del mezzo: ");
//                    long mezzoId = scanner.nextLong();
//                    String dataManutenzione = mezzoDAO.getDataManutenzioneById(mezzoId);
//                    System.out.println("La data di manutenzione per il mezzo con ID " + mezzoId + " è: " + dataManutenzione);
//                    break;
//                case 2:
//                    creaTratta(scanner);
//                    break;
//                case 3:
//                    creaMezzo(scanner);
//                    break;
//                case 4:
//                    System.out.print("Inserisci il tipo di distributore (AUTOMATICO/GENERICO): ");
//                    String tipoDistributoreString = scanner.next();
//                    TipoDistributore tipoDistributore = TipoDistributore.valueOf(tipoDistributoreString);
//
//                    if (tipoDistributore == TipoDistributore.Automatico) {
//                        System.out.print("Il distributore automatico è attivo? (true/false): ");
//                        boolean attivo = scanner.nextBoolean();
//
//                        RivenditoreAutorizzatoAutomatico distributoreAutomatico = new RivenditoreAutorizzatoAutomatico(true);
//
//
//                        distributoriDAO.save(distributoreAutomatico, attivo);
//                    } else if (tipoDistributore == TipoDistributore.Rivenditore) {
//                        Rivenditore_Autorizzato distributore = new Rivenditore_Autorizzato();
//
//
//                        distributoriDAO.save(distributore);
//                    } else {
//                        System.out.println("Tipo di distributore non valido.");
//                    }
//                    break;
//                case 0:
//                    System.out.println("Uscita dal programma.");
//                    System.exit(0);
//                default:
//                    System.out.println("Scelta non valida. Riprova.");
//            }
//        }
    }
    private static void creaTratta(Scanner scanner) {
//        System.out.print("Inserisci la partenza della tratta: ");
//        String partenza = scanner.next();
//        System.out.print("Inserisci l'arrivo della tratta: ");
//        String arrivo = scanner.next();
//        System.out.print("Inserisci la distanza della tratta: ");
//        double distanza = scanner.nextDouble();
//
//        Tratta tratta = new Tratta(2,"Catania","Milano");
//        trattaDAO.saveTratta(tratta);
//        System.out.println("Tratta creata con successo.");
    }
    private static void creaMezzo(Scanner scanner) {
//        System.out.print("Inserisci il tipo del mezzo (BUS/TRAIN/AUTO): ");
//        String tipoMezzoString = scanner.next();
//        TipoMezzo tipoMezzo = TipoMezzo.valueOf(tipoMezzoString);
//
//        System.out.print("Inserisci la capienza del mezzo: ");
//        int capienza = scanner.nextInt();
//
//        System.out.print("Inserisci lo stato del mezzo (IN_MANUTENZIONE/ATTIVO): ");
//        String statoMezzoString = scanner.next();
//        StatoMezzo statoMezzo = StatoMezzo.valueOf(statoMezzoString);
//
//        System.out.print("Inserisci la data di manutenzione del mezzo (AAAA-MM-GG): ");
//        String dataManutenzione = scanner.next();
//        LocalDate dataManutenzioneParsed = LocalDate.parse(dataManutenzione);
//
//        Mezzo mezzo = new Mezzo(tipoMezzo, capienza, statoMezzo, dataManutenzioneParsed);
//        mezzoDAO.save(mezzo);
//        System.out.println("Mezzo creato con successo.");
    }

}
