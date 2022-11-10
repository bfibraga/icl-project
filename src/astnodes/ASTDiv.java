package src.astnodes;

import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

public class ASTDiv implements ASTNode{

    private ASTNode l, r;

    public ASTDiv(ASTNode l, ASTNode r){
        this.l = l;
        this.r = r;
    }

    @Override
    public int eval(Environment<Integer> e) {
        int valueL = this.l.eval(e);
        int valueR = this.r.eval(e);

        return valueL / valueR;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.l.compile(block, e);
        this.r.compile(block, e);
        block.emit(JVM.IDIV.toString());
    }
}
