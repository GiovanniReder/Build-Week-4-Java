package giovanni.DAO;

import giovanni.entities.Mezzi;
import giovanni.entities.Tratta;
import giovanni.entities.TrattaMezzi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TrattaMezziDAO {

    private EntityManager entityManager;


    public TrattaMezziDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(TrattaMezzi trattaMezzi) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist(trattaMezzi);
            trans.commit();
            System.out.println("il percorso: " + trattaMezzi.getId() + " è stata creata con successo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public TrattaMezzi searchById(long id) {
        return entityManager.find(TrattaMezzi.class, id);
    }

    public void delete(long id) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            TrattaMezzi found = searchById(id);
            if (found != null) {
                trans.begin();
                entityManager.remove(found);
                trans.commit();
                System.out.println("il percorso con ID " + id + " è stata eliminata ");
            } else System.out.println("Elemento non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<TrattaMezzi> listaTratteMezzi() {
        TypedQuery<TrattaMezzi> query = entityManager.createQuery("select b from TrattaMezzi b", TrattaMezzi.class);
        return query.getResultList();
    }

    public void trattaPercorsa(long idMezzo, long idTratta) {


        MezziDAO md = new MezziDAO(entityManager);
        Mezzi mezzo = md.searchById(idMezzo);

        TrattaDAO td = new TrattaDAO(entityManager);
        Tratta tratta = td.searchById(idTratta);

        Query query = entityManager.createQuery("SELECT COUNT(tm) FROM TrattaMezzi tm WHERE tm.mezzi = :idMezzo AND tm.tratta = :idTratta");
        query.setParameter("idMezzo", mezzo);
        query.setParameter("idTratta", tratta);

        Long result = (Long) query.getSingleResult();

        Query query1 = entityManager.createQuery("SELECT tm.TempoEffettivo FROM TrattaMezzi tm WHERE  tm.mezzi = :idMezzo AND tm.tratta = :idTratta");
        query1.setParameter("idMezzo", mezzo);
        query1.setParameter("idTratta", tratta);
        System.out.println("Il mezzo con id " + mezzo.getId() + " ha percorso la tratta da " + tratta.getPartenza() + " a " + tratta.getCapolinea() + "  in " + query1.getSingleResult() + " minuti");


    }
}
