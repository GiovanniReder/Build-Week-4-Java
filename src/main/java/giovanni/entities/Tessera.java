package giovanni.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class Tessera {
    @Id
    @OneToOne
    private long numTessera;

    private LocalDate emessa;

    private LocalDate scadenza;

    public Tessera (){}

    public Tessera(LocalDate emessa, LocalDate scadenza) {
        this.emessa = emessa;
        this.scadenza = scadenza;
    }

    public long getNumTessera() {
        return numTessera;
    }


    public LocalDate getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDate scadenza) {
        this.scadenza = scadenza;
    }

    public LocalDate getEmessa() {
        return emessa;
    }

    public void setEmessa(LocalDate emessa) {
        this.emessa = emessa;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "numTessera=" + numTessera +
                ", emessa=" + emessa +
                ", scadenza=" + scadenza +
                '}';
    }
}
