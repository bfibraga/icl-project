package src.astnodes.operations.relational;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

public class ASTLwtEq implements ASTNode {
    private ASTNode l, r;

    public ASTLwtEq(ASTNode l, ASTNode r){
        this.l = l;
        this.r = r;
    }

    @Override
    public int eval(Environment<Integer> e) {
        int valueL = this.l.eval(e);
        int valueR = this.r.eval(e);

        return valueL <= valueR ? 1 : 0;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}
