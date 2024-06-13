package giovanni.entities;

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
    private TipoMezzoEnum tipo;


    @OneToMany(mappedBy = "mezzo")
    private List<Biglietto> bigliettiTimbrati;

    @OneToMany(mappedBy = "mezzi")
    private List<TrattaMezzi> percorsi;

    @ManyToMany(mappedBy = "mezzi")
    private List<Manutenzione> manutenzione;


    public Mezzi() {
    }

    public Mezzi(TipoMezzoEnum tipo) {
        this.tipo = tipo;
        if (tipo == TipoMezzoEnum.BUS) {
            this.capienza = 50;
        } else if (tipo == TipoMezzoEnum.TRAM) {
            this.capienza = 80;
        }

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


    public TipoMezzoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoMezzoEnum tipo) {
        this.tipo = tipo;
        this.capienza = tipo == TipoMezzoEnum.BUS ? 50 : 80;
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
                ", tipo=" + tipo +
                '}';
    }
}






