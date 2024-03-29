package team4.Navigations;

import jakarta.persistence.EntityManager;
import team4.Application;
import team4.dao.MezzoDAO;
import team4.dao.Rivenditore_AutorizzatoDAO;
import team4.dao.TrattaDAO;
import team4.entities.*;
import team4.enums.TipoMezzo;

import java.time.LocalDate;
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
            System.out.println("Menu: ");
            System.out.println("1. Pannello Manutenzione mezzi");
            System.out.println("2. Crea tratta");
            System.out.println("3. Crea mezzo");
            System.out.println("4. Assegna mezzo libero a tratta");
            System.out.println("5. Crea distributore");
            System.out.println("6. Rimuovi tratta");
            System.out.println("7. Rimuovi mezzo");
            System.out.println("8. Rimuovi distributore");
            System.out.println("9. Return");
            System.out.print("Seleziona un'opzione: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    pannelloManutenzioneMezzi();
                    break;
                case 2:
                    creaTratta();
                    break;
                case 3:
                    creaMezzo();
                    break;
                case 4:
                    assegnaATratta();
                    break;
                case 5:
                    creaDistributore();
                    break;
                case 6:
                    rimuoviTratta();
                    break;
                case 7:
                    rimuoviMezzo();
                    break;
                case 8:
                    rimuoviDistributore();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

    private static void assegnaATratta() {
        List<Mezzo> mezziNonAssegnati = mezzoDAO.listaMezziNonAssegnati();
        if (mezziNonAssegnati.isEmpty()) {
            System.out.println("Non ci sono mezzi disponibili per l'assegnazione.");
            return;
        }

        System.out.println("Mezzi non assegnati a una tratta:");
        for (Mezzo mezzo : mezziNonAssegnati) {
            System.out.println(mezzo.getId() + " - " + mezzo.getTipoMezzo() + ", Capienza: " + mezzo.getCapienza());
        }

        System.out.println("Inserisci l'ID del mezzo da assegnare a una tratta:");
        long mezzoId = Long.parseLong(scanner.nextLine());
        Mezzo mezzoSelezionato = mezziNonAssegnati.stream().filter(m -> m.getId() == mezzoId).findFirst().orElse(null);

        if (mezzoSelezionato == null) {
            System.out.println("Mezzo non valido o già assegnato.");
            return;
        }

        List<Tratta> tratte = trattaDAO.listaTratte();
        if (tratte.isEmpty()) {
            System.out.println("Non ci sono tratte disponibili.");
            return;
        }

        System.out.println("Tratte disponibili:");
        for (Tratta tratta : tratte) {
            System.out.println(tratta.getId() + " - Da: " + tratta.getPartenza() + " a: " + tratta.getArrivo() + ", Durata: " + tratta.getDurata() + " minuti");
        }

        System.out.println("Inserisci l'ID della tratta a cui assegnare il mezzo:");
        long trattaId = Long.parseLong(scanner.nextLine());
        Tratta trattaSelezionata = tratte.stream().filter(t -> t.getId() == trattaId).findFirst().orElse(null);

        if (trattaSelezionata == null) {
            System.out.println("Tratta non valida.");
            return;
        }

        // Assegnazione del mezzo alla tratta
        mezzoSelezionato.setTrattaServita(trattaSelezionata);
        mezzoDAO.update(mezzoSelezionato); // Assumi che questo metodo aggiorni il mezzo nel database, incluse le relazioni
        System.out.println("Mezzo ID: " + mezzoId + " assegnato alla tratta ID: " + trattaId + " con successo.");
    }


    private static void creaTratta() {
        System.out.println("Inserisci la partenza della tratta: ");
        String partenza = scanner.nextLine();
        System.out.println("Inserisci l'arrivo della tratta: ");
        String arrivo = scanner.nextLine();
        System.out.println("Inserisci la durata della tratta: ");
        int durata = Integer.parseInt(scanner.nextLine());
        Tratta tratta = new Tratta(durata, partenza, arrivo);
        trattaDAO.saveTratta(tratta);
        System.out.println("Tratta creata con successo.");
    }

    private static void pannelloManutenzioneMezzi(){
        System.out.println("--------------------------------------------------------------------------------------------");
        List<Mezzo> listaMezzi = mezzoDAO.listaMezzi();
        for (Mezzo mezzo : listaMezzi) {
            System.out.println(mezzo.stringaPerPannello());
        }
        System.out.println("Seleziona una di queste opzioni: ");
        System.out.println("1. Manda un mezzo in manutenzione");
        System.out.println("2. Manda un mezzo in servizio");
        System.out.println("3. Storico Manutenzioni");
        System.out.println("4. Indietro");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                mandaMezzoInManutenzione();
                break;
            case 2:
                concludiManutenzioneMezzo();
                break;
            case 3:
                storicoManutenzioni();
            case 4:
                System.out.println("Ritorno al menu");
                break;
            default:
                System.out.println("Scelta non valida. Riprova.");
        }
    }

    private static void storicoManutenzioni() {
        System.out.println("Inserisci l'ID del mezzo di cui vuoi vedere lo storico delle manutenzioni:");
        try {
            long mezzoId = Long.parseLong(scanner.nextLine()); // Legge l'ID inserito dall'utente
            Mezzo mezzo = mezzoDAO.findById(mezzoId); // Cerca il mezzo tramite il DAO

            if (mezzo == null) {
                System.out.println("Mezzo non trovato.");
                return;
            }

            List<Manutenzione> manutenzioni = mezzo.getManutenzioni(); // Ottiene le manutenzioni del mezzo

            if (manutenzioni.isEmpty()) {
                System.out.println("Nessuna manutenzione registrata per questo mezzo.");
                return;
            }

            System.out.println("Storico delle manutenzioni per il mezzo ID: " + mezzoId);
            for (Manutenzione manutenzione : manutenzioni) {
                System.out.println("Inizio manutenzione: " + manutenzione.getData_inizio() +
                        ", Fine manutenzione: " + (manutenzione.getData_fine() != null ? manutenzione.getData_fine().toString() : "In corso"));
            }
        } catch (NumberFormatException e) {
            System.out.println("ID non valido. Inserire un numero.");
        }
    }


    private static void mandaMezzoInManutenzione() {
        System.out.println("Inserisci l'ID del mezzo da mandare in manutenzione:");
        Long mezzoId = Long.parseLong(scanner.nextLine());
        Mezzo mezzo = mezzoDAO.findById(mezzoId);
        if (mezzo != null) {
            Manutenzione manutenzione = new Manutenzione(mezzo);
            mezzo.getManutenzioni().add(manutenzione);
            mezzoDAO.update(mezzo);
            System.out.println("Mezzo mandato in manutenzione.");
        } else {
            System.out.println("Mezzo non trovato.");
        }
    }

    private static void concludiManutenzioneMezzo() {
        System.out.println("Inserisci l'ID del mezzo per cui concludere la manutenzione:");
        Long mezzoId = Long.parseLong(scanner.nextLine());
        Mezzo mezzo = mezzoDAO.findById(mezzoId);
        if (mezzo != null && !mezzo.getManutenzioni().isEmpty()) {
            Manutenzione ultimaManutenzione = mezzo.getManutenzioni().get(mezzo.getManutenzioni().size() - 1);
            if (ultimaManutenzione.getData_fine() == null) {
                ultimaManutenzione.setData_fine(LocalDate.now());
                mezzoDAO.update(mezzo);
                System.out.println("Manutenzione conclusa con successo.");
            } else {
                System.out.println("Non ci sono manutenzioni attive per questo mezzo.");
            }
        } else {
            System.out.println("Mezzo non trovato o nessuna manutenzione presente.");
        }
    }

    private static void creaMezzo() {
        System.out.println("Inserisci il tipo del mezzo: ");
        System.out.println("1. BUS");
        System.out.println("2. TRAM");

        try {
                 int tipoMezzo = Integer.parseInt(scanner.nextLine());
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
//                    System.out.println("Inserisci la capienza del mezzo: ");
//                    int capienza2 = Integer.parseInt(scanner.nextLine());
                    List<Tratta> tratte2 = trattaDAO.listaTratte();
                    System.out.println("Inserisci l'ID della tratta servita: ");
                    int trattaId2 = Integer.parseInt(scanner.nextLine());
                    Tratta trattaServita2 = trattaDAO.findTrattaById(trattaId2);
                    System.out.println("Inserisci il numero di volte: ");
                    int numeroDiVolte2 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserisci il tempo effettivo: ");
                    int tempoEffettivo2 = Integer.parseInt(scanner.nextLine());
                    Mezzo mezzo2 = new Mezzo(tpM2, trattaServita2, numeroDiVolte2, tempoEffettivo2);
//                    mezzo2.setTipoMezzo(tpM2);
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

        long bigliettoId = bigliettoDAO.findBigliettoByMezzoId(mezzoId);
        Biglietto biglietto = bigliettoDAO.findById(bigliettoId);

        biglietto.setMezzoDiVidimazione(null);
        System.out.println(biglietto);
        bigliettoDAO.update(biglietto);
        mezzoDAO.deleteMezzo(mezzo);


        System.out.println("FINITO IL METODO CONTROLLA DB");
    }

    public static void rimuoviDistributore() {
        System.out.println("INSERISCI ID DISTRIBUTORE DA ELIMINARE");
        int distributoreId = Integer.parseInt(scanner.nextLine());

        Rivenditore_Autorizzato rivenditoreAutorizzato = rivenditoreAutorizzatoDAO.findById(distributoreId);

        System.out.println(rivenditoreAutorizzato);

        long bigliettoId = bigliettoDAO.findBigliettoByDistributoreId(distributoreId);
        Biglietto biglietto = bigliettoDAO.findById(bigliettoId);
        biglietto.setEmessoDa(null);
        System.out.println(biglietto);
        bigliettoDAO.update(biglietto);
        rivenditoreAutorizzatoDAO.findByIdAndDelete(distributoreId);

        System.out.println("FINITO IL METODO CONTROLLA DB");

    }


}


