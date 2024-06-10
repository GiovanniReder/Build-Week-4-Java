package giovanni.entities;

import jakarta.persistence.Entity;

@Entity
public class Rivenditore extends Biglietteria {

    public Rivenditore() {}

    public Rivenditore(String luogo) {
        super(luogo);
    }

    @Override
    public String toString() {
        return "Rivenditore{" +
                "luogo='" + getLuogo() + '\'' +
                '}';
    }
}
