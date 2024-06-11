package giovanni.DAO;

import giovanni.entities.Tessera;
import giovanni.entities.TitoloDiViaggio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
                System.out.println("Tessera con ID "+ id + " eliminata ");
            } else System.out.println("Tessera non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
