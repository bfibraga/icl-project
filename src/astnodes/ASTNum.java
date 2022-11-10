package src.astnodes;

public class ASTNum implements ASTNode {
    private int val;

    public ASTNum(int val){
        this.val = val;
    }

    @Override
    public int eval() {
        return this.val;
    }
}

