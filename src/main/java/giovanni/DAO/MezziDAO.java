package giovanni.DAO;

import giovanni.entities.Biglietto;
import giovanni.entities.Mezzi;
import giovanni.entities.TitoloDiViaggio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

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
                System.out.println("Mezzo con ID " + id + " eliminato ");
            } else System.out.println("Elemento non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public void timbratura(long idBiglietto, long idMezzo) {
        EntityTransaction trans = entityManager.getTransaction();

        Mezzi found = searchById(idMezzo);

        TitoloDiViaggioDAO td = new TitoloDiViaggioDAO(entityManager);
        TitoloDiViaggio biglietto = td.searchById(idBiglietto);

        if (biglietto instanceof Biglietto && !((Biglietto) biglietto).getValidato()) {
            trans.begin();
            Query query = entityManager.createQuery("UPDATE  Biglietto b SET b.dataValidazione = CURRENT_DATE , b.validato = true ,b.mezzo = :idMezzo WHERE b.id = :idBiglietto");
            query.setParameter("idBiglietto", biglietto.getId());
            query.setParameter("idMezzo", found);
            entityManager.persist(biglietto);
            query.executeUpdate();
            trans.commit();
            System.out.println("BIGLIETTO VALIDATO");
        } else System.out.println("Il biglietto è stato gia validato in data");

    }


}



