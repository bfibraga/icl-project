package src.astnodes;

import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

public class ASTNum implements ASTNode {
    private int val;

    public ASTNum(int val){
        this.val = val;
    }

    @Override
    public int eval(Environment<Integer> e) {
        return this.val;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        block.emit(String.format("%s %d", JVM.SIPUSH, this.val));
    }
}

