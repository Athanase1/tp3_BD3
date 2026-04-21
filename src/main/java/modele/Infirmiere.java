package modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Generated;
import org.hibernate.validator.constraints.Length;

import java.util.List;


@Entity
public class Infirmiere {
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
    @Length(min = 3)
    private String prenom;

    @NotNull
    @Email
    @Column(unique = true)
    private String courriel;

    @OneToMany(mappedBy = "infirmiere")
    private List<Disponibilite> disponibilites;

    @OneToMany(mappedBy = "infirmiere")
    private List<NouvelleDuree> nouvellesDurees;

    public Infirmiere(String nom, String prenom, String courriel) {
        this.nom = nom;
        this.prenom = prenom;
        this.courriel = courriel;
    }

    public Infirmiere() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public List<Disponibilite> getDisponibilites() {
        return disponibilites;
    }

    public void setDisponibilites(List<Disponibilite> disponibilites) {
        this.disponibilites = disponibilites;
    }

    public List<NouvelleDuree> getNouvellesDurees() {
        return nouvellesDurees;
    }

    public void setNouvellesDurees(List<NouvelleDuree> nouvellesDurees) {
        this.nouvellesDurees = nouvellesDurees;
    }

    @Override
    public String toString() {
        return "Infirmiere {" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", courriel='" + courriel + '\'' +
                ", nb_disponibilites=" + (disponibilites != null ? disponibilites.size() : 0) +
                ", nb_nouvellesDurees=" + (nouvellesDurees != null ? nouvellesDurees.size() : 0) +
                '}';
    }
    public Infirmiere creerInfirmiere(String nom, String prenom, String courriel) {
       return new Infirmiere(nom, prenom, courriel);
    }
}
