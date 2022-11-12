package src.astnodes.operations.arithmetic;

import src.astnodes.ASTNode;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

public class ASTMod implements ASTNode {
    private ASTNode l, r;

    public ASTMod(ASTNode l, ASTNode r){
        this.l = l;
        this.r = r;
    }

    @Override
    public int eval(Environment<Integer> e) {
        int valueL = this.l.eval(e);
        int valueR = this.r.eval(e);

        return valueL % valueR;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        //1 : result = l / r
        //2 : l - ( result * r )
        l.compile(block, e);
        block.emit(JVM.DUP.toString());
        r.compile(block, e);
        block.emit(JVM.DUP.toString());
        block.emit(String.format("%s_%d",JVM.ISTORE, 1));
        block.emit(JVM.IDIV.toString());
        block.emit(String.format("%s_%d",JVM.ILOAD, 1));
        block.emit(JVM.IMUL.toString());
        block.emit(JVM.ISUB.toString());
    }
}
