package giovanni;

import giovanni.DAO.BiglietteriaDAO;
import giovanni.DAO.TesseraDAO;
import giovanni.DAO.TitoloDiViaggioDAO;
import giovanni.entities.Biglietteria;
import giovanni.entities.Biglietto;
import giovanni.entities.DistributoreAutomatico;
import giovanni.entities.Tessera;
import giovanni.supplier.Suppliers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

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

        DistributoreAutomatico da1 = new DistributoreAutomatico("Roma", true);
//        bd.save(da1);
        Tessera tDB = td.searchById(2);
        Biglietteria daDB = bd.searchById(1);

        TitoloDiViaggioDAO tvd = new TitoloDiViaggioDAO(em);
        Biglietto ab1 = new Biglietto(LocalDate.now(), daDB, false);
//        tvd.save(ab1);

//        LocalDate creazioneAbbonamento1 = LocalDate.of(2023 , 5 , 12);
//        Abbonamento abbonamento1 = new Abbonamento(creazioneAbbonamento1 , daDB , TipoAbbonamentoEnum.MENSILE , tDB);

//        td.rinnovoTessera(2);

        tvd.numeroBigliettiVenduti(LocalDate.of(2024, 6, 11), LocalDate.of(2024, 6, 13));
        em.close();
        emf.close();


    }
}