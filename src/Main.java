import java.nio.charset.Charset;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        ChavesUtils chavesUtils = new ChavesUtils();

        String chaveMasterKeyStore = chavesUtils.gerarChaveAleatoria();

        System.out.println(chaveMasterKeyStore);
    }
}
