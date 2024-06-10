package giovanni.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rivenditore extends Biglietteria {


    public Rivenditore() {
    }

    public Rivenditore(String luogo) {
        super(luogo);
    }


    @Override
    public String toString() {
        return "Rivenditore{}";
    }
}
