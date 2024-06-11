package giovanni;

import giovanni.DAO.BiglietteriaDAO;
import giovanni.DAO.ManutenzioneDAO;
import giovanni.DAO.MezziDAO;
import giovanni.DAO.TitoloDiViaggioDAO;
import giovanni.entities.Biglietteria;
import giovanni.entities.DistributoreAutomatico;
import giovanni.entities.Manutenzione;
import giovanni.entities.Mezzi;
import giovanni.enums.TipoMezzoEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Locale;

public class Application {


    private static final EntityManagerFactory emf= Persistence.createEntityManagerFactory("Trasporto_pubblico");
    public static void main(String[] args) {
        System.out.println("Hello World!");

        EntityManager em = emf.createEntityManager();
       // Faker faker = new Faker(Locale.ITALY);
        BiglietteriaDAO biglietteriaDAO = new BiglietteriaDAO(em);
        ManutenzioneDAO manutenzioneDAO = new ManutenzioneDAO(em);
        MezziDAO mezziDAO = new MezziDAO(em);
        TitoloDiViaggioDAO titoloDiViaggioDAO = new TitoloDiViaggioDAO(em);

        DistributoreAutomatico distributore = new DistributoreAutomatico("MIlano",true);
       // Manutenzione manutenzione = new Manutenzione()
        Mezzi mezzo1 = new Mezzi( TipoMezzoEnum.BUS,10);
       // mezziDAO.save(mezzo1);
       // biglietteriaDAO.save(distributore);
        System.out.println(mezziDAO.searchById(2));

        //biglietteriaDAO.delete(1);
    }



}
