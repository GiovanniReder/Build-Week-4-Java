package giovanni.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

 @Entity
 @Table(name="biglietti e abbonamenti")
 @Inheritance(strategy= InheritanceType.JOINED)

public abstract class DocumentoDiViaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;


    @ManyToOne
    protected Biglietteria emessoDa;

    protected LocalDate dataEmissione;

    public DocumentoDiViaggio(){}

    public DocumentoDiViaggio(LocalDate dataEmissione, Biglietteria emessoDa) {
        this.dataEmissione = dataEmissione;
        this.emessoDa = emessoDa;
    }

    @Override
    public String toString() {
        return "DocumentoDiViaggio{" +
                "id=" + id +
                ", emessoDa=" + emessoDa +
                ", dataEmissione=" + dataEmissione +
                '}';
    }
}
