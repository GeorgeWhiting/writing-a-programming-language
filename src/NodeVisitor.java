import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NodeVisitor {

    public Object visit(ASTree node){
        String methodName = "visit" + node.getClass().getSimpleName();
        Method visitor = null;
        try {
            visitor = Interpreter.class.getMethod(methodName, ASTree.class);
        } catch(NoSuchMethodException e) {
            throw new java.lang.RuntimeException(e + "No such method " + methodName + " found in " + Interpreter.class.toString());
        }

        try {
            return visitor.invoke(this, node);
        } catch(IllegalAccessException | InvocationTargetException e) {
            throw new java.lang.RuntimeException(e + " error when attempting to invoke " + methodName + " on " + this.toString());
        }

    }

}



