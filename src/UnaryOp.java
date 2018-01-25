public class UnaryOp extends ASTree {

    public Token op;
    public ASTree expr;

    public UnaryOp(Token op, ASTree expr){
        this.op = op;
        this.expr = expr;
    }
}
