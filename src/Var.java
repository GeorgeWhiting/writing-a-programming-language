public class Var extends ASTree {

    Token token;
    Object value;

    public Var(Token token){
        this.token = token;
        this.value = token.value;
    }

}
