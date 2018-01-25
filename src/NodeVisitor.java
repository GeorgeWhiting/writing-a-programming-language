import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NodeVisitor {

    public Object visit(ASTree node){
        String methodName = "visit" + node.getClass().getSimpleName();
        Method visitor = null;

//        try {
//            Method[] methods = Interpreter.class.getMethods();
//            System.out.println(methods);
//            for(int i = 0; i < methods.length; i++) {
//                System.out.println(methods[i]);
//            }
//        } catch(Exception e) {
//            throw new java.lang.RuntimeException(e);
//        }

//        try {
//            visitor = Interpreter.class.getMethod(methodName, ASTree.class);
//        } catch(NoSuchMethodException e) {
//            throw new java.lang.RuntimeException("No such method " + methodName + " found in " + Interpreter.class.toString());
//        }
//
//        try {
//            return visitor.invoke(this, node);
//        } catch(IllegalAccessException | InvocationTargetException e) {
//            throw new java.lang.RuntimeException(e + " when attempting to invoke " + methodName + " on " + this.toString());
//        }

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
        } else if (nodeType.equals("NoOp")){
            interpOb.visitNoOp((NoOp) node);
            return null;
        } else {
            throw new java.lang.RuntimeException("Unrecognised node type");
        }

    }

}



