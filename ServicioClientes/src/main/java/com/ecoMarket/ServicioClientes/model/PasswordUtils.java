import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    // Genera un hash BCrypt
    public static String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    // Verifica si la contraseña coincide con el hash
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
}