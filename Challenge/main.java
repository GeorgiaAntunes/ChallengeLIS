import java.util.Arrays;
import java.util.List;

public class main {

  
  public static void main ( String[] args ) {
    List<String> messages = Arrays.asList("teste","SecretMessage");
    
    for ( String message : messages ) {
      Gerenciador ger = Gerenciador.loadMessage(message);
      
      
        System.out.println("-----------------------------");
        // System.out.println("Decryption: " + ger.decrypt(message, 3));
    }
  }
}
