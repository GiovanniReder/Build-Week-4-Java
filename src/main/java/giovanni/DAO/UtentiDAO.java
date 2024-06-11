package giovanni.DAO;

import giovanni.entities.TitoloDiViaggio;
import giovanni.entities.Utenti;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UtentiDAO {
    private EntityManager entityManager;

    public UtentiDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void save(Utenti utenti) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist(utenti);
            trans.commit();
            System.out.println("Titolo di viaggio: " + utenti.getNome() + " creato con successo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Utenti searchById(long id) {
        return entityManager.find(Utenti.class, id);
    }

    public void delete(long id) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            Utenti found = searchById(id);
            if (found != null) {
                trans.begin();
                entityManager.remove(found);
                trans.commit();
                System.out.println("Utente con ID "+ id + " eliminato ");
            } else System.out.println("Utente non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
