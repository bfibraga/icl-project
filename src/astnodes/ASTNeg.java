package src.astnodes;

public class ASTNeg implements ASTNode {

    private ASTNode exp;

    public ASTNeg(ASTNode exp){
        this.exp = exp;
    }

    @Override
    public int eval() {
        return -1 * exp.eval();
    }
}
