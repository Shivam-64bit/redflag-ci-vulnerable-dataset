import java.io.*;
import java.util.Base64;

class Data implements Serializable {
    String value;
}

public class VulnerableApp8 {

    // 🔴 VULN 1: Hardcoded API key
    static String API_KEY = "API-SECRET-999";

    public static void main(String[] args) {
        try {

            System.out.println("Using key: " + API_KEY);

            // 🔴 VULN 2: Insecure Deserialization
            byte[] decoded = Base64.getDecoder().decode(args[0]);
            ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(decoded)
            );

            Object obj = ois.readObject();
            System.out.println("Received: " + obj);

            // 🔴 VULN 3: Missing Authentication
            grantAdminAccess();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void grantAdminAccess() {
        System.out.println("Admin access granted without auth!");
    }
}