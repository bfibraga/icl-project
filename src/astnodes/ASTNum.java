package src.astnodes;

import src.misc.Environment;

public class ASTNum implements ASTNode {
    private int val;

    public ASTNum(int val){
        this.val = val;
    }

    @Override
    public int eval(Environment<Integer> e) {
        return this.val;
    }
}

