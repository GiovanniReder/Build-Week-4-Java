package giovanni.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;


 @Entity
 @Inheritance(strategy=InheritanceType.JOINED)
public abstract class Biglietteria {
 @Id
  @GeneratedValue



 protected long idRivenditore;

 @OneToMany(mappedBy = "emessoDa")
 protected List<DocumentoDiViaggio> bigliettiVenduti;

    private String luogo;

    public Biglietteria(){}

    public Biglietteria(String luogo) {
        this.luogo = luogo;
    }

    public long getId() {
        return idRivenditore;
    }

    @Override
    public String toString() {
        return "Biglietteria{" +
                "id=" + idRivenditore +
                '}';
    }
}
