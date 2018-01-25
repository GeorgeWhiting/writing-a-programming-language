public class Interpreter extends NodeVisitor {
    private Integer pos;
    private char currentChar;
    private Parser parser;


    Interpreter(Parser parser){
        this.parser = parser;
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

    public Object interpret() {
        ASTree tree = this.parser.parse();
        return this.visit(tree);
    }

//    private void error(){
//        throw new java.lang.RuntimeException("Invalid syntax");
//    }

    public Object visit_UnaryOp(UnaryOp node) {
        String op = node.op.type;
        Object calcVal = this.visit(node.expression);
        if (op.equals("PLUS")) {
            return calcVal;
        } else if (op.equals("MINUS")) {
            return -(Integer) calcVal;
        }
        return null;
    }
}
