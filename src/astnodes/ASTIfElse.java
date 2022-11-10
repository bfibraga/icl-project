package src.astnodes;

import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

public class ASTIfElse implements ASTNode {

    private ASTNode cond;
    private ASTNode thenBody;
    private ASTNode elseBody;

    public ASTIfElse(ASTNode cond, ASTNode thenBody, ASTNode elseBody){
        this.cond = cond;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    @Override
    public int eval(Environment<Integer> e) {
        int condValue = this.cond.eval(e);
        return condValue != 0 ? this.thenBody.eval(e) : this.elseBody.eval(e);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}
