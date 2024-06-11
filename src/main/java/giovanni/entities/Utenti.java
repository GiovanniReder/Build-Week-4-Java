package giovanni.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Utenti {

 @Id
 @OneToOne
 @JoinColumn(name = "tessera_id")
 private Tessera tessera;

 private String nome;
 private String cognome;

public Utenti(){}

    public Utenti(String cognome, String nome) {
        this.cognome = cognome;
        this.nome = nome;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String toString() {
        return "Utenti{" +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                '}';
    }
}
