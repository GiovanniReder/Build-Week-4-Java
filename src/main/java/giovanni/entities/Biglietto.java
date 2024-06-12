package giovanni.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity

public class Biglietto extends TitoloDiViaggio {

    // COLLEGATO AI MEZZI
    //  @OneToOne(mappedBy = "idBigliettiTimbrato")
    private Boolean validato;

    private LocalDate dataValidazione;

    // DA COLLEGARE CON LA CLASSE MEZZI
    private String luogoValidazione;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzi mezzo;


    public Biglietto() {
    }

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

    @Override
    public String toString() {
        return "Biglietto{" +
                "validato=" + validato +
                ", dataValidazione=" + dataValidazione +
                ", luogoValidazione='" + luogoValidazione + '\'' +
                ", mezzo=" + mezzo +
                '}';
    }
}
