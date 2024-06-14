package giovanni.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("rivenditore")
public class Rivenditore extends Biglietteria {

    public Rivenditore() {
    }

    public Rivenditore(String luogo) {
        super(luogo);
    }

    @Override
    public String toString() {
        return "Rivenditore{ " + super.toString();
    }
}
