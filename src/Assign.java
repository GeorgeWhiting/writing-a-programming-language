import jdk.nashorn.internal.ir.debug.ASTWriter;

public class Assign extends ASTree {

    public ASTree left;
    public Token op;
    public ASTree right;

    public Assign(ASTree left, Token op, ASTree right){

        this.left = left;
        this.op = op;
        this.right = right;

    }

}
