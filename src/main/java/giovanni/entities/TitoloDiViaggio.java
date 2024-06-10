package giovanni.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="titolo_di_viaggio")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TitoloDiViaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @ManyToOne
    @JoinColumn(name = "biglietteria_id")
    protected Biglietteria emessoDa;

    protected LocalDate dataEmissione;

    public TitoloDiViaggio() {}

    public TitoloDiViaggio(LocalDate dataEmissione, Biglietteria emessoDa) {
        this.dataEmissione = dataEmissione;
        this.emessoDa = emessoDa;
    }

    public long getId() {
        return id;
    }

    public Biglietteria getEmessoDa() {
        return emessoDa;
    }

    public void setEmessoDa(Biglietteria emessoDa) {
        this.emessoDa = emessoDa;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    @Override
    public String toString() {
        return "TitoloDiViaggio{" +
                "id=" + id +
                ", emessoDa=" + emessoDa +
                ", dataEmissione=" + dataEmissione +
                '}';
    }
}

