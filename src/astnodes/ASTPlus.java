package src.astnodes;

public class ASTPlus implements ASTNode {

    private ASTNode l, r;

    public ASTPlus(ASTNode l, ASTNode r){
        this.l = l;
        this.r = r;
    }

    @Override
    public int eval() {
        int valueL = this.l.eval();
        int valueR = this.r.eval();

        return valueL + valueR;
    }
}

