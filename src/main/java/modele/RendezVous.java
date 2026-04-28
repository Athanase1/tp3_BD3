package modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "RENDEZ_VOUS")
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq_gen")
    @SequenceGenerator(
            name = "hospital_seq_gen",
            sequenceName = "seq_hospital", // Le nom exact dans le script SQ L
            initialValue = 1000,
            allocationSize = 2
    )
    private Long id;
    @Column(name = "DATE_RDV")
    @NotNull(message = "La date est obligatoire.")
    private LocalDate date;

    @NotNull(message = "L'heure du debut de rendez-vous est obligatoire.")
    @Column(name = "HEURE_DEBUT")
    private LocalDateTime heureDebut;
    @Column(name = "HEURE_FIN")
    @NotNull(message = "L'heure de la fin du rendez-vous est obligatoire.")
    private  LocalDateTime heureFin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "infirmiere_id")
    private Infirmiere infirmiere;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "service_id")
    private Service service;
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "disponibilite_id")
   private Disponibilite disponibilite;

    public RendezVous(@NotNull(message = "La date est obligatoire.") LocalDate date, LocalDateTime heureDebut, LocalDateTime heureFin, Patient patient, Infirmiere infirmiere, Service service) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.patient = patient;
        this.infirmiere = infirmiere;
        this.service = service;
    }

    public RendezVous() {
    }

    public @NotNull(message = "La date est obligatoire.") LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "La date est obligatoire.") LocalDate date) {
        this.date = date;
    }

    public @NotNull(message = "L'heure du debut de rendez-vous est obligatoire.") LocalDateTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(@NotNull(message = "L'heure du debut de rendez-vous est obligatoire.") LocalDateTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public @NotNull(message = "L'heure de la fin du rendez-vous est obligatoire.") LocalDateTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(@NotNull(message = "L'heure de la fin du rendez-vous est obligatoire.") LocalDateTime heureFin) {
        this.heureFin = heureFin;
    }

    public Infirmiere getInfirmiere() {
        return infirmiere;
    }

    public void setInfirmiere(Infirmiere infirmiere_id) {
        this.infirmiere = infirmiere_id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient_id) {
        this.patient = patient_id;
    }

    public Service getService_id() {
        return service;
    }

    public void setService_id(Service service_id) {
        this.service = service_id;
    }

    public Disponibilite getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Disponibilite disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "RendezVous{" +
                "id=" + id +
                ", date=" + date +
                ", patient=" + (patient != null ? patient.getNom() : "null") +
                ", service=" + (service != null ? service.getNom() : "null") +
                '}';
    }
}
