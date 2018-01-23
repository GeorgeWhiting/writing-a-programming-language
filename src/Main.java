public class Main {
    public static void main(String args[]){

        Interpreter interpreter = new Interpreter("2+2");
        Integer result = interpreter.expr();
        System.out.println(result);

    }
}
