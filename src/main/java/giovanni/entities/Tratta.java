package giovanni.entities;

import jakarta.persistence.*;

import java.util.List;

 @Entity
 @Table
public class Tratta {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String partenza;

    private String capolinea;


  private double tempoMedioPercorrenza;

    @OneToMany
    private List<Mezzi> mezzi;



  public Tratta(){}


     public Tratta(String capolinea, String partenza, double tempoMedioPercorrenza, List<Mezzi> mezzi) {
         this.capolinea = capolinea;
         this.partenza = partenza;
         this.tempoMedioPercorrenza = tempoMedioPercorrenza;
         this.mezzi = mezzi;
     }

     @Override
     public String toString() {
         return "Tratta{" +
                 "id=" + id +
                 ", partenza='" + partenza + '\'' +
                 ", capolinea='" + capolinea + '\'' +
                 ", tempoMedioPercorrenza=" + tempoMedioPercorrenza +
                 ", mezzi=" + mezzi +
                 '}';
     }
 }
