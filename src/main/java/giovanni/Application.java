package giovanni;

import giovanni.DAO.*;
import giovanni.entities.*;
import giovanni.supplier.Suppliers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {


    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trasporto_pubblico");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        System.out.println("Hello World!");
        Suppliers suppliers = new Suppliers();
        //  suppliers.creaBiglietti2();
        //  suppliers.creaMezzi2();
        //  suppliers.creaUtenti(); NON FUNZIONA!!


//        UtentiDAO ud = new UtentiDAO(em);
//        Utenti u1 = new Utenti("LUIGI", "ROSSI");
//        ud.save(u1);

//        Utenti uFDB = ud.searchById(1);
//
        TesseraDAO td = new TesseraDAO(em);
//        Tessera t1 = new Tessera(LocalDate.now(), uFDB);
//        td.save(t1);


        BiglietteriaDAO bd = new BiglietteriaDAO(em);

        DistributoreAutomatico da1 = new DistributoreAutomatico("avellino", false);
        Rivenditore r1 = new Rivenditore("Tufello");
//        bd.save(r1);
        Tessera tDB = td.searchById(2);
        Biglietteria daDB = bd.searchById(1);

        TitoloDiViaggioDAO tvd = new TitoloDiViaggioDAO(em);
        Biglietto ab1 = new Biglietto(LocalDate.now(), daDB, false);
//        tvd.save(ab1);

//        LocalDate creazioneAbbonamento1 = LocalDate.of(2023 , 5 , 12);
//        Abbonamento abbonamento1 = new Abbonamento(creazioneAbbonamento1 , daDB , TipoAbbonamentoEnum.MENSILE , tDB);

//        td.rinnovoTessera(2);

//        tvd.numeroBigliettiVenduti(LocalDate.of(2024, 6, 11), LocalDate.of(2024, 6, 13));

//        tvd.titoliViaggioPerPuntoEmissione(LocalDate.of(2024, 6, 11), LocalDate.of(2024, 6, 13), 1);

//        tvd.verificaAbbonamentoDallaTessera(2);
//        bd.creaBiglietto(4);

        MezziDAO md = new MezziDAO(em);
//        Mezzi tram = md.searchById(2);
//        Mezzi atac = md.searchById(1);
//
        List<Mezzi> mezziRoma = new ArrayList<>();
//        mezziRoma.add(tram);
//        mezziRoma.add(atac);
//
        ManutenzioneDAO manuDao = new ManutenzioneDAO(em);
        Manutenzione manu1 = new Manutenzione(LocalDate.of(2023, 9, 12), LocalDate.of(2023, 10, 25), mezziRoma);
//        manuDao.save(manu1);

//        manuDao.periodoManutenzione(1).forEach(System.out::println);

//        md.timbratura(2, 1);


        em.close();
        emf.close();


    }
}