package giovanni.entities;

import jakarta.persistence.*;

@Entity
public class Utenti {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @OneToOne(mappedBy = "utente")

    private Tessera tessera;

    private String nome;
    private String cognome;

    public Utenti() {
    }

    public Utenti(String cognome, String nome) {
        this.cognome = cognome;
        this.nome = nome;
    }

    public long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Utenti{" +
                "id=" + id +
                ", tessera=" + tessera +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                '}';
    }
}
