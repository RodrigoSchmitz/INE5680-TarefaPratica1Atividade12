import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class KeyStoreUtils {

    private KeyStore keyStore;
    private static KeyStoreUtils instance;
    private String keyStorePassword;

    private KeyStoreUtils() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
//      Parte do keyStore
        String senha = ChavesUtils.getSalt();
        String salt = ChavesUtils.getSalt();
        Integer it = 10000;
        keyStorePassword = ChavesUtils.generateDerivedKey(senha, salt, it);
        System.out.println("Senha original = " + senha);
        System.out.println("Salt gerado = " + salt);
        System.out.println("Numero de iteracoes = " + it);
        System.out.println("Chave da keystore = " + keyStorePassword);
        keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(null, keyStorePassword.toCharArray());
    }

    public static KeyStoreUtils getInstance() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        if (instance == null) {
            instance = new KeyStoreUtils();
        }
        return instance;
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    public String getKeyStorePassword() {
        return keyStorePassword;
    }
}
