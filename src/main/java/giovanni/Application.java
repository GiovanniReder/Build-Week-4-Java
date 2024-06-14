package giovanni;

import giovanni.entities.PannelloCarmen;
import giovanni.entities.PannelloControllo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {


    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trasporto_pubblico");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
//        Suppliers suppliers = new Suppliers();
//        Biglietto biglietto = new Biglietto();
//        Abbonamento abbonamento = new Abbonamento();
//        suppliers.creaTitoliDiViaggio(biglietto);
//        suppliers.creaTitoliDiViaggio(abbonamento);

        try {
            PannelloCarmen.startApp();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            em.close();
            emf.close();

        }
    }
}