package giovanni.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity

public class Biglietto extends Biglietteria{

    // DA COLLEGARE AI MEZZI
    @OneToOne(mappedBy = "idBigliettiTimbrato")
    private Boolean validato;

    private LocalDate dataValidazione;

    // DA COLLEGARE CON LA CLASSE MEZZI
    private String luogoValidazione;

public Biglietto(){}

    public Biglietto(Boolean validato, LocalDate dataValidazione, String luogoValidazione) {
        this.validato = validato;
        this.dataValidazione = dataValidazione;
        this.luogoValidazione = luogoValidazione;
    }

    public Boolean getValidato() {
        return validato;
    }

    public void setValidato(Boolean validato) {
        this.validato = validato;
    }

    public String getLuogoValidazione() {
        return luogoValidazione;
    }

    public void setLuogoValidazione(String luogoValidazione) {
        this.luogoValidazione = luogoValidazione;
    }

    public LocalDate getDataValidazione() {
        return dataValidazione;
    }

    public void setDataValidazione(LocalDate dataValidazione) {
        this.dataValidazione = dataValidazione;
    }

}
