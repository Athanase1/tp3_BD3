package modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;



@Entity
public class NouvelleDuree {
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
    @Min(5)
    private int nouvelleDuree;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "infirmiere_id")
    private Infirmiere infirmiere;

    public NouvelleDuree(int nouvelleDuree, Service service, Infirmiere infirmiere) {
        this.nouvelleDuree = nouvelleDuree;
        this.service = service;
        this.infirmiere = infirmiere;
    }

    public NouvelleDuree() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNouvelleDuree() {
        return nouvelleDuree;
    }

    public void setNouvelleDuree(int nouvelleDuree) {
        this.nouvelleDuree = nouvelleDuree;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Infirmiere getInfirmiere() {
        return infirmiere;
    }

    public void setInfirmiere(Infirmiere infirmiere) {
        this.infirmiere = infirmiere;
    }

    @Override
    public String toString() {
        return "NouvelleDuree{" +
                "id=" + id +
                ", nouvelleDuree=" + nouvelleDuree +
                ", service=" + service +
                ", infirmiere=" + infirmiere +
                '}';
    }
}
