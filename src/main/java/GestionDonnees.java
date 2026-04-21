import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class GestionDonnees {
    public List<String> lireFichier(String nomFichier) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(nomFichier); // charge le fichier depuis le dossier resources
        if (is == null) {
            throw new IllegalArgumentException("Fichier n'existe pas : " + nomFichier);
        }
        return new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.toList());
    }
}
