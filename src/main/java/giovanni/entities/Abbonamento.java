package giovanni.entities;

import giovanni.enums.TipoAbbonamentoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Abbonamento extends TitoloDiViaggio {


    @Enumerated(EnumType.STRING)
    private TipoAbbonamentoEnum tipoAbbonamento;


    @ManyToOne
    private Tessera tessera;

    private LocalDate scadenza;


    public Abbonamento() {
    }

    public Abbonamento(LocalDate dataEmissione, Biglietteria emessoDa, TipoAbbonamentoEnum tipoAbbonamento, Tessera tessera) {
        super(dataEmissione, emessoDa);
        this.tipoAbbonamento = tipoAbbonamento;
        this.tessera = tessera;
        if (tipoAbbonamento == TipoAbbonamentoEnum.MENSILE) this.scadenza = dataEmissione.plusMonths(1);
        else if (tipoAbbonamento == TipoAbbonamentoEnum.SETTIMANALE) this.scadenza = dataEmissione.plusDays(7);
    }

    public TipoAbbonamentoEnum getTipoAbbonamento() {
        return tipoAbbonamento;
    }

    public void setTipoAbbonamento(TipoAbbonamentoEnum tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
    }


    public LocalDate getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDate scadenza) {
        this.scadenza = scadenza;
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "emessoDa=" + emessoDa +
                ", dataEmissione=" + dataEmissione +
                ", id=" + id +
                ", scadenza=" + scadenza +
                ", tessera=" + tessera +
                ", tipoAbbonamento=" + tipoAbbonamento +
                '}';
    }
}
