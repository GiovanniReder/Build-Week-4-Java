package giovanni.entities;

import jakarta.persistence.Entity;

 @Entity
public class Automatico extends Biglietteria {
    private Boolean inFunzione;


    public Automatico(Boolean inFunzione) {
        this.inFunzione = inFunzione;
    }

    public Automatico(String luogo, Boolean inFunzione) {
        super(luogo);
        this.inFunzione = inFunzione;
    }

    public Boolean getInFunzione() {
        return inFunzione;
    }

    public void setInFunzione(Boolean inFunzione) {
        this.inFunzione = inFunzione;
    }

    @Override
    public String toString() {
        return "Automatico{" +
                "inFunzione=" + inFunzione +
                '}';
    }
}
