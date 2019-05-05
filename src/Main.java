import org.apache.commons.codec.DecoderException;
import org.bouncycastle.operator.OperatorCreationException;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {

    final static String CHAVE_KEY = "CHAVE_KEY";

    public static void main(String[] args) throws GeneralSecurityException, IOException, OperatorCreationException, DecoderException {
        Funcionario Alice = new Funcionario("Alice");
        Funcionario Bob = new Funcionario("Bob");

        System.out.println(Alice.nome + " quer enviar uma mensagem para " + Bob.nome + ", logo ela chama o metodo criaMensagemCriptografada() para criar uma mensagem criptografada");

        byte[] msgCriptografada = Alice.criaMensagemCriptografada();

        System.out.println("Agora " + Alice.nome + " envia a mensagem para " + Bob.nome + " usando o metodo recebeMensagem() passando a mensagem que foi criptografada");

        Bob.recebeMensagem(msgCriptografada);

        System.out.println("Agora " + Bob.nome + " recebeu a mensagem com sucesso e o expediente termina.");
    }

    //        ChavesUtils chavesUtils = new ChavesUtils();

//        //Gera senha e salt
//        String senha = chavesUtils.getSalt();
//        String salt = chavesUtils.getSalt();
//        int it = 10000;
//        System.out.println("Senha original = " + senha);
//        System.out.println("Salt gerado = " + salt);
//        System.out.println("Numero de iteracoes = " + it);

//        //Parte do keyStore
//        CHAVE_KEYSTORE = ChavesUtils.generateDerivedKey(senha, salt, it);
//        System.out.println("Chave da keystore derivada da senha = " + CHAVE_KEYSTORE);
//        KeyStore keyStore = KeyStore.getInstance("PKCS12");
//        keyStore.load(null, CHAVE_KEYSTORE.toCharArray());

//        //Gera chave
//        KeyGenerator kgen = KeyGenerator.getInstance("AES");
//        SecretKey key = kgen.generateKey();
//        System.out.println("Chave gerada = " + key.toString());

//        //Cria e inicializa objeto para criptografar
//        Cipher in;
//        in = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        in.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));

//        //Armazena no keystore a chave
//        keyStore.setEntry(CHAVE_KEY, new KeyStore.SecretKeyEntry(key),
//                new KeyStore.PasswordProtection(CHAVE_KEYSTORE.toCharArray(),
//                        "PBEWithHmacSHA512AndAES_128",
//                        new PBEParameterSpec(salt.getBytes(), 100000)));


//        //Criptografa mensagem
//        String mensagem = "Essa eh uma mensagem da dona Alice para o senhor Bob, eh noix";
//        byte[] input = mensagem.getBytes(StandardCharsets.UTF_8);
//        byte[] msgCriptografada = in.doFinal(input);
//        System.out.println("Mensagem = " + mensagem);
//        System.out.println("Mensagem criptografada = " + new String(msgCriptografada));
//
//        //Busca da chave na KeyStore
//        SecretKeySpec chaveResgatada = (SecretKeySpec) keyStore.getKey(CHAVE_KEY, CHAVE_KEYSTORE.toCharArray());
//        System.out.println("Chave Resgatada = " + chaveResgatada.toString());
//
//        //Pegar valor pra descriptografar
//        Cipher out = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        out.init(Cipher.DECRYPT_MODE, chaveResgatada, new IvParameterSpec(new byte[16]));
//        String msgRecebida = new String(out.doFinal(msgCriptografada), StandardCharsets.UTF_8);
//        System.out.println("Mensagem recebida = " + msgRecebida);
}
