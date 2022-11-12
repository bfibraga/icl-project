package src.astnodes.operations.relational;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

public class ASTNot implements ASTNode {
    private ASTNode body;

    public ASTNot(ASTNode body){
        this.body = body;
    }

    @Override
    public int eval(Environment<Integer> e) {
        int value = this.body.eval(e);

        return value != 0 ? 0 : 1;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}
