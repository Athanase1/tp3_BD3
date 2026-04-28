import modele.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import util.Hasheur;

import java.sql.Date;
import java.time.LocalDateTime;

import java.util.Collections;
import java.util.List;
import java.util.Random;

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
        Random rand = new Random();


        try (Session session = factory.openSession()) {

            // création de 6 services de base
           session.beginTransaction();
           Service serv1 = new Service("Prélèvement sanguin",15,40.00);
           session.persist(serv1);
           Service serv2 = new Service("Lavage d’oreille", 20,45.00);
           session.persist(serv2);
           Service serv3 = new Service("Injection de médication",10,40.00);
           session.persist(serv3);
           Service serv4 = new Service("Extraction de points de suture",15,45.00);
           session.persist(serv4);
           Service serv5 = new Service("Suivi de plaies",20,40.00);
           session.persist(serv5);
           Service serv6 = new Service("Évaluation de la condition de santé",30,65.00);
           session.persist(serv6);
           session.getTransaction().commit();
           System.out.println("Sauvegarde de 6 service avec succèss");



            // creation de 5000 clients (patients)
            session.beginTransaction();

            for (int i = 0; i < 5000; i++) {
                //création des noms au hasard
                String nom = noms.get(i % noms.size());
                String prenom = prenoms.get(i % prenoms.size());

                // Format du courriel demandé: prenom.nom@tp3.com
                // On enlève les espaces et on met en minuscule pour faire propre
                String email = (prenom + "." + nom + i).toLowerCase().replace(" ", "") + "@tp3.com";

                Patient p = new Patient(nom, prenom, email, motDePasseHache);
                session.persist(p);

                // Batch processing : on vide la mémoire tous les 50 enregistrements sinon le programme ralentit
                if (i % 50 == 0) {
                    session.flush();
                    session.clear();
                }
            }

            session.getTransaction().commit();
            System.out.println("5000 patients ont été créés avec succès !");

            // création de 100 infirmières
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


            // création service selon infirmière
            session.beginTransaction();

            // on recupère les infirmieres
            List<Infirmiere> infirmieres = session.createQuery("from Infirmiere", Infirmiere.class).list();
            // On récupère d'abord les services dans la bd
            List<Service> tousServices = session.createQuery("from Service", Service.class).list();

            for (int i = 0; i < infirmieres.size(); i++) {
                Infirmiere inf = infirmieres.get(i);
                if (i < 60) {
                    // Donne tous les services, temps de base
                    for (Service s : tousServices) {
                        ajouterAptitude(session, inf, s, s.getDuree());
                    }
                } else if (i < 80) {
                    // Prélèvement et Suivi + 5 min
                    for (Service s : tousServices) {
                        if (s.getNom().contains("sanguin") || s.getNom().contains("plaies"))
                            ajouterAptitude(session, inf, s, s.getDuree() + 5);
                    }
                } else {
                    // Lavage d'oreille - 5 min
                    for (Service s : tousServices) {
                        if (s.getNom().contains("oreille"))
                            ajouterAptitude(session, inf, s, s.getDuree() - 5);
                    }
                }
            }
            session.getTransaction().commit();
            System.out.println("Attribution des services aux infirmieres avec succèss!");

            // création de 200 disponibilites pour chaque infirmière
            session.getTransaction().begin();
            List<Infirmiere> infirmieres2 = session.createQuery("from Infirmiere ", Infirmiere.class).list();

            for (Infirmiere inf : infirmieres2) {
                for (int j = 0; j < 200; j++) {
                    Disponibilite d = new Disponibilite();
                    d.setInfirmiere(inf);

                    // Génération du moment de départ
                    LocalDateTime start = LocalDateTime.of(2026 + rand.nextInt(2), 1 + rand.nextInt(12), 1 + rand.nextInt(28), 8 + rand.nextInt(8), 0);

                    d.setHeure_debut(start);
                    d.setHeure_fin(start.plusHours(2));
                    d.setDate(start.toLocalDate());

                    session.persist(d);
                }
            }
            session.getTransaction().commit();
            System.out.println("Création de 200 disponibilités pour chaque infirmières!");

            //Créer 10 000 rendez-vous ---
            session.getTransaction().begin();
            // Récupérer les listes pour les lier aléatoirement



            List<Patient> patients = session.createQuery("from Patient", Patient.class).list();
            List<Infirmiere> infirmieres1 = session.createQuery("from Infirmiere", Infirmiere.class).list();
            List<Service> services = session.createQuery("from Service", Service.class).list();
            List<Disponibilite> disponibilites = session.createQuery("from Disponibilite", Disponibilite.class).list();

        // Mélange aléatoire des disponibilités pour respecter la contrainte UNIQUE
            Collections.shuffle(disponibilites);
            for (int i = 0; i < 10000; i++) {
                RendezVous rdv = new RendezVous();

                // Attribution des entités (on utilise l'index i pour les dispos)
                rdv.setPatient(patients.get(rand.nextInt(patients.size())));
                rdv.setInfirmiere(infirmieres1.get(rand.nextInt(infirmieres1.size())));
                rdv.setService(services.get(rand.nextInt(services.size())));
                rdv.setDisponibilite(disponibilites.get(i));

                // 3. Génération temporelle cohérente
                LocalDateTime momentRdv = LocalDateTime.of(
                        2026 + rand.nextInt(2),
                        1 + rand.nextInt(12),
                        1 + rand.nextInt(28),
                        8 + rand.nextInt(9),
                        0
                );

                rdv.setHeureDebut(momentRdv);
                rdv.setHeureFin(momentRdv.plusMinutes(30));
                rdv.setDate(momentRdv.toLocalDate()); // Utilise le bon setter selon ton entité

                session.persist(rdv);

                // Batch processing : on flush mais on évite le clear() total
                // pour ne pas perdre les listes chargées en mémoire.
                if (i > 0 && i % 100 == 0) {
                    session.flush();
                }
            }

            session.getTransaction().commit();
            System.out.println("Création de 10 000 rendez-vous aléatoires terminée avec succès !");

        } catch (Exception e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        } finally {
            factory.close();
        }

    }
    private static void ajouterAptitude(Session s, Infirmiere i, Service ser, int duree) {
        NouvelleDuree apt = new NouvelleDuree();
        apt.setInfirmiere(i);
        apt.setService(ser);
        apt.setNouvelleDuree(duree);
        s.persist(apt);
    }
}
