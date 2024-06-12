package giovanni.DAO;

import giovanni.entities.TitoloDiViaggio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TitoloDiViaggioDAO {
    private EntityManager entityManager;

    public TitoloDiViaggioDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void save(TitoloDiViaggio titoloDiViaggio) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist(titoloDiViaggio);
            trans.commit();
            System.out.println("Titolo di viaggio: " + titoloDiViaggio.getId() + " creato con successo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public TitoloDiViaggio searchById(long id) {
        return entityManager.find(TitoloDiViaggio.class, id);
    }

    public void delete(long id) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            TitoloDiViaggio found = searchById(id);
            if (found != null) {
                trans.begin();
                entityManager.remove(found);
                trans.commit();
                System.out.println("Titolo di viaggio con ID " + id + " eliminato ");
            } else System.out.println("Elemento non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
