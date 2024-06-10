package giovanni.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Utenti {

 @Id
 @OneToOne
 @JoinColumn(name = "tessera_id")
 private Tessera tessera;

 @OneToMany(mappedBy = "utente")
 private List<Abbonamento> abbonamenti;
 private String nome;
 private String cognome;

public Utenti(){}

    public Utenti(String cognome, String nome) {
        this.cognome = cognome;
        this.nome = nome;
    }

    public String toString() {
        return "Utenti{" +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                '}';
    }
}
