package src.astnodes.control;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TBool;
import src.type.AbstractType;
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

    @Override
    public AbstractType typecheck(Environment<AbstractType> e) {
        AbstractType targetAbstractType = new TBool();
        AbstractType condAbstractType = this.cond.typecheck(e);

        if (!condAbstractType.sameType(targetAbstractType))
            throw new InvalidTypeConvertion(condAbstractType.show(), targetAbstractType.show(), this.getClass().getSimpleName());

        return this.body.typecheck(e);
    }
}
