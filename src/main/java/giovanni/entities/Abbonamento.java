package giovanni.entities;

import giovanni.enums.TipoAbbonamentoEnum;
import jakarta.persistence.*;

import java.time.LocalDate;

 @Entity
public class Abbonamento extends TitoloDiViaggio{

     @Enumerated(EnumType.STRING)
private TipoAbbonamentoEnum tipoAbbonamento;
     @ManyToOne
     @JoinColumn(name = "utente_id")
     private Utenti utente;

    private LocalDate scadenza;
    protected long idTessera;


    public Abbonamento (){}
     public Abbonamento(LocalDate dataEmissione, Biglietteria emessoDa, TipoAbbonamentoEnum tipoAbbonamento, Utenti utente) {
         super(dataEmissione, emessoDa);
         this.tipoAbbonamento = tipoAbbonamento;
         this.utente = utente;
         if (tipoAbbonamento == TipoAbbonamentoEnum.MENSILE) this.scadenza = dataEmissione.plusMonths(1);
         else if (tipoAbbonamento == TipoAbbonamentoEnum.SETTIMANALE) this.scadenza = dataEmissione.plusDays(7);
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
                 ", utente=" + utente +
                 ", id=" + id +
                 '}';
     }
}
