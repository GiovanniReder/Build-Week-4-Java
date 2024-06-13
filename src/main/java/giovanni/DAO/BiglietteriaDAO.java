package giovanni.DAO;

import giovanni.entities.*;
import giovanni.enums.TipoAbbonamentoEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;

public class BiglietteriaDAO {
    private EntityManager entityManager;

    public BiglietteriaDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void save(Biglietteria biglietteria) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist(biglietteria);
            trans.commit();
            System.out.println("Biglietteria: " + biglietteria.getId() + " creata con successo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Biglietteria searchById(long id) {
        return entityManager.find(Biglietteria.class, id);
    }

    public void delete(long id) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            Biglietteria found = searchById(id);
            if (found != null) {
                trans.begin();
                entityManager.remove(found);
                trans.commit();
                System.out.println("Biglietteria con ID " + id + " eliminata ");
            } else System.out.println("Elemento non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void creaBiglietto(long idBiglietteria) {
        Biglietteria biglietteria = searchById(idBiglietteria);

        if (biglietteria instanceof DistributoreAutomatico && ((DistributoreAutomatico) biglietteria).getInFunzione()) {
            TitoloDiViaggioDAO td = new TitoloDiViaggioDAO(entityManager);
            Biglietto biglietto = new Biglietto(LocalDate.now(), biglietteria, false);
            td.save(biglietto);
        } else System.out.println("Attenzione il distributore è fuori servizio");


    }

    public void creaAbbonamento(long idBiglietteria, TipoAbbonamentoEnum tipoAbbonamento, long idTessera) {
        Biglietteria biglietteria = searchById(idBiglietteria);
        TesseraDAO tesseraDao = new TesseraDAO(entityManager);
        Tessera tessera = tesseraDao.searchById(idTessera);

        if (biglietteria instanceof DistributoreAutomatico && ((DistributoreAutomatico) biglietteria).getInFunzione()) {
            TitoloDiViaggioDAO td = new TitoloDiViaggioDAO(entityManager);
            Abbonamento abbonamento = new Abbonamento(LocalDate.now(), biglietteria, tipoAbbonamento, tessera);
            td.save(abbonamento);
        } else System.out.println("Attenzione il distributore è fuori servizio");


    }

}
