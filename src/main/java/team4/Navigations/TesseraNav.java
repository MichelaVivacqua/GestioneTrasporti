package team4.Navigations;

import static team4.Navigations.Navigations.scanner;
import static team4.Navigations.Navigations.tessereDAO;

public class TesseraNav {
    public static boolean chiediCreazioneTessera() {
        System.out.println("Vuoi creare una tessera?");
        System.out.println("1. SI");
        System.out.println("2. No");
        int sceltaT = Integer.parseInt(scanner.nextLine());
        return sceltaT == 1;
    }



    public static void cercaPerTessera() {
        System.out.println("Inserisci il numero di tessera");
        int tesseraId = Integer.parseInt(scanner.nextLine());
        System.out.println(tessereDAO.findById(tesseraId));
        try {
//            long tesseraId = Long.parseLong(id);
            tessereDAO.rinnovaTessera(tesseraId);
        } catch (NumberFormatException e) {
            System.out.println("ID della tessera non valido. Riprova.");
        }
    }



}
