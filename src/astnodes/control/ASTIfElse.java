package src.astnodes.control;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Bool;
import src.value.Value;

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
    public Value eval(Environment<Value> e) {
        Value condValue = this.cond.eval(e);

        if (!condValue.isBoolean()){
            throw new InvalidTypes(condValue.show());
        }

        return ((Bool)condValue).getValue() ? this.thenBody.eval(e) : this.elseBody.eval(e);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        //TODO Implement compilation code for this astnode
    }
}
