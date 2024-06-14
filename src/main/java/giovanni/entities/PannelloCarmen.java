package giovanni.entities;

import giovanni.DAO.*;
import giovanni.enums.TipoAbbonamentoEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class PannelloCarmen {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trasporto_pubblico");

    static Scanner sc = new Scanner(System.in);
    static EntityManager em = emf.createEntityManager();
    static UtentiDAO utentiDAO = new UtentiDAO(em);

    static ManutenzioneDAO mtd = new ManutenzioneDAO(em);
    static MezziDAO md = new MezziDAO(em);
    static BiglietteriaDAO bd = new BiglietteriaDAO(em);
    static TitoloDiViaggioDAO td = new TitoloDiViaggioDAO(em);
    static TrattaDAO trattaD = new TrattaDAO(em);
    static TesseraDAO tesseraDAO = new TesseraDAO(em);

    public static void startApp() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("---- APPLICAZIONE INIZIALIZZATA ----");
            System.out.println();

            System.out.println("Benvenuto nella nostra applicazione di trasporti");
            System.out.println();

            System.out.println("Sei un operatore oppure un utente?");
            System.out.println("1. Operatore");
            System.out.println("2. Utente registrato");
            System.out.println("3. Utente non registrato");
            System.out.println("0. Esci");

            try {
                int scelta = Integer.parseInt(sc.nextLine());

                switch (scelta) {
                    case 1: {
                        System.out.println("Inserisci la tua password:");
                        String password = sc.nextLine();
                        // Controllo della password
                        // controlloPassword(password);
                        break;
                    }
                    case 2: {
                        System.out.println("Inserisci l'ID della tua tessera per visualizzare le opzioni:");
                        int tesseraID = Integer.parseInt(sc.nextLine());
                        bentornatoUtente(tesseraID);
                        break;
                    }
                    case 3: {
                        benvenutoUtente();
                        break;
                    }
                    case 0: {
                        System.out.println("A presto!");
                        return;
                    }
                    default: {
                        System.out.println("Scelta non valida");
                        break;
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("Input non valido. Inserisci un numero.");
            }

        } while (true);
    }

    public static void bentornatoUtente(int tesseraID) {
        Scanner sc = new Scanner(System.in);
        Tessera tessera = tesseraDAO.searchById(tesseraID);

        if (tessera == null) {
            System.out.println("Tessera non trovata. Riprova.");
            return;
        }

        do {
            System.out.println("Scegli cosa fare:");
            System.out.println("1. Rinnova la tua tessera");
            System.out.println("2. Crea un nuovo abbonamento");
            System.out.println("0. Esci");

            try {
                int scelta1 = Integer.parseInt(sc.nextLine());

                switch (scelta1) {
                    case 1: {
                        System.out.println("Rinnovo tessera...");
                        tesseraDAO.rinnovoTessera(tesseraID);
                        break;
                    }
                    case 2: {
                        System.out.println("Scegli la durata dell'abbonamento:");
                        System.out.println("1. Settimanale");
                        System.out.println("2. Mensile");
                        int tipoAbbonamento = Integer.parseInt(sc.nextLine());

                        System.out.println("Inserisci l'id della biglietteria dalla quale acquistare l'abbonamento:");
                        bd.listaBiglietterie().forEach(System.out::println);
                        int biglietteriaID = Integer.parseInt(sc.nextLine());
                        Biglietteria biglietteriaScelta = bd.searchById(biglietteriaID);

                        TipoAbbonamentoEnum tipoAbbonamentoEnum = null;
                        if (tipoAbbonamento == 1) {
                            tipoAbbonamentoEnum = TipoAbbonamentoEnum.SETTIMANALE;
                        } else if (tipoAbbonamento == 2) {
                            tipoAbbonamentoEnum = TipoAbbonamentoEnum.MENSILE;
                        } else {
                            System.out.println("Tipo di abbonamento non valido.");
                            break;
                        }
                        Abbonamento abbonamento = new Abbonamento(LocalDate.now(), biglietteriaScelta, tipoAbbonamentoEnum, tessera);
                        td.save(abbonamento);
                        break;
                    }
                    case 0: {
                        System.out.println("Torno al menu principale.");
                        return;
                    }
                    default: {
                        System.out.println("Scelta non valida.");
                        break;
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("Input non valido. Inserisci un numero.");
            }

        } while (true);
    }
    public static void benvenutoUtente() {
        System.out.println("Benvenuto");

        do {
            System.out.println("Scegli cosa fare:");
            System.out.println("1. Crea la tua tessera");
            System.out.println("2. Acquista un biglietto");
            System.out.println("0. Esci");

            try {
                int scelta1 = Integer.parseInt(sc.nextLine());

                switch (scelta1) {
                    case 1: {
                        System.out.println("Inserisci il tuo nome:");
                        String nome = sc.nextLine();
                        System.out.println("Inserisci il tuo cognome:");
                        String cognome = sc.nextLine();
                        Utenti utente = new Utenti(cognome,nome);
                        utentiDAO.save(utente);
                        Tessera tessera = new Tessera(LocalDate.now(),utente);
                        tesseraDAO.save(tessera);
                        int intero = (int) tessera.getNumTessera();
                        bentornatoUtente(intero);
                        break;
                    }
                    case 2: {
                        System.out.println("Inserisci l'id della biglietteria dalla quale acquistare il biglietto:");
                        bd.listaBiglietterie().forEach(System.out::println);
                        int biglietteriaID = Integer.parseInt(sc.nextLine());
                        Biglietteria biglietteriaScelta = bd.searchById(biglietteriaID);
                        Biglietto biglietto = new Biglietto(LocalDate.now(),biglietteriaScelta);
                        break;
                    }
                    case 0: {
                        System.out.println("Torno al menu principale.");
                        return;
                    }
                    default: {
                        System.out.println("Scelta non valida.");
                        break;
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("Input non valido. Inserisci un numero.");
            }

        } while (true);
    }
}