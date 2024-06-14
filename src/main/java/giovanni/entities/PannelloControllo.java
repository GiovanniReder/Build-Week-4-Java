package giovanni.entities;

import giovanni.DAO.*;
import giovanni.enums.TipoAbbonamentoEnum;
import giovanni.enums.TipoMezzoEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class PannelloControllo {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trasporto_pubblico");

    static Scanner sc = new Scanner(System.in);
    static EntityManager em = emf.createEntityManager();

    static ManutenzioneDAO mtd = new ManutenzioneDAO(em);
    static MezziDAO md = new MezziDAO(em);
    static BiglietteriaDAO bd = new BiglietteriaDAO(em);
    static TitoloDiViaggioDAO td = new TitoloDiViaggioDAO(em);
    static TrattaDAO trattaD = new TrattaDAO(em);
    static TesseraDAO tesseraDAO = new TesseraDAO(em);
    static UtentiDAO utentiDAO = new UtentiDAO(em);
    static TrattaMezziDAO trattaMezziDAO = new TrattaMezziDAO(em);


    public static void startApp() {


        do {

            System.out.println("----APPLICAZIONE INIZIALIZZATA----");
            System.out.println();

            System.out.println("Benvenuto nella nostra applicazione di trasporti");
            System.out.println();

            System.out.println("sei un operatore oppure un utente?");
            System.out.println("1. operatore");
            System.out.println("2. Utente registrato");
            System.out.println("3. Utente non registrato");
            System.out.println("0. Esci");

            try {
                int scelta = Integer.parseInt(sc.nextLine());

                switch (scelta) {
                    case 1: {
                        boolean passwordCorretta = false;
                        do {
                            switch (scelta) {
                                case 1: {
                                    System.out.println("Inserisci la tua password");

                                    String password = sc.nextLine();
                                    if (password.equals("Team5")) {
                                        operatore();
                                        passwordCorretta = true; //
                                    } else {
                                        System.out.println("Password errata riprova");
                                    }
                                    break;
                                }
                                case 0: {
                                    break;
                                }

                            }
                        } while (!passwordCorretta);
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
                        Utenti utente = new Utenti(cognome, nome);
                        utentiDAO.save(utente);
                        Tessera tessera = new Tessera(LocalDate.now(), utente);
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
                        Biglietto biglietto = new Biglietto(LocalDate.now(), biglietteriaScelta);
                        td.save(biglietto);
                        long bigliettoDaUsare = biglietto.getId();
                        System.out.println("premi 1 se vuoi validarlo");
                        System.out.println("0 per uscire");
                        int scelta3 = Integer.parseInt(sc.nextLine());
                        switch (scelta3) {
                            case 1: {
                                System.out.println();
                                md.listaMezzi().forEach(System.out::println);
                                System.out.println();
                                System.out.println("scegli l'id del mezzo dove vuoi timbrare il biglietto");
                                long idMezzo = Long.parseLong(sc.nextLine());
                                md.timbratura(bigliettoDaUsare, idMezzo);
                                break;
                            }
                        }
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


    public static void controlloPassword(String myStr) {

        if (myStr.equals("SonoIO")) {
            System.out.println("Benvenuto operatore");
            operatore();
        } else {
            System.out.println("password errata");

        }

    }

    public static void operatore() {
        while (true) {
            System.out.println();
            System.out.println("scegli un opzione:");
            System.out.println(" 1. per controllare i periodi di manutenzione filtrati per data");
            System.out.println(" 2. per controllare quanti biglietti sono stati vidimati in una determinato periodo di tempo");
            System.out.println(" 3. per controllare la lista mezzi");
            System.out.println(" 4. per controllare il totale di titoli di viaggio emessi in un periodo di tempo per un punto di emissione");
            System.out.println(" 5. per visualizzare la lista delle tratte percorse");
            System.out.println(" 6. Inserisci un nuovo mezzo nel database");

            try {
                int scelta = Integer.parseInt(sc.nextLine());


                operatore:
                switch (scelta) {
                    case 1: {
                        System.out.println();
                        System.out.println("inserisci data inizio:");
                        LocalDate date1 = LocalDate.parse(sc.nextLine());
                        System.out.println("inserisci data fine:");
                        LocalDate date2 = LocalDate.parse(sc.nextLine());
                        mtd.sonoInManutenzione(date1, date2).forEach(System.out::println);
                        System.out.println();
                        break;
                    }
                    case 2: {
                        System.out.println();
                        System.out.println("inserisci data inizio:");
                        LocalDate date1 = LocalDate.parse(sc.nextLine());
                        System.out.println("inserisci data fine:");
                        LocalDate date2 = LocalDate.parse(sc.nextLine());
                        System.out.println("questo è il totale dei biglietti vidimati:");
                        md.bigliettiVidimati(date1, date2);
                        System.out.println();
                        break;
                    }
                    case 3: {
                        System.out.println();
                        System.out.println("questa è la lista mezzi");
                        md.listaMezzi().forEach(System.out::println);
                        System.out.println();
                        System.out.println("premi 1 per controllare i biglietti vidimati su un determinato mezzo per un periodo di tempo");
                        System.out.println("premi 0 per uscire");
                        int controllo = Integer.parseInt(sc.nextLine());
                        try {
                            switch (controllo) {
                                case 1: {
                                    System.out.println();
                                    System.out.println("inserisci data inizio:");
                                    LocalDate date1 = LocalDate.parse(sc.nextLine());
                                    System.out.println("inserisci data fine:");
                                    LocalDate date2 = LocalDate.parse(sc.nextLine());
                                    System.out.println("ora scegli l'id del mezzo");
                                    long idMezzo = Long.parseLong(sc.nextLine());
                                    System.out.println("ecco a te il risultato:");
                                    md.bigliettiVidimatiPerMezzo(date1, date2, idMezzo);
                                    break operatore;

                                }
                                case 0: {
                                    break operatore;
                                }
                                default:
                                    break;
                            }

                        } catch (Exception ex) {
                            System.out.println("non hai scelto correttamente");
                        }
                    }
                    case 4: {
                        System.out.println();
                        System.out.println("questa è la lista delle biglietterie ");
                        bd.listaBiglietterie().forEach(System.out::println);
                        System.out.println("Scegli l'id della biglietteria da controllare");
                        long idBiglietteria = Integer.parseInt(sc.nextLine());
                        System.out.println("inserisci data inizio:");
                        LocalDate date1 = LocalDate.parse(sc.nextLine());
                        System.out.println("inserisci data fine:");
                        LocalDate date2 = LocalDate.parse(sc.nextLine());
                        System.out.println();
                        td.titoliViaggioPerPuntoEmissione(date1, date2, idBiglietteria);
                        break;
                    }
                    case 5: {
                        System.out.println();
                        System.out.println("lista delle tratte percorse dai mezzi:");
                        trattaMezziDAO.listaTratteMezzi().forEach(System.out::println);
                        System.out.println();
                        System.out.println("premi 1 per visualizzare il tempo medio di percorrenza della tratta");
                        System.out.println("premi 0 per uscire");
                        int controllo = Integer.parseInt(sc.nextLine());
                        switch (controllo) {
                            case 1: {
                                try {
                                    System.out.println();
                                    System.out.println("inserisci l'id della tratta");
                                    long idTratta = Long.parseLong(sc.nextLine());
                                    trattaD.mediaTratta(idTratta);
                                    Tratta searchTratta = trattaD.searchById(idTratta);
                                    System.out.println(searchTratta.getTempoMedioPercorrenza());
                                    break;

                                } catch (Exception ex) {
                                    System.out.println("non esiste una tratta con quell'id");
                                }
                            }
                            case 0: {
                                break operatore;
                            }
                            default:
                                break operatore;
                        }


                    }
                    case 6: {
                        System.out.println();
                        System.out.println("Inserisci il tipo di mezzo:");
                        System.out.println("1. TRAM ");
                        System.out.println("2. BUS ");
                        try {
                            int controllo = Integer.parseInt(sc.nextLine());
                            TipoMezzoEnum tipoMezzo = null;
                            switch (controllo) {
                                case 1:
                                    tipoMezzo = TipoMezzoEnum.TRAM;
                                    break;
                                case 2:
                                    tipoMezzo = TipoMezzoEnum.BUS;
                                    break;
                                default:
                                    System.out.println("Scelta non valida. Riprova.");
                                    break;
                            }
                            if (tipoMezzo != null) {
                                Mezzi mezzo = new Mezzi(tipoMezzo);
                                md.save(mezzo);
                                ;
                            }
                        } catch (NumberFormatException ex) {
                            System.out.println("Input non valido. Inserisci un numero.");
                        }
                        break;
                    }
                    default:
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("scelta non valida");
            }

        }
    }


}
