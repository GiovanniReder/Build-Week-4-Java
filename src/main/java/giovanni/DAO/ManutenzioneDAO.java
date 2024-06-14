package giovanni.DAO;

import giovanni.entities.Manutenzione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ManutenzioneDAO {
    private EntityManager entityManager;

    public ManutenzioneDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void save(Manutenzione manutenzione) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist(manutenzione);
            trans.commit();
            System.out.println("Manutenzione: " + manutenzione.getId() + " creata con successo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Manutenzione searchById(long id) {
        return entityManager.find(Manutenzione.class, id);
    }

    public void delete(long id) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            Manutenzione found = searchById(id);
            if (found != null) {
                trans.begin();
                entityManager.remove(found);
                trans.commit();
                System.out.println("Manutenzione con ID " + id + " eliminata ");
            } else System.out.println("Elemento non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public List<Manutenzione> periodoManutenzione(long idManutenzione) {
        Manutenzione found = searchById(idManutenzione);

        TypedQuery<Manutenzione> query = entityManager.createQuery("SELECT mt FROM Mezzi m JOIN m.manutenzione mt WHERE m.id = :idMezzo", Manutenzione.class);
        query.setParameter("idMezzo", found.getId());

        return query.getResultList();

    }
}
