package src.astnodes.operations.arithmetic;

import src.astnodes.ASTNode;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

public class ASTSub implements ASTNode {
    private ASTNode l, r;

    public ASTSub(ASTNode l, ASTNode r){
        this.l = l;
        this.r = r;
    }

    @Override
    public int eval(Environment<Integer> e) {
        int valueL = this.l.eval(e);
        int valueR = this.r.eval(e);

        return valueL - valueR;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.l.compile(block, e);
        this.r.compile(block, e);
        block.emit(JVM.ISUB.toString());
    }
}
