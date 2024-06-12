package giovanni.supplier;

import com.github.javafaker.Faker;
import giovanni.DAO.*;
import giovanni.entities.DistributoreAutomatico;
import giovanni.entities.Mezzi;
import giovanni.entities.Utenti;
import giovanni.enums.TipoMezzoEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Suppliers {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trasporto_pubblico");
    EntityManager em = emf.createEntityManager();
    BiglietteriaDAO biglietteriaDAO = new BiglietteriaDAO(em);
    ManutenzioneDAO manutenzioneDAO = new ManutenzioneDAO(em);
    UtentiDAO utentiDAO = new UtentiDAO(em);
    TesseraDAO tesseraDAO = new TesseraDAO(em);
    MezziDAO mezziDAO = new MezziDAO(em);
    TitoloDiViaggioDAO titoloDiViaggioDAO = new TitoloDiViaggioDAO(em);
    Faker faker = new Faker();

    public void creaBiglietti() {
        for (int i = 0; i < 50; i++) {
            DistributoreAutomatico distributore = new DistributoreAutomatico(faker.address().cityName(), true);
            biglietteriaDAO.save(distributore);
        }
    }

    ;

    public void creaBiglietti2() {
        for (int i = 0; i < 50; i++) {
            DistributoreAutomatico distributore = new DistributoreAutomatico(faker.address().cityName(), false);
            biglietteriaDAO.save(distributore);
        }
    }

    ;

    public void creaUtenti() {
        for (int i = 0; i < 100; i++) {
            Utenti utenti = new Utenti(faker.name().firstName(), faker.name().lastName());
            utentiDAO.save(utenti);
        }
    }

    ;

    public void creaTessera() {
        for (int i = 0; i < 10; i++) {

//            Tessera tessera = new Tessera(LocalDate.of(2023, 5, 23));
//            tesseraDAO.save(tessera);
//
        }
    }

    ;

    public void creaMezzi() {
        for (int i = 0; i < 50; i++) {
            Mezzi mezzi = new Mezzi(TipoMezzoEnum.BUS, faker.number().randomDouble(2, 20, 120));
            mezziDAO.save(mezzi);
        }
    }

    ;

    public void creaMezzi2() {
        for (int i = 0; i < 50; i++) {
            Mezzi mezzi = new Mezzi(TipoMezzoEnum.TRAM, faker.number().randomDouble(2, 20, 120));
            mezziDAO.save(mezzi);
        }
    }

    ;

}
