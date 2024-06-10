package giovanni.entities;

import jakarta.persistence.*;

import java.util.UUID;

 @Entity

 @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Biglietteria {
  @Id
  @GeneratedValue

    protected long id;

    private String luogo;

    public Biglietteria(){}

    public Biglietteria(String luogo) {
        this.luogo = luogo;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Biglietteria{" +
                "id=" + id +
                '}';
    }
}
