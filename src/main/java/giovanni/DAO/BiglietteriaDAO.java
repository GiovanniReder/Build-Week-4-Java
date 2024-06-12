package giovanni.DAO;

import giovanni.entities.Biglietteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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


}
