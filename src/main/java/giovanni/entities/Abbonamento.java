package giovanni.entities;

import giovanni.enums.TipoAbbonamentoEnum;
import jakarta.persistence.*;

import java.time.LocalDate;

 @Entity
public class Abbonamento extends DocumentoDiViaggio{



    // @Enumerated(EnumType.STRING)
private TipoAbbonamentoEnum tipoAbbonamento;

    private LocalDate scadenza;



    protected long idTessera;

    public Abbonamento (){}

     public Abbonamento(LocalDate dataEmissione, Biglietteria emessoDa, TipoAbbonamentoEnum tipoAbbonamento) {
         super(dataEmissione, emessoDa);
         this.tipoAbbonamento = tipoAbbonamento;
         if (tipoAbbonamento == TipoAbbonamentoEnum.MENSILE) this.scadenza = dataEmissione.plusMonths(1);
         else if (tipoAbbonamento == TipoAbbonamentoEnum.SETTIMANALE)   this.scadenza = dataEmissione.plusDays(7);
     }




     public TipoAbbonamentoEnum getTipoAbbonamento() {
        return tipoAbbonamento;
    }

    public void setTipoAbbonamento(TipoAbbonamentoEnum tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
    }

    public long getIdTessera() {
        return idTessera;
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
                "tipoAbbonamento=" + tipoAbbonamento +
                ", scadenza=" + scadenza +
                ", idTessera=" + idTessera +
                ", id=" + id +
                '}';
    }
}
