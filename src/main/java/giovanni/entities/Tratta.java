package giovanni.entities;

import jakarta.persistence.*;

import java.util.List;

// @Entity
public class Tratta {
   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String partenza;

    private String capolinea;

 private List<Mezzi> mezzoChePassa;


  private double tempoMedioPercorrenza;

    @OneToMany(mappedBy = "tratta")
    private List<Mezzi> mezzi;

  public Tratta(){}

    public Tratta(String partenza, String capolinea, double tempoMedioPercorrenza) {
        this.partenza = partenza;
        this.capolinea = capolinea;
        this.tempoMedioPercorrenza = tempoMedioPercorrenza;
    }


    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", partenza='" + partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoMedioPercorrenza=" + tempoMedioPercorrenza +
                '}';
    }
}
