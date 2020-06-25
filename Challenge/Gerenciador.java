import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/* 
Olá, gostaria de explicar meu raciocínio para descobrir o enigma. Em criptografia existe um método chamado
Cifra de Cézar, em ingles, Caezar cipher, e em sua fórmula de resolução é aplicado MOD 26, que é modulo de 26, esse 26 corresponde a quantidade
de letras do alfabeto, estava me baseando neste método para resolver o exercicio, pois quando li o enunciado, tinha entendido
que se tratava deste metodo para resolver o problema. Porém, o alfabeto dado no enunciado corresponde a um Array de 31 posições, 
se calcularmos o MOD 31, não chega-se a palavras concisas no .txt. Acabei perdendo muito tempo tentando adaptar a cifra de césar
ao alfabeto com caracteres (31), porém não obtive nenhum resultado positivo.
Descobri o valor da chave através de um teste de mesa, encontrei o valor -16, a fórmula para decifrar o enigma é 
(INDEX_LETRA_ALFABETO_CRIPTOGRAFADO - 16) = VALOR_INDEX_LETRA_DESCRIPTOGRAFADA. Como perdi muito tempo me baseando num problema que
achei bem mais dificil do que o que realmente era, acabei montando meu desenvolvimento nesses moldes. Como me restou pouco tempo para
readequar o código, decidi mandar desse jeito e explicar o motivo da minha dificuldade para resolver.
A mensagem secreta criptografada é:
VOCE DECIFROU A MENSAGEM SECRETA. ESSE DESAFIO FAZ PARTE DA PRIMEIRA DO PROCESSO SELETIVO. DOCUMENTE SUA SOLUCAO E NAO SQUECA DE NOS ENTREGAR
DENTRO DO PRAZO ESTABELECIDO.

Para atender ao prazo estebelecido decidi enviar desse jeito, porém, darei continuidade nesse código para que o mesmo possa resolver o enigma 
sendo executado. Gostaria de ter a oportunidade de reencaminhar-lo adiante.

*/


public class Gerenciador {

    String message;
    int key = -16;
    //31 posições deste alfabeto, contando os caracteres no inicio
    char[] alphabet = {'?','!',';',',','.','Z','Y','X','W','V','U','T','S','R','Q',
    'P','O','N','M','L','K','J','I','H','G','F','E','D','C','B','A'};
    

    public Gerenciador(String message, int key){
        this.message = message;
        this.key = key;
    }
       
    //Carrega o arquivo txt
    public static ArrayList<String> loadMessage ( String message ) {
        ArrayList<String> resultado = new ArrayList<>();
        Path path = Paths.get("messages/" + message + ".txt");
        try ( Scanner sc = new Scanner(Files.newBufferedReader(
                path, StandardCharsets.UTF_8)).useDelimiter("#") ) {
        while ( sc.hasNext() ) {
            resultado.add(sc.next());
        }
        } catch ( Exception e ) {
        System.err.format("Exception: %s%n", e);
        }
        
        //quebra a mensagem (split) e coloca no array vet
        ArrayList<String> vet = new ArrayList<>();
        resultado.forEach(line -> {
            Arrays.asList(line.split("")).forEach(c -> {
                vet.add(c);
            });
        });
        
        return vet;
    }
    public String decrypt(String []message, int key) {
        int aux = 0;
        char st = "";
        for (int i = 0; i < message.length; i++) {
            for(int j= 0; i < alphabet.length; i++){
                if(alphabet[j] == message.chartAt(i)){
                    message[i] = message[((j + Integer.parseInt(key)) % alphabet.length)];

                }

                if (Character.isUpperCase(message.charAt(i))) {
                    char ch = (char) (((int) message.charAt(i) +
                            shift - 65) % 26 + 65);
                    result.append(ch);
                } 
                else {
                    char ch = (char) (((int) vet.charAt(i) +
                            shift - 97) % 26 + 97);
                    result.append(ch);
                }
            }
        }
        return result;
    }

    String cipher(String msg, int key){
        String str = "";
        int len = msg.length();
        for(int i = 0; i < len; i++){
            char c = (char)(msg.charAt(i) + key);
            if (c > 'Z')
                str += (char)(msg.charAt(i) - (31-key));
            else
                str += (char)(msg.charAt(i) + key);
        }
        return str;
    }
    
    public static String decrypt2(String msg, int shiftKey){
        String pt = "";
        for (int i = 0; i < msg.length(); i++){
            char letter = msg.charAt(i);
            boolean upperCase = false;
            if((int)letter < 91){
                letter = (char)((int)letter + 32);
                upperCase = true;
            }
            int charPosition = alphabet.indexOf(letter);
            int keyVal = (charPosition - shiftKey) % 31;
            if (keyVal < 0){
                keyVal = alphabet.length() + keyVal;
            }
            char replaceVal = alphabet.charAt(keyVal);
            if(upperCase){
                replaceVal = (char)((int)replaceVal - 32);
            }
            pt += replaceVal;
        }
        return pt;
    }
    // Decrypts cipher using shift
    public static StringBuffer decrypt(String msg[], int key) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < msg.length; i++) {
            for(int j = 0; j < alphabet.length(); j++){
                if(msg[i]).charAt(alphabet[j]))
                if (Character.isUpperCase(msg.charAt(i))) {
                    char ch = (char) (((int) msg.charAt(i) +
                    key - 65) % 26 + 65);
                    result.append(ch);
                } else {
                    char ch = (char) (((int) msg.charAt(i) +
                    key - 97) % 26 + 97);
                    result.append(ch);
                }
            }
        }
        return result;
    }



}