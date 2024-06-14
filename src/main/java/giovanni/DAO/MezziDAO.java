package giovanni.DAO;

import giovanni.entities.Biglietto;
import giovanni.entities.Mezzi;
import giovanni.entities.TitoloDiViaggio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

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


    public List<Mezzi> listaMezzi() {
        TypedQuery<Mezzi> query = entityManager.createQuery("Select m From Mezzi m", Mezzi.class);
        return query.getResultList();
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
        } else System.out.println("Il biglietto risulta già validato ");

    }

    public void bigliettiVidimati(LocalDate date1, LocalDate date2) {


        Query query = entityManager.createQuery("SELECT COUNT(t)  FROM Biglietto t WHERE   t.validato = true AND t.dataValidazione BETWEEN :date1 AND :date2  ");
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);


        System.out.println("dal " + date1 + " al " + date2 + " sono stati vidimati: " + query.getSingleResult() + " biglietti");

    }


    public void bigliettiVidimatiPerMezzo(LocalDate date1, LocalDate date2, long idMezzo) {

        Mezzi found = searchById(idMezzo);


        Query query = entityManager.createQuery("SELECT COUNT(t)  FROM Biglietto t WHERE  t.mezzo = :idMezzo AND t.validato = true AND t.dataValidazione BETWEEN :date1 AND :date2  ");
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        query.setParameter("idMezzo", found);


        System.out.println("Nel mezzo con id " + found.getId() + " sono stati validati: " + query.getSingleResult() + " biglietti");

    }


}



