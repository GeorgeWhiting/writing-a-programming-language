public class NodeVisitor {

    public Object visit(ASTree node){


        String nodeType = node.getClass().getSimpleName();
        Interpreter interpOb = (Interpreter) this;

        if(nodeType.equals("BinOp")){
            return interpOb.visitBinOp((BinOp) node);
        } else if(nodeType.equals("Num")) {
            return interpOb.visitNum((Num) node);
        } else if(nodeType.equals("UnaryOp")) {
            return interpOb.visitUnaryOp((UnaryOp) node);
        } else if(nodeType.equals("Assign")) {
            interpOb.visitAssign((Assign) node);
            return null;
        } else if (nodeType.equals("Compound")) {
            interpOb.visitCompound((Compound) node);
            return null;
        } else if (nodeType.equals("Var")) {
            return interpOb.visitVar((Var) node);
        } else if (nodeType.equals("NoOp")) {
            interpOb.visitNoOp((NoOp) node);
            return null;
        } else if (nodeType.equals("Sayer")) {
            interpOb.visitSayer((Sayer) node);
            return null;
        } else {
            throw new java.lang.RuntimeException("Unrecognised node type");
        }

    }

}



