package giovanni.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tratta_mezzi")
public class TrattaMezzi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "tratta_id")
    private Tratta tratta;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzi mezzi;

    @Column(name = "tempo_effettivo_percorrenza")
    private double TempoEffettivo;

    public TrattaMezzi() {

    }

    public TrattaMezzi(Tratta tratta, Mezzi mezzi, double tempoEffettivo) {
        this.tratta = tratta;
        this.mezzi = mezzi;
        TempoEffettivo = tempoEffettivo;
    }

    public long getId() {
        return id;
    }


    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public Mezzi getMezzi() {
        return mezzi;
    }

    public void setMezzi(Mezzi mezzi) {
        this.mezzi = mezzi;
    }

    public double getTempoEffettivo() {
        return TempoEffettivo;
    }

    public void setTempoEffettivo(double tempoEffettivo) {
        TempoEffettivo = tempoEffettivo;
    }

    @Override
    public String toString() {
        return "TrattaMezzi{" +
                "id=" + id +
                ", tratta=" + tratta +
                ", mezzi=" + mezzi +
                ", TempoEffettivo=" + TempoEffettivo +
                '}';
    }
}
