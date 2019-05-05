import javax.crypto.SecretKey;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

public class EXValues
{
    public static void main(String... args) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(null, null); // Initialize a blank keystore

        SecretKey key = new SecretKeySpec(new byte[32], "AES");

        char[] password = "changeit".toCharArray();
        byte[] salt = new byte[20];
        new SecureRandom().nextBytes(salt);
        keyStore.setEntry("test", new KeyStore.SecretKeyEntry(key),
                new KeyStore.PasswordProtection(password,
                        "PBEWithHmacSHA512AndAES_128",
                        new PBEParameterSpec(salt, 100_000)));

        keyStore.store(new FileOutputStream("/tmp/keystore.p12"), password);
    }

}
