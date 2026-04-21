import modele.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Application {
    public static void main(String[] args) {
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

        // 2. Utilisation d'un bloc try-with-resources pour gérer la session
        try (Session session = factory.getCurrentSession()) {

        } catch (Exception e) {
            System.err.println("Échec de la connexion :");
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
