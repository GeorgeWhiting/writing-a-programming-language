public class BinOp extends ASTree{

    public Object left;
    public Object right;
    public Token op;


    public BinOp(Object left, Token op, Object right){
        this.left = left;
        this.op = op;
        this.right = right;

    }

}
