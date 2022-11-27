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

    @Override
    public AbstractType typecheck(Environment<AbstractType> e) {
        AbstractType targetAbstractType = new TBool();
        AbstractType condAbstractType = this.cond.typecheck(e);

        if (!condAbstractType.sameType(targetAbstractType))
            throw new InvalidTypeConvertion(condAbstractType.show(), targetAbstractType.show(), this.getClass().getSimpleName());

        AbstractType thenAbstractType = this.thenBody.typecheck(e);
        AbstractType elseAbstractType = this.elseBody.typecheck(e);

        if (!thenAbstractType.sameType(elseAbstractType))
            throw new InvalidTypeConvertion(thenAbstractType.show(), elseAbstractType.show(), this.getClass().getSimpleName());

        return elseAbstractType; //Or elseType
    }
}
