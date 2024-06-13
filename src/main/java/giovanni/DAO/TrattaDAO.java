package giovanni.DAO;

import giovanni.entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TrattaDAO {
    private EntityManager entityManager;

    public TrattaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Tratta tratta) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist(tratta);
            trans.commit();
            System.out.println("La tratta: " + tratta.getId() + " è stata creata con successo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Tratta searchById(long id) {
        return entityManager.find(Tratta.class, id);
    }

    public void delete(long id) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            Tratta found = searchById(id);
            if (found != null) {
                trans.begin();
                entityManager.remove(found);
                trans.commit();
                System.out.println("la tratta con ID " + id + " è stata eliminata ");
            } else System.out.println("Elemento non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
