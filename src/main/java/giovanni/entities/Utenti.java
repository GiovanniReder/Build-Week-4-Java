package giovanni.entities;

import jakarta.persistence.*;

@Entity
public class Utenti {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@OneToOne
    private long numTessera;

private String nome;
private String cognome;

public Utenti(){}

    public Utenti(String cognome, String nome) {
        this.cognome = cognome;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Utenti{" +
                "numTessera=" + numTessera +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                '}';
    }
}
