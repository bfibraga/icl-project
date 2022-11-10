package src.astnodes;

import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

public class ASTPow implements ASTNode{
    private ASTNode l, r;

    public ASTPow(ASTNode l, ASTNode r){
        this.l = l;
        this.r = r;
    }

    @Override
    public int eval(Environment<Integer> e) {
        int valueL = this.l.eval(e);
        int valueR = this.r.eval(e);

        return (int) Math.pow(valueL, valueR);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}
