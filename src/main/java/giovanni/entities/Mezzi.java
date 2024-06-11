package giovanni.entities;

import giovanni.enums.StatoMezzoEnum;
import giovanni.enums.TipoMezzoEnum;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Mezzi  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer capienza;


    @Enumerated(EnumType.STRING)
    private TipoMezzoEnum tipo;

    private double durataTratta;

    @OneToMany(mappedBy = "mezzo")
    private List<Biglietto> bigliettiTimbrati;

    @ManyToOne
    @JoinColumn(name = "tratta_percorsa")
    private Tratta tratta;

    @ManyToMany(mappedBy = "mezzi")
    private List<Manutenzione> manutenzione;


    public Mezzi() {}

    public Mezzi( TipoMezzoEnum tipo, double durataTratta) {
        this.tipo = tipo;
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
                ", tipo=" + tipo +
                ", durataTratta=" + durataTratta +
                '}';
    }
}






