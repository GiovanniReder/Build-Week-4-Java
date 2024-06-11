package giovanni.entities;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
public class Manutenzione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dataInizioManutenzione;
    private LocalDate dataFineManutenzione;

    @ManyToMany
    @JoinTable(name = "mezzi_in_manutenzione", joinColumns = @JoinColumn(name = "manutenzione_id"),
    inverseJoinColumns = @JoinColumn(name = "mezzo_id"))
    private List<Mezzi> mezzi;

    public Manutenzione(){}

    public Manutenzione(LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione, List<Mezzi> mezzi) {
        this.dataInizioManutenzione = dataInizioManutenzione;
        this.dataFineManutenzione = dataFineManutenzione;
        this.mezzi = mezzi;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDataInizioManutenzione() {
        return dataInizioManutenzione;
    }

    public void setDataInizioManutenzione(LocalDate dataInizioManutenzione) {
        this.dataInizioManutenzione = dataInizioManutenzione;
    }

    public LocalDate getDataFineManutenzione() {
        return dataFineManutenzione;
    }

    public void setDataFineManutenzione(LocalDate dataFineManutenzione) {
        this.dataFineManutenzione = dataFineManutenzione;
    }

    public List<Mezzi> getMezzi() {
        return mezzi;
    }

    public void setMezzi(List<Mezzi> mezzi) {
        this.mezzi = mezzi;
    }

    @Override
    public String toString() {
        return "Manutenzione{" +
                "id=" + id +
                ", dataInizioManutenzione=" + dataInizioManutenzione +
                ", dataFineManutenzione=" + dataFineManutenzione +
                ", mezzi=" + mezzi +
                '}';
    }
}
