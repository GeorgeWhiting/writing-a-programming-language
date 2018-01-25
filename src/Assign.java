public class Assign extends ASTree {

    public Var left;
    public Token op;
    public ASTree right;

    public Assign(Var left, Token op, ASTree right){

        this.left = left;
        this.op = op;
        this.right = right;

    }

}
