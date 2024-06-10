package giovanni.entities;

import giovanni.enums.TipoAbbonamentoEnum;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Abbonamento extends Biglietteria{

    @Enumerated(EnumType.STRING)
private TipoAbbonamentoEnum tipoAbbonamento;

    private LocalDate scadenza;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    protected long idTessera;

    public Abbonamento (){}

    public Abbonamento(TipoAbbonamentoEnum tipoAbbonamento, LocalDate scadenza) {
        this.tipoAbbonamento = tipoAbbonamento;
        this.scadenza = scadenza;
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
