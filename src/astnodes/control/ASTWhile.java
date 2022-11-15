package src.astnodes.control;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Bool;
import src.value.Value;

public class ASTWhile implements ASTNode {

    private ASTNode cond;
    private ASTNode body;

    public ASTWhile(ASTNode cond, ASTNode body){
        this.body = body;
        this.cond = cond;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value condValue = this.cond.eval(e);

        if (!condValue.isBoolean()){
            throw new InvalidTypes(condValue.show());
        }

        if (((Bool)condValue).getValue()){
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
