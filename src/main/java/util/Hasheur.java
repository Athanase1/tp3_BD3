package util;

import java.security.MessageDigest;
import java.util.Base64;

public class Hasheur {
    public static String hacherMotDePasse(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du hachage", e);
        }
    }
}
