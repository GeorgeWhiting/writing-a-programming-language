import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        String input;
        Scanner reader = new Scanner(System.in);
        System.out.println("Type in stop to end process");

        while(true) {
            input = reader.nextLine();
            if(input.equals("stop")){
                System.out.println("Stopping....");
                break;
            }
            Lexer lexer = new Lexer(input);
            Parser parser = new Parser(lexer);
            Interpreter interpreter = new Interpreter(parser);
            Object result = interpreter.interpret();
            System.out.println(result);
        }
    }
}
