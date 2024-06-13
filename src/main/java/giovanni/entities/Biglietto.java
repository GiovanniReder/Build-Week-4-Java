package giovanni.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("biglietto")
public class Biglietto extends TitoloDiViaggio {

    // COLLEGATO AI MEZZI
    //  @OneToOne(mappedBy = "idBigliettiTimbrato")
    private Boolean validato;

    private LocalDate dataValidazione;

    // DA COLLEGARE CON LA CLASSE MEZZI


    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzi mezzo;


    public Biglietto() {
    }

    public Biglietto(LocalDate dataEmissione, Biglietteria emessoDa, Boolean validato) {
        super(dataEmissione, emessoDa);
        this.validato = validato;
    }

    public Boolean getValidato() {
        return validato;
    }

    public void setValidato(Boolean validato) {
        this.validato = validato;
        System.out.println("Il biglietto è stato validato");
    }


    public LocalDate getDataValidazione() {
        return dataValidazione;
    }

    public void setDataValidazione(LocalDate dataValidazione) {
        this.dataValidazione = dataValidazione;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "validato=" + validato +
                ", dataValidazione=" + dataValidazione +
                ", mezzo=" + mezzo +
                '}';
    }
}
