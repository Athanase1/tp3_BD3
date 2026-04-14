package modele;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
public class Infirmiere {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq_gen")
    @SequenceGenerator(
            name = "hospital_seq_gen",
            sequenceName = "seq_hospital", // Le nom exact dans le script SQL
            initialValue = 1000,
            allocationSize = 2
    )
    private Long id;

    private String nom;
}
