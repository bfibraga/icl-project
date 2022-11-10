package src.astnodes;

public class ASTPow implements ASTNode{
    private ASTNode l, r;

    public ASTPow(ASTNode l, ASTNode r){
        this.l = l;
        this.r = r;
    }

    @Override
    public int eval() {
        int valueL = this.l.eval();
        int valueR = this.r.eval();

        return (int) Math.pow(valueL, valueR);
    }
}
