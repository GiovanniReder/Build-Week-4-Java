package giovanni.entities;

import giovanni.DAO.*;
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


    public static void startApp() {


        do {

            System.out.println("----APPLICAZIONE INIZIALIZZATA----");
            System.out.println();

            System.out.println("Benvenuto nella nostra applicazione di trasporti");
            System.out.println();

            System.out.println("sei un operatore oppure un utente?");
            System.out.println("1 operatore");
            System.out.println("2 utente");
            System.out.println();

            try {

                int scelta = Integer.parseInt(sc.nextLine());

                switch (scelta) {
                    case 1: {
                        System.out.println("inserisci la tua password:");
                        String password = sc.nextLine();
                        controlloPassword(password);


                        break;

                    }
                    case 2: {
                        System.out.println("benvenuto utente");
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("scelta non valida");
            }


        } while (false);

    }

    public static void controlloPassword(String myStr) {

        if (myStr.equals("SonoIO")) {
            System.out.println("Benvenuto operatore");
            operatore();
        } else System.out.println("password errata");

    }

    public static void operatore() {
        while (true) {
            System.out.println();
            System.out.println("decidi cosa vuoi fare:");
            System.out.println("premi 1 per controllare la lista delle manutenzione dei mezzi");
            System.out.println("premi 2 per controllare quanti biglietti sono stati vidimati in una determinato periodo di tempo");
            System.out.println("premi 3 per controllare la lista mezzi");
            System.out.println("premi 4 per controllare il totale di titoli di viaggio emessi in un periodo di tempo per un punto di emissione");
            System.out.println("premi 5 per visualizzare la lista delle tratte percorse");

            try {
                int scelta = Integer.parseInt(sc.nextLine());


                operatore:
                switch (scelta) {
                    case 1: {
                        System.out.println();
                        System.out.println("Ecco qua la tua lista");
                        mtd.listaManutenzioni().forEach(System.out::println);
                        System.out.println();
                        break;
                    }
                    case 2: {
                        System.out.println();
                        System.out.println("Inserisci le due date:");
                        LocalDate date1 = LocalDate.parse(sc.nextLine());
                        LocalDate date2 = LocalDate.parse(sc.nextLine());
                        System.out.println("questo è il totale dei biglietti vidimati:");
                        md.bigliettiVidimati(date1, date2);
                        System.out.println();
                        break;
                    }
                    case 3: {
                        System.out.println();
                        System.out.println("questa è la lista mezzi");
                        md.listaMezzi();
                        System.out.println();
                        System.out.println("premi 1 per controllare i biglietti vidimati su un determinato mezzo per un periodo di tempo");
                        System.out.println("premi 0 per uscire");
                        int controllo = Integer.parseInt(sc.nextLine());
                        try {
                            switch (controllo) {
                                case 1: {
                                    System.out.println();
                                    System.out.println("per prima cosa scegli le date:");
                                    LocalDate date1 = LocalDate.parse(sc.nextLine());
                                    LocalDate date2 = LocalDate.parse(sc.nextLine());
                                    System.out.println("ora scegli l'id del mezzo");
                                    long idMezzo = Long.parseLong(sc.nextLine());
                                    System.out.println("ecco a te il risultato:");
                                    md.bigliettiVidimatiPerMezzo(date1, date2, idMezzo);
                                    break;
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
                        System.out.println("ora scegli le date da controllare");
                        LocalDate date1 = LocalDate.parse(sc.nextLine());
                        LocalDate date2 = LocalDate.parse(sc.nextLine());
                        System.out.println("questi sono i biglietti stampati");
                        td.titoliViaggioPerPuntoEmissione(date1, date2, idBiglietteria);
                        break;
                    }
                    case 5: {
                        System.out.println();
                        System.out.println("lista delle tratte disponibili:");
                        trattaD.listaTratte();
                        System.out.println();
                        System.out.println("premi 1 se vuoi calcolare il tempo medio di una tratta");
                        System.out.println("premi 0 per uscire");
                        int controllo = Integer.parseInt(sc.nextLine());
                        switch (controllo) {
                            case 1: {
                                try {
                                    System.out.println();
                                    System.out.println("scegli l'id della tratta");
                                    long idTratta = Long.parseLong(sc.nextLine());
                                    System.out.println("ecco a te il risultato:");
                                    trattaD.mediaTratta(idTratta);
                                    break;

                                } catch (Exception ex) {
                                    System.out.println("non esiste una tratta con quell'id");
                                }
                            }
                            case 0: {
                                break operatore;
                            }
                            default:
                                break;
                        }


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
