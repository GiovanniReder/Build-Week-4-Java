package giovanni.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Tratta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String partenza;

    private String capolinea;


    private double tempoMedioPercorrenza;

    @OneToMany(mappedBy = "tratta")
    private List<TrattaMezzi> percorsi;


    public Tratta() {
    }


    public Tratta(String capolinea, String partenza, double tempoMedioPercorrenza) {
        this.capolinea = capolinea;
        this.partenza = partenza;
        this.tempoMedioPercorrenza = tempoMedioPercorrenza;

    }

    public long getId() {
        return id;
    }


    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public double getTempoMedioPercorrenza() {
        return tempoMedioPercorrenza;
    }

    public void setTempoMedioPercorrenza(double tempoMedioPercorrenza) {
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
