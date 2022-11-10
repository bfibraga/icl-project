package src.astnodes;

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
}
