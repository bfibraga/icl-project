package src.astnodes;

import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

public class ASTNeg implements ASTNode {

    private ASTNode exp;

    public ASTNeg(ASTNode exp){
        this.exp = exp;
    }

    @Override
    public int eval(Environment<Integer> e) {
        return -1 * exp.eval(e);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.exp.compile(block, e);
        block.emit(JVM.INEG.toString());
    }
}
