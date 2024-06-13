package giovanni;

import giovanni.DAO.BiglietteriaDAO;
import giovanni.DAO.TesseraDAO;
import giovanni.DAO.TitoloDiViaggioDAO;
import giovanni.entities.*;
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


     //  UtentiDAO ud = new UtentiDAO(em);
    //    Utenti u1 = new Utenti("LUIGI", "ROSSI");
     //   ud.save(u1);

     //   Utenti uFDB = ud.searchById(1);
//
      //  TesseraDAO td = new TesseraDAO(em);
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
       tvd.save(ab1);

       Biglietto biglietto1 = new Biglietto(LocalDate.now(), daDB , false  );


//        tvd.numeroBigliettiVenduti(LocalDate.of(2024, 6, 11), LocalDate.of(2024, 6, 13));

//        tvd.titoliViaggioPerPuntoEmissione(LocalDate.of(2024, 6, 11), LocalDate.of(2024, 6, 13), 1);

//        tvd.verificaAbbonamentoDallaTessera(2);
//        bd.creaBiglietto(4);
        em.close();
        emf.close();


    }
}