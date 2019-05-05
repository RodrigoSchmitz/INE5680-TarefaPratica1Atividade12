import org.bouncycastle.operator.OperatorCreationException;

import javax.crypto.SecretKey;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

public class Main {

    public static void main(String[] args) throws GeneralSecurityException, IOException, OperatorCreationException {
        ChavesUtils chavesUtils = new ChavesUtils();

        String senha = chavesUtils.getSalt();
        String salt = chavesUtils.getSalt();
        int it = 10000;

        System.out.println("Senha original = " + senha);
        System.out.println("Salt gerado = " + salt);
        System.out.println("Numero de iteracoes = " + it);


        //Parte do keyStore
        String chaveKeyStore = ChavesUtils.generateDerivedKey(senha, salt, it);

        System.out.println("Chave derivada da senha = " + chaveKeyStore);


        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //Inicia keytore vazio
        keyStore.load(null, chaveKeyStore.toCharArray());

        //criptografa mensagem usando AES
        String mensagem = "ola";
        byte[] msg = mensagem.getBytes();

        SecretKey key = new SecretKeySpec(msg, "AES");

        //Armazena no keystore
        keyStore.setEntry("test", new KeyStore.SecretKeyEntry(key),
                new KeyStore.PasswordProtection(chaveKeyStore.toCharArray(),
                        "PBEWithHmacSHA512AndAES_128",
                        new PBEParameterSpec(salt.getBytes(), 100_000)));

        // Busca do KeyStore
          SecretKey cha = (SecretKey) keyStore.getKey("test",chaveKeyStore.toCharArray());

        System.out.println(new String(cha.getEncoded()));

    }
}
