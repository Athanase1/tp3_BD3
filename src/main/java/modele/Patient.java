package modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
public class Patient {
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
    @Length(min = 2)
    private String nom;
    @NotNull
    @Length(min = 3)
    private String prenom;

    @NotNull
    @Email
    @Column(unique = true)
    private String courriel;

    @NotNull
    @Length(min = 3)
    private String mot_de_passe;

    @OneToMany(mappedBy = "patient")
    private List<RendezVous> rendezVouses;

    public Patient(String nom, String prenom, String courriel, String mot_de_passe) {
        this.nom = nom;
        this.prenom = prenom;
        this.courriel = courriel;
        this.mot_de_passe = mot_de_passe;
    }

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
    @Override
    public String toString() {
        return "Patient {" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", courriel='" + courriel + '\'' +
                ", mot_de_passe='********'" +
                ", nb_rendez_vous=" + (rendezVouses != null ? rendezVouses.size() : 0) +
                '}';
    }
    public Patient CreerPatient(String nom, String prenom, String courriel, String mot_de_passe) {
        return new Patient(nom, prenom, courriel, mot_de_passe);
    }
}
