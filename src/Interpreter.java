import java.util.HashMap;

public class Interpreter extends NodeVisitor {
    private Parser parser;
    public HashMap<Object, Object> symbolTable;

    Interpreter(){
        this.symbolTable = new HashMap<>();
    }

    public Object visitBinOp(BinOp node) {
        if (node.op.type.equals("PLUS")) {
            return (Integer) this.visit(node.left) + (Integer) this.visit(node.right);
        } else if (node.op.type.equals("MINUS")) {
            return (Integer) this.visit(node.left) - (Integer) this.visit(node.right);
        } else if (node.op.type.equals("MULTIPLY")) {
            return (Integer) this.visit(node.left) * (Integer) this.visit(node.right);
        } else if (node.op.type.equals("DIVIDE")) {
            return (Integer) this.visit(node.left) / (Integer) this.visit(node.right);
        } else {
            throw new java.lang.RuntimeException(node.op.type + " is not an accepted operator");
        }
    }

    public Object visitNum(Num node) {
        return node.value;
    }

    public Object interpret(Parser parser) {
        this.parser = parser;
        ASTree tree = this.parser.parse();
        return this.visit(tree);
    }

    public void visitCompound(Compound node) {
        for(int i = 0; i < node.children.size(); i++){
            this.visit((ASTree) node.children.get(i));
        }
    }

    public void visitNoOp(NoOp node){

    }

    public void visitAssign(Assign node){
        Object varName = node.left.value;
        symbolTable.put(varName, this.visit(node.right));
    }

    public Object visitVar(Var node){
        Object varName = node.value;
        Object val = this.symbolTable.get(varName);
        if(val == null){
            throw new java.lang.RuntimeException("Undefined variable " + varName.toString());
        } else {
            return val;
        }
    }

    public Object visitUnaryOp(UnaryOp node){
        String op = node.op.type;
        Object calcVal = this.visit(node.expression);
        if (op.equals("PLUS")) {
            return calcVal;
        } else if (op.equals("MINUS")) {
            return -(Integer) calcVal;
        }
        return null;
    }

    public void visitSayer(Sayer node){
       System.out.println(this.visit(node.right));
    }
}
