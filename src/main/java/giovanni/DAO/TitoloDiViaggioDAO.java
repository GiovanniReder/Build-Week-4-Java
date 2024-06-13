package giovanni.DAO;

import giovanni.entities.Biglietteria;
import giovanni.entities.TitoloDiViaggio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.time.LocalDate;

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

    public void numeroBigliettiVenduti(LocalDate date1, LocalDate date2) {


        Query queryBiglietto = entityManager.createQuery("SELECT COUNT(b) FROM Biglietto b WHERE b.dataEmissione BETWEEN :date1 AND :date2");
        queryBiglietto.setParameter("date1", date1);
        queryBiglietto.setParameter("date2", date2);


        Query queryAbb = entityManager.createQuery("SELECT COUNT(b) FROM Abbonamento b WHERE b.dataEmissione BETWEEN :date1 AND :date2");
        queryAbb.setParameter("date1", date1);
        queryAbb.setParameter("date2", date2);


        System.out.println("Il numero venduti  di biglietti è " + queryBiglietto.getSingleResult() + " mentre quello di abbonamenti è " + queryAbb.getSingleResult());


    }

    public void titoliViaggioPerPuntoEmissione(LocalDate date1, LocalDate date2, long idBiglietteria) {

        BiglietteriaDAO bdv = new BiglietteriaDAO(entityManager);
        Biglietteria biglietti = bdv.searchById(idBiglietteria);


        Query query = entityManager.createQuery("SELECT COUNT(t)  FROM Biglietto t WHERE  t.emessoDa = :idBiglietteria AND t.dataEmissione BETWEEN :date1 AND :date2  ");
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        query.setParameter("idBiglietteria", biglietti);
        Query query1 = entityManager.createQuery("SELECT COUNT(t)  FROM Abbonamento t WHERE  t.emessoDa = :idBiglietteria AND t.dataEmissione BETWEEN :date1 AND :date2  ");
        query1.setParameter("date1", date1);
        query1.setParameter("date2", date2);
        query1.setParameter("idBiglietteria", biglietti);


        System.out.println("la biglietteria " + biglietti.getId() + " ha emesso  biglietti: " + query.getSingleResult() + " e  abbonamenti: " + query1.getSingleResult());

    }


}
