import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        String longInput = "";
        String input;
        Scanner reader = new Scanner(System.in);
        System.out.println("Type in stop or quit to end process");

        while(true) {
            System.out.print("lavajava > ");
            input = reader.nextLine();
            if(input.equals("stop")){
                System.out.println("lavajava > " + "goodbye");
                break;
            }
            if(input.equals("quit")){
                System.out.println("lavajava > " + "goodbye");
                break;
            }


            if(input.equals("please")){
                longInput = "please  ";
                while(!input.equals("thanks.")){
                    System.out.print("lavajava > ");
                    input = reader.nextLine();
                    longInput += input;
                }
                Lexer lexer = new Lexer(longInput);
                Parser parser = new Parser(lexer);
                Interpreter interpreter = new Interpreter(parser);
                Object result = interpreter.interpret();
            }  else {
                Lexer lexer = new Lexer(input);
                Parser parser = new Parser(lexer);
                Interpreter interpreter = new Interpreter(parser);
                Object result = interpreter.interpret();
            }
        }
    }
}


