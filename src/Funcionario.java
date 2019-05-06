import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Scanner;

public class Funcionario {

    public String nome;

    public Funcionario(String nome) {
        this.nome = nome;
    }

    public void recebeMensagem(byte[] msgCriptografada) throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, UnrecoverableKeyException, InvalidAlgorithmParameterException, InvalidKeyException {

        //Busca da chave KeyStore
        SecretKeySpec chaveResgatada = (SecretKeySpec) KeyStoreUtils.getInstance().getKeyStore().getKey(Main.CHAVE_KEY, KeyStoreUtils.getInstance().getKeyStorePassword().toCharArray());
        System.out.println("Chave Resgatada = " + chaveResgatada.toString());

        //Pegar valor pra descriptografar
        Cipher out = Cipher.getInstance("AES/CBC/PKCS5Padding");
        out.init(Cipher.DECRYPT_MODE, chaveResgatada, new IvParameterSpec(new byte[16]));
        String msgRecebida = new String(out.doFinal(msgCriptografada), StandardCharsets.UTF_8);
        System.out.println("Mensagem recebida = " + msgRecebida);
    }

    public byte[] criaMensagemCriptografada() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, KeyStoreException, CertificateException, BadPaddingException, IllegalBlockSizeException, IOException {
        //Gera chave
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecretKey key = kgen.generateKey();
        System.out.println("Chave gerada = " + key.toString());

        //Cria e inicializa objeto para criptografar
        Cipher in;
        in = Cipher.getInstance("AES/CBC/PKCS5Padding");
        in.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));

        //Criptografa mensagem
        System.out.println("Digite a mensagem: ");
        Scanner sc = new Scanner(System.in);
        String mensagem = sc.nextLine();

        byte[] input = mensagem.getBytes(StandardCharsets.UTF_8);
        byte[] msgCriptografada = in.doFinal(input);
        System.out.println("Mensagem = " + mensagem);
        System.out.println("Mensagem criptografada = " + new String(msgCriptografada));

        //Armazena no keystore a chave
        KeyStoreUtils.getInstance().getKeyStore().setEntry(Main.CHAVE_KEY, new KeyStore.SecretKeyEntry(key),
                new KeyStore.PasswordProtection(KeyStoreUtils.getInstance().getKeyStorePassword().toCharArray(),
                        "PBEWithHmacSHA512AndAES_128",
                        new PBEParameterSpec(ChavesUtils.getSalt().getBytes(), 100000)));

        return msgCriptografada;
    }
}
