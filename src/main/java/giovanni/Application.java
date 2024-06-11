package giovanni;

import giovanni.DAO.BiglietteriaDAO;
import giovanni.DAO.ManutenzioneDAO;
import giovanni.DAO.MezziDAO;
import giovanni.DAO.TitoloDiViaggioDAO;
import giovanni.entities.*;
import giovanni.enums.TipoMezzoEnum;
import giovanni.supplier.Suppliers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Application {


    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trasporto_pubblico");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        System.out.println("Hello World!");
      //  Suppliers suppliers = new Suppliers();
      //  suppliers.creaBiglietti2();
      //  suppliers.creaMezzi2();
      //  suppliers.creaUtenti(); NON FUNZIONA!!



    }
}