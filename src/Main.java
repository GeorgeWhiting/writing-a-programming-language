import java.util.Scanner;

public class Main {
    public static void main(String args[]){

<<<<<<< HEAD
        Interpreter interpreter = new Interpreter("2+2");
        Integer result = interpreter.expr();
        System.out.println(result);
=======
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
>>>>>>> ca98032a4fae4d8ae9f9b6f391a32e632aedf942

    }
}
