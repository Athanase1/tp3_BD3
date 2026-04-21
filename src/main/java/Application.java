import modele.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import util.Hasheur;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        GestionDonnees donnees = new GestionDonnees();
        List<String> noms = donnees.lireFichier("Nom.txt");
        List<String> prenoms = donnees.lireFichier("Prenom.txt");
        // 1. Initialisation de la SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Infirmiere.class)
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Disponibilite.class)
                .addAnnotatedClass(NouvelleDuree.class)
                .addAnnotatedClass(Service.class)
                .addAnnotatedClass(RendezVous.class)
                .buildSessionFactory();

        //meme hash pour gagner du temps
        String motDePasseHache = Hasheur.hacherMotDePasse("TP3AK");

        try (Session session = factory.openSession()) { // openSession est souvent plus simple pour les traitements de masse
           /*
            // creation de 5000 clients (patients)
            session.beginTransaction();

            for (int i = 0; i < 5000; i++) {
                //création des noms au hasard
                String nom = noms.get(i % noms.size());
                String prenom = prenoms.get(i % prenoms.size());

                // Format du courriel demandé: prenom.nom@tp3.com
                // On enlève les espaces et on met en minuscule pour faire propre
                String email = (prenom + "." + nom).toLowerCase().replace(" ", "") + "@tp3.com";

                Patient p = new Patient(nom, prenom, email, motDePasseHache);
                session.persist(p);

                // Batch processing : on vide la mémoire tous les 50 enregistrements sinon le programme ralentit
                if (i % 50 == 0) {
                    session.flush();
                    session.clear();
                }
            }

            session.getTransaction().commit();
            System.out.println("5000 patients ont été créés avec succès !");*/

            session.beginTransaction();
            for (int i = 0; i < 100; i++) {
                // creation de noms au hasard
                String nom = noms.get(i % noms.size());
                String prenom = prenoms.get(i % prenoms.size());

                // on format le email pour le format demandé
                String email = (prenom + "." + nom).toLowerCase().replace(" ", "") + "@tp3.com";
                Infirmiere infirmiere = new Infirmiere(nom, prenom, email);
                session.persist(infirmiere);
                // Batch processing : on vide la mémoire tous les 50 enregistrements sinon le programme ralentit
                if (i % 50 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.getTransaction().commit();
            System.out.println("100 infirmieres ont été créés avec succès !");

        } catch (Exception e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        } finally {
            factory.close();
        }
    }
}
