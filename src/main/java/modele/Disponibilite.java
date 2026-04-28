package modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "DATE_DISPO")
    @NotNull(message = "La date est obligatoire")
    private LocalDate date;

    @Column(name = "HEURE_DEBUT")
    @NotNull(message = "L'heure du debut de la disponibilité est obligatoire")
    private LocalDateTime heure_debut;

    @Column(name = "HEURE_FIN")
    @NotNull(message = "L'heure de fin de la disponibilté est obligatoire")
    private  LocalDateTime heure_fin;

    @ManyToOne(fetch = FetchType.EAGER)
    private Infirmiere infirmiere;

    public Disponibilite(@NotNull(message = "La date est obligatoire") LocalDate date, LocalDateTime heure_debut, LocalDateTime heure_fin) {
        this.date = date;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
    }

    public Disponibilite() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "La date est obligatoire") LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "La date est obligatoire") LocalDate date) {
        this.date = date;
    }

    public @NotNull(message = "L'heure du debut de la disponibilité est obligatoire") LocalDateTime getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(@NotNull(message = "L'heure du debut de la disponibilité est obligatoire") LocalDateTime heure_debut) {
        this.heure_debut = heure_debut;
    }

    public @NotNull(message = "L'heure de fin de la disponibilté est obligatoire") LocalDateTime getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(@NotNull(message = "L'heure de fin de la disponibilté est obligatoire") LocalDateTime heure_fin) {
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
