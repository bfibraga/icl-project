package src.astnodes.value.primitives;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TInt;
import src.type.Type;
import src.value.Int;
import src.value.Value;

public class ASTInt extends TypeHolder implements ASTNode {
    private int val;

    public ASTInt(int val){
        this.val = val;
    }

    @Override
    public Value eval(Environment<Value> e) {
        return new Int(this.val);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        block.emit(String.format("%s %d", JVM.SIPUSH, this.val));
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        this.setType(new TInt());
        return new TInt();
    }
}

