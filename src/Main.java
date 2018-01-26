import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        String longInput = "";
        String input;
        String replInput = "MOM >> ";
        Scanner reader = new Scanner(System.in);
        System.out.println("Type in stop or quit to end process");

        while(true) {
            System.out.print(replInput);
            input = reader.nextLine();
            if(input.equals("stop")){
                System.out.println(replInput + "goodbye");
                break;
            }
            if(input.equals("quit")){
                System.out.println(replInput + "goodbye");
                break;
            }


            if(input.equals("please")){
                longInput = "please  ";
                while(!input.equals("thanks.")){
                    System.out.print(replInput);
                    input = reader.nextLine();
                    longInput += input;
                }
                Lexer lexer = new Lexer(longInput);
                Parser parser = new Parser(lexer);
                Interpreter interpreter = new Interpreter(parser);
                try {
                    Object result = interpreter.interpret();
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Mom is not pleased...");
                }
            }  else {
                Lexer lexer = new Lexer(input);
                Parser parser = new Parser(lexer);
                Interpreter interpreter = new Interpreter(parser);
                try {
                    interpreter.interpret();
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Mom is not pleased...");
                }
            }
        }
    }
}


