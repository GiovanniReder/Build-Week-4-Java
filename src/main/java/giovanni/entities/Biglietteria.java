package giovanni.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Biglietteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idRivenditore;

    @OneToMany(mappedBy = "emessoDa")
    protected List<TitoloDiViaggio> bigliettiVenduti;

    private String luogo;

    public Biglietteria() {}

    public Biglietteria(String luogo) {
        this.luogo = luogo;
    }

    public long getId() {
        return idRivenditore;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public List<TitoloDiViaggio> getBigliettiVenduti() {
        return bigliettiVenduti;
    }

    public void setBigliettiVenduti(List<TitoloDiViaggio> bigliettiVenduti) {
        this.bigliettiVenduti = bigliettiVenduti;
    }

    @Override
    public String toString() {
        return "Biglietteria{" +
                "id=" + idRivenditore +
                ", luogo='" + luogo + '\'' +
                '}';
    }
}
