package modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalTime;

@Entity
public class Disponibilite {
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
    private Date date;
    private @NotNull LocalTime heure_debut;
    private @NotNull LocalTime heure_fin;

    @ManyToOne(fetch = FetchType.EAGER)
    private Infirmiere infirmiere;

    public Disponibilite(Date date, @NotNull LocalTime heure_debut, @NotNull LocalTime heure_fin, Infirmiere infirmiere) {
        this.date = date;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.infirmiere = infirmiere;
    }
    public Disponibilite() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public @NotNull LocalTime getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(@NotNull LocalTime heure_debut) {
        this.heure_debut = heure_debut;
    }

    public @NotNull LocalTime getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(@NotNull LocalTime heure_fin) {
        this.heure_fin = heure_fin;
    }

    public Infirmiere getInfirmiere() {
        return infirmiere;
    }

    public void setInfirmiere(Infirmiere infirmiere_id) {
        this.infirmiere = infirmiere_id;
    }

    @Override
    public String toString() {
        return "Disponibilite{" +
                "id=" + id +
                ", date=" + date +
                ", heure_debut=" + heure_debut +
                ", heure_fin=" + heure_fin +
                ", infirmiere_id=" + infirmiere +
                '}';
    }

}
