package giovanni.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
   @Table
   public class Tessera {
   @Id

    private long numeroTessera;

    private LocalDate emessa;

    private LocalDate scadenza;

    @OneToOne(mappedBy = "tessera")
    private Utenti utente;

    @OneToMany(mappedBy = "tessera")
    private List<Abbonamento> abbonamenti;


    public Tessera (){}


       public Tessera(LocalDate emessa, LocalDate scadenza) {
           this.emessa = emessa;
           this.scadenza = scadenza;
       }

    public long getNumTessera() {
        return numeroTessera;
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
                "numeroTessera=" + numeroTessera +
                ", emessa=" + emessa +
                ", scadenza=" + scadenza +
                '}';
    }
}
