package src.astnodes;

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

    }
}
