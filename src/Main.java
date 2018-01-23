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
            Interpreter interpreter = new Interpreter(input);
            Integer result = interpreter.expr();
            System.out.println(result);
        }

    }
}
