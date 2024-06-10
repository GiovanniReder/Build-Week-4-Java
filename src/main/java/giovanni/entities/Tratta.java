package giovanni.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class Tratta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String partenza;

    private String capoline;

 private List<Mezzi> mezzoChePassa;


  private double tempoMedioPercorrenza;

  public Tratta(){}

    public Tratta(String partenza, String capoline, List<Mezzi> mezzoChePassa, double tempoMedioPercorrenza) {
        this.partenza = partenza;
        this.capoline = capoline;
        this.mezzoChePassa = mezzoChePassa;
        this.tempoMedioPercorrenza = tempoMedioPercorrenza;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", partenza='" + partenza + '\'' +
                ", capoline='" + capoline + '\'' +
                ", mezzoChePassa=" + mezzoChePassa +
                ", tempoMedioPercorrenza=" + tempoMedioPercorrenza +
                '}';
    }
}
