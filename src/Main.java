import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static String CHAVE_KEY = "CHAVE_KEY";

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        Funcionario Alice = new Funcionario("Alice");
        Funcionario Bob = new Funcionario("Bob");

        System.out.println(Alice.nome + " quer enviar uma mensagem para " + Bob.nome + ", logo ela chama o metodo criaMensagemCriptografada() para criar uma mensagem criptografada");

        byte[] msgCriptografada = Alice.criaMensagemCriptografada();

        System.out.println("Agora " + Alice.nome + " envia a mensagem para " + Bob.nome + " usando o metodo recebeMensagem() passando a mensagem que foi criptografada");

        Bob.recebeMensagem(msgCriptografada);

        System.out.println("Agora " + Bob.nome + " recebeu a mensagem com sucesso e o expediente termina.");
    }
}
