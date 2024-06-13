package giovanni.DAO;

import giovanni.entities.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.time.LocalDate;

public class TesseraDAO {
    private EntityManager entityManager;

    public TesseraDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void save(Tessera tessera) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist(tessera);
            trans.commit();
            System.out.println("Tessera: " + tessera.getNumTessera() + " creata con successo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Tessera searchById(long id) {
        return entityManager.find(Tessera.class, id);
    }

    public void delete(long id) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            Tessera found = searchById(id);
            if (found != null) {
                trans.begin();
                entityManager.remove(found);
                trans.commit();
                System.out.println("Tessera con ID " + id + " eliminata ");
            } else System.out.println("Tessera non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void rinnovoTessera(long tessera_id) {
        EntityTransaction trans = entityManager.getTransaction();
        Tessera found = searchById(tessera_id);
        if (found.getScadenza().isBefore(LocalDate.now())) {
            trans.begin();
            Query query = entityManager.createQuery("UPDATE Tessera abb SET abb.scadenza =  :newDate WHERE abb.id = :id");
            query.setParameter("newDate", LocalDate.now().plusYears(1));
            query.setParameter("id", tessera_id);
            entityManager.persist(found);
            query.executeUpdate();
            trans.commit();
            System.out.println("la tua tessera è stata rinnovata");
        } else System.out.println("la tua tessera non è da rinnovare");


    }


}
