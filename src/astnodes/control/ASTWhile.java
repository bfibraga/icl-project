package src.astnodes.control;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

public class ASTWhile implements ASTNode {

    private ASTNode cond;
    private ASTNode body;

    public ASTWhile(ASTNode cond, ASTNode body){
        this.body = body;
        this.cond = cond;
    }

    @Override
    public int eval(Environment<Integer> e) {
        int condValue = this.cond.eval(e);
        if (condValue != 0){
            body.eval(e);
            return this.eval(e);
        } else {
            return condValue;
        }
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        //TODO Implement compilation code for this astnode
    }
}
