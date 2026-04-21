package modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.Date;

@Entity
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
    @NotNull
    private Date date;
    private @NotNull LocalTime heureDebut;
    private @NotNull LocalTime heureFin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "infirmiere_id")
    private Infirmiere infirmiere;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "service_id")
    private Service service;

    public RendezVous(Date date, @NotNull LocalTime heureDebut, @NotNull LocalTime heureFin, Infirmiere infirmiere, Patient patient, Service service_id) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.infirmiere = infirmiere;
        this.patient = patient;
        this.service = service_id;
    }

    public RendezVous() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public @NotNull LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(@NotNull LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public @NotNull LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(@NotNull LocalTime heureFin) {
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
