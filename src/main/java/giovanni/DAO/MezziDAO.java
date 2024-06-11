package giovanni.DAO;

import giovanni.entities.Manutenzione;
import giovanni.entities.Mezzi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MezziDAO {
    private EntityManager entityManager;

    public MezziDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void save(Mezzi mezzi) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist(mezzi);
            trans.commit();
            System.out.println("Mezzi: " + mezzi.getId() + " creata con successo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Mezzi searchById(long id) {
        return entityManager.find(Mezzi.class, id);
    }

    public void delete(long id) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            Mezzi found = searchById(id);
            if (found != null) {
                trans.begin();
                entityManager.remove(found);
                trans.commit();
                System.out.println("Mezzo con ID "+ id + " eliminato ");
            } else System.out.println("Elemento non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
