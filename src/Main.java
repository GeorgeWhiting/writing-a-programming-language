public class Main {
    public static void main(String args[]){

        Interpreter interpreter = new Interpreter("9+5");
        Integer result = interpreter.expr();
        System.out.println(result);

    }
}
