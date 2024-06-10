package giovanni.entities;

import giovanni.enums.StatoMezzoEnum;
import giovanni.enums.TipoMezzoEnum;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Mezzi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer capienza;

    @Enumerated(EnumType.STRING)
    private StatoMezzoEnum stato;

    @Enumerated(EnumType.STRING)
    private TipoMezzoEnum tipo;

    private double durataTratta;

    @OneToMany(mappedBy = "mezzo")
    private List<Biglietto> bigliettiTimbrati;

    public Mezzi() {}

    public Mezzi(Integer capienza, StatoMezzoEnum stato, TipoMezzoEnum tipo, double durataTratta) {
        this.capienza = capienza;
        this.stato = stato;
        if (tipo == TipoMezzoEnum.BUS) {
            this.capienza = 50;
        } else if (tipo == TipoMezzoEnum.TRAM) {
            this.capienza = 80;
        }
        this.durataTratta = durataTratta;
    }

    public long getId() {
        return id;
    }

    public Integer getCapienza() {
        return capienza;
    }

    public void setCapienza(Integer capienza) {
        this.capienza = capienza;
    }

    public StatoMezzoEnum getStato() {
        return stato;
    }

    public void setStato(StatoMezzoEnum stato) {
        this.stato = stato;
    }

    public TipoMezzoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoMezzoEnum tipo) {
        this.tipo = tipo;
        this.capienza = tipo == TipoMezzoEnum.BUS ? 50 : 80;
    }

    public double getDurataTratta() {
        return durataTratta;
    }

    public void setDurataTratta(double durataTratta) {
        this.durataTratta = durataTratta;
    }

    public List<Biglietto> getBigliettiTimbrati() {
        return bigliettiTimbrati;
    }

    public void setBigliettiTimbrati(List<Biglietto> bigliettiTimbrati) {
        this.bigliettiTimbrati = bigliettiTimbrati;
    }

    @Override
    public String toString() {
        return "Mezzi{" +
                "id=" + id +
                ", capienza=" + capienza +
                ", stato=" + stato +
                ", tipo=" + tipo +
                ", durataTratta=" + durataTratta +
                ", bigliettiTimbrati=" + bigliettiTimbrati +
                '}';
    }
}






