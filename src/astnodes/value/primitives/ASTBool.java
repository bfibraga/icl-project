package src.astnodes.value.primitives;

import src.astnodes.ASTNode;
import src.jvm.JVM;
import src.jvm.JVMValues;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TBool;
import src.type.Type;
import src.value.Bool;
import src.value.Value;

public class ASTBool implements ASTNode {

    private final boolean val;

    public ASTBool(boolean val){
        this.val = val;
    }

    @Override
    public Value eval(Environment<Value> e) {
        return new Bool(this.val);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        JVMValues intValue = this.val ? JVMValues.TRUE : JVMValues.FALSE;
        block.emit(String.format("%s %b", JVM.SIPUSH, intValue));
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        return new TBool();
    }
}
