package team4.Navigations;

import team4.dao.Rivenditore_AutorizzatoDAO;
import team4.entities.*;
import team4.enums.DurataTitolo;
import team4.exceptions.UtenteNonTrovatoException;

import java.net.Inet4Address;
import java.time.LocalDate;
import java.util.List;

import static team4.Application.em;
import static team4.Navigations.Navigations.*;
import static team4.Navigations.TesseraNav.*;

public class UtentiNav {

    public static void utente() throws UtenteNonTrovatoException{
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
                break;
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
        if (utente == null) {
            throw new UtenteNonTrovatoException("Utente non trovato");
        }
        if (utente != null) {
            System.out.println(utente);
            if (utente.getTessera() == null) {
                System.out.println("1. Crea tessera");
                System.out.println("2. Esci");
                int sceltaTessera= Integer.parseInt(scanner.nextLine());
                switch (sceltaTessera){
                    case 1:
                        if (chiediCreazioneTesseraSelezioneUtente()) {
                            Tessera tessera = new Tessera();
                            tessera.setUtente(utente);
                            tesseraDAO.save(tessera);
                            System.out.println("Tessera creata e associata all'utente");
                            utente.setPossiedeTessera(true);
                            utenteDAO.update(utente);
                        } else {
                            System.out.println("Nessuna tessera creata");
                        }
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("Opzione non valida");
                }
            } else {
                System.out.println("1. Crea abbonamento");
                System.out.println("2. Esci");

                int sceltaAbbonamento= Integer.parseInt(scanner.nextLine());
                switch (sceltaAbbonamento){
                    case 1:
                        System.out.println("Seleziona tratta:");
                        List<Tratta> tratte = trattaDAO.listaTratte();

                        System.out.println("Inserisci l'ID della tratta selezionata:");
                        long trattaId = Long.parseLong(scanner.nextLine());         //!Tratta

                        Tratta trattaSelezionata = trattaDAO.findTrattaById(trattaId);
                        if (trattaSelezionata != null) {

                            List<Mezzo> mezzi = mezzoDAO.findMezziForTratta(trattaSelezionata.getId());
                            if (mezzi.isEmpty()) {
                                System.out.println("Non ci sono mezzi disponibili per questa tratta.");
                            } else {
                                System.out.println("Mezzi disponibili per la tratta selezionata:");
                                for (Mezzo mezzo : mezzi) {
                                    System.out.println("ID: " + mezzo.getId() + " Mezzo:  " + mezzo.getTipoMezzo() + " Capienza:  " + mezzo.getCapienza());
                                }

                                System.out.println("Inserisci l'ID del mezzo selezionato:");
                                int mezzoId = Integer.parseInt(scanner.nextLine());       //! Mezzo

                                System.out.println("Seleziona un rivenditore");

                                Rivenditore_AutorizzatoDAO rivenditoreDAO = new Rivenditore_AutorizzatoDAO(em);
                                List<Rivenditore_Autorizzato> rivenditori = rivenditoreDAO.findAll();
                                for (Rivenditore_Autorizzato rivenditore : rivenditori) {
                                    if (rivenditore instanceof RivenditoreAutorizzatoAutomatico) {
                                        RivenditoreAutorizzatoAutomatico automatico = (RivenditoreAutorizzatoAutomatico) rivenditore;
                                        System.out.println("ID: " + automatico.getId() + ", Tipo distributore: Automatico, Attivo: " + automatico.isAttivo());
                                    } else {
                                        System.out.println("ID: " + rivenditore.getId() + ", Tipo distributore: Non automatico");
                                    }
                                }
                                 int rivenditoreID= Integer.parseInt(scanner.nextLine());    //! Rivenditore

                                System.out.println("Inserisci la durata dell'Abbonamento: ");
                                System.out.println("1. Settimanale");
                                System.out.println("2. Mensile");
                                int durataA=Integer.parseInt(scanner.nextLine());
                                DurataTitolo durata = null;
                                Abbonamento abbonamento = null; // Dichiarazione della variabile abbonamento fuori dallo switch
                                Mezzo mezzo= mezzoDAO.findById(mezzoId);
                                Rivenditore_Autorizzato emessoDa= rivenditoreDAO.findById(rivenditoreID);
                                Tessera tessera= utente.getTessera();


                                switch (durataA) {
                                    case 1:
                                        durata = DurataTitolo.SETTIMANALE;
                                        abbonamento = new Abbonamento(durata, mezzo, emessoDa, LocalDate.now(), tessera);
                                        abbonamento.setDataDiVidimazione(LocalDate.now());
                                        abbonamento.addTratta(trattaSelezionata); // Correzione della formattazione
                                        bigliettoDAO.emettiAbbonamento(abbonamento);
                                        break;
                                    case 2:
                                        durata = DurataTitolo.MENSILE;
                                        abbonamento = new Abbonamento(durata, mezzo, emessoDa, LocalDate.now(), tessera);
                                        abbonamento.setDataDiVidimazione(LocalDate.now());
                                        abbonamento.addTratta(trattaSelezionata); // Correzione della formattazione
                                        bigliettoDAO.emettiAbbonamento(abbonamento);
                                        break;
                                    case 3:
                                        break;
                                    default:
                                        System.out.println("Opzione non valida");
                                        break;
                                }

                                System.out.println("ABBONAMENTO CREATO");

                            }
                        } else {
                            System.out.println("Tratta non trovata");
                        }

                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("Opzione non valida");
                        break;

                }


            }
        } else {
            System.out.println("Utente non trovato");
        }

    }
}
