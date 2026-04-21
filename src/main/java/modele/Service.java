package modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;


import java.util.List;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq_gen")
    @SequenceGenerator(
            name = "hospital_seq_gen",
            sequenceName = "seq_hospital", // Le nom exact dans le script SQ L
            initialValue = 1000,
            allocationSize = 2
    )
    private Long id;

    @NotNull
    @Length(min = 3)
    private String nom;

    @NotNull
    private int duree;
    @NotNull
    @Positive
    private double prix;

    @OneToMany(mappedBy = "service")
    private List<NouvelleDuree> nouvellesDurees;
    @OneToMany(mappedBy = "service")
    private List<RendezVous> rendezVous;

    public Service(String nom, int duree, double prix) {
        this.nom = nom;
        this.duree = duree;
        this.prix = prix;
    }

    public List<NouvelleDuree> getNouvellesDurees() {
        return nouvellesDurees;
    }

    public void setNouvellesDurees(List<NouvelleDuree> nouvellesDurees) {
        this.nouvellesDurees = nouvellesDurees;
    }

    public Service() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }



    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", duree=" + duree +
                ", prix=" + prix +
                ", nouvelleDuree_id="  +
                '}';
    }
}
