public class UnaryOp extends ASTree {

    public Token op;
    public ASTree expression;

    public UnaryOp(Token op, ASTree expression){
        this.op = op;
        this.expression = expression;
    }
}
