package giovanni.entities;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("distributore_automatico")
public class DistributoreAutomatico extends Biglietteria {


    private Boolean inFunzione;

    public DistributoreAutomatico() {}

    public DistributoreAutomatico(String luogo, Boolean inFunzione) {
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
        return "DistributoreAutomatico{" +
                "inFunzione=" + inFunzione +
                ", luogo='" + getLuogo() + '\'' +
                '}';
    }
}
