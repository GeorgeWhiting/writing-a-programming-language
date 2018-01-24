public class Num extends ASTree{

    public Token token;
    public Object value;

    public Num(Token token){
        this.token = token;
        this.value = token.value;
    }

}
