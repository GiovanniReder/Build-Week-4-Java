package giovanni.entities;

import giovanni.enums.StatoMezzoEnum;
import giovanni.enums.TipoMezzoEnum;
import jakarta.persistence.*;

import java.util.List;

// @Entity
public class Mezzi {
  //   @Id
  //   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer capienza;
// @Enumerated(EnumType.STRING)
    private StatoMezzoEnum stato ;


// @Enumerated(EnumType.STRING)
    private TipoMezzoEnum tipo;

// @ManyToOne
private double durataTratta;

// COLLEGATO A BIGLIETTO
// @ManyToOne
private List<Biglietto> idBigliettiTimbrato;

public Mezzi(){}

    public Mezzi(Integer capienza, StatoMezzoEnum stato, TipoMezzoEnum tipo, double durataTratta) {
        this.capienza = capienza;
        this.stato = stato;
        this.tipo = tipo;
        this.durataTratta = durataTratta;

    }

    @Override
    public String toString() {
        return "Mezzi{" +
                "id=" + id +
                ", capienza=" + capienza +
                ", stato=" + stato +
                ", tipo=" + tipo +
                ", durataTratta=" + durataTratta +
                ", idBigliettiTimbrato=" + idBigliettiTimbrato +
                '}';
    }
}
