public class BinOp extends ASTree{

    public ASTree left;
    public ASTree right;
    public Token op;


    public BinOp(ASTree left, Token op, ASTree right){
        this.left = left;
        this.op = op;
        this.right = right;
    }
}
