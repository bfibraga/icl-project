package src.astnodes;

import src.misc.Environment;

public class ASTId implements ASTNode {

    private final String id;

    public ASTId(String id){
        this.id = id;
    }

    @Override
    public int eval(Environment<Integer> e) {
        return e.find(this.id);
    }
}
