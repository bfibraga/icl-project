package src.astnodes.operations.relational;

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

public class ASTNot implements ASTNode {
    private ASTNode body;

    public ASTNot(ASTNode body){
        this.body = body;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value valueBody = this.body.eval(e);
        if (!valueBody.isBoolean() || valueBody.isNumber()){
            throw new InvalidTypes(valueBody.show());
        }

        return new Bool( !((Bool)valueBody).getValue()) ;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        //TODO Implement compilation code for this astnode
    }

    @Override
    public AbstractType typecheck(Environment<AbstractType> e) {
        AbstractType targetAbstractType = new TBool();
        AbstractType bodyAbstractType = this.body.typecheck(e);

        if (!bodyAbstractType.sameType(targetAbstractType))
            throw new InvalidTypeConvertion(bodyAbstractType.show(), targetAbstractType.show(), this.getClass().getSimpleName());

        return targetAbstractType;
    }
}
