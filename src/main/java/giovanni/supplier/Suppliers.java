package giovanni.supplier;

import com.github.javafaker.Faker;
import giovanni.DAO.*;
import giovanni.entities.*;
import giovanni.enums.TipoAbbonamentoEnum;
import giovanni.enums.TipoMezzoEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Suppliers {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trasporto_pubblico");
    EntityManager em = emf.createEntityManager();
    BiglietteriaDAO biglietteriaDAO = new BiglietteriaDAO(em);
    ManutenzioneDAO manutenzioneDAO = new ManutenzioneDAO(em);
    UtentiDAO utentiDAO = new UtentiDAO(em);
    TesseraDAO tesseraDAO = new TesseraDAO(em);
    TrattaDAO trattaDAO = new TrattaDAO(em);
    MezziDAO mezziDAO = new MezziDAO(em);
    TitoloDiViaggioDAO titoloDiViaggioDAO = new TitoloDiViaggioDAO(em);
    Faker faker = new Faker();


    private LocalDate getRandomDate() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(2022, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2023, 12, 31).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private LocalDate getRandomEndDate(LocalDate startDate) {
        Random random = new Random();
        int minDay = (int) startDate.toEpochDay();
        int maxDay = (int) LocalDate.of(2023, 12, 31).toEpochDay();
        long randomDay = minDay + 1 + random.nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public void creaRivenditore(Biglietteria tipoBiglietteria) {
        for (int i = 0; i < 10; i++) {
            if (tipoBiglietteria instanceof DistributoreAutomatico) {
                DistributoreAutomatico distributore = new DistributoreAutomatico(faker.address().cityName(), true);
                biglietteriaDAO.save(distributore);
            } else if (tipoBiglietteria instanceof Rivenditore) {
                Rivenditore rivenditore = new Rivenditore(faker.address().cityName());
                biglietteriaDAO.save(rivenditore);
            } else {
                System.out.println("Tipo di biglietteria non riconosciuto.");
            }
        }
    }

    public void creaUtenti() {
        for (int i = 0; i < 100; i++) {
            Utenti utenti = new Utenti(faker.name().firstName(), faker.name().lastName());
            utentiDAO.save(utenti);
        }
    }

    public void creaTessera() {
        for (int i = 0; i < 10; i++) {
            Random rmn = new Random();
            Utenti utente = utentiDAO.searchById(rmn.nextInt(1, 100));
            if (!tesseraDAO.verificaUtenteRegistrati(utente)) {
                Tessera nuovaTessera = new Tessera(getRandomDate(), utente);
                tesseraDAO.save(nuovaTessera);
                System.out.println("Tessera creata per l'utente " + utente.getId());
            } else {
                System.out.println("L'utente  ha già una tessera.");
            }


        }
    }

    public void creaMezzi(TipoMezzoEnum tipoMezzoEnum) {
        for (int i = 0; i < 20; i++) {
            Mezzi mezzi = new Mezzi(tipoMezzoEnum);
            mezziDAO.save(mezzi);
        }
    }

    public void creaTitoliDiViaggio(TitoloDiViaggio titoloDiViaggio) {
        for (int i = 0; i < 10; i++) {
            Random rmn = new Random();
            Biglietteria emessoDa = biglietteriaDAO.searchById(rmn.nextInt(1, 100));
            if (!(emessoDa == null)) {
                Tessera tessera = tesseraDAO.searchById(rmn.nextInt(1, 10));
                if (titoloDiViaggio instanceof Biglietto) {
                    Biglietto biglietto = new Biglietto(getRandomDate(), emessoDa);
                    titoloDiViaggioDAO.save(biglietto);
                } else if (titoloDiViaggio instanceof Abbonamento) {

                    Abbonamento abbonamento = new Abbonamento(getRandomDate(), emessoDa, TipoAbbonamentoEnum.MENSILE, tessera);
                    titoloDiViaggioDAO.save(abbonamento);
                } else {
                    System.out.println("Tipo di titolo non riconosciuto.");
                }
            }
        }
    }

    public List<Mezzi> creaListaMezzi() {
        List<Mezzi> mezziList = new ArrayList<>();
        Random rmn = new Random();
        for (int i = 0; i < 10; i++) {
            int id = rmn.nextInt(1, 100);
            Mezzi mezziLista = mezziDAO.searchById(id);
            if (mezziLista != null) {
                mezziList.add(mezziLista);
            }
        }
        return mezziList;
    }

    public void creaManutenzione() {
        Random rmn = new Random();
        List<Mezzi> mezziList = creaListaMezzi();
        for (int i = 0; i < 10; i++) {
            LocalDate startDate = getRandomDate();
            LocalDate endDate = getRandomEndDate(startDate);
            Manutenzione manutenzione = new Manutenzione(startDate, endDate, mezziList);
            manutenzioneDAO.save(manutenzione);
        }
    }

    public void creaTratte() {
        Random rmn = new Random();
        double min = 0.0;
        double max = 6.0;
        for (int i = 0; i < 10; i++) {
            double randomDouble = min + (max - min) * rmn.nextDouble();
            randomDouble = Math.floor(randomDouble * 100) / 100.0;
            int randomInt = (int) randomDouble;
            double doubleDec = randomDouble - randomInt;
            doubleDec = Math.min(doubleDec, 0.60);
            randomDouble = randomInt + doubleDec;
            Tratta tratta = new Tratta(faker.address().cityName(), faker.address().cityName(), randomDouble);
            trattaDAO.save(tratta);
        }
    }
}

