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

public class ASTOr implements ASTNode {
    private ASTNode l, r;

    public ASTOr(ASTNode l, ASTNode r){
        this.l = l;
        this.r = r;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value valueL = this.l.eval(e);
        if (!valueL.isBoolean() || valueL.isNumber()){
            throw new InvalidTypes(valueL.show());
        }

        Value valueR = this.r.eval(e);
        if (!valueR.isBoolean() || valueR.isNumber()){
            throw new InvalidTypes(valueR.show());
        }

        return new Bool(((Bool)valueL).getValue() || ((Bool)valueR).getValue()) ;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        //TODO Implement compilation code for this astnode
    }

    @Override
    public AbstractType typecheck(Environment<AbstractType> e) {
        AbstractType targetAbstractType = new TBool();
        AbstractType lAbstractType = this.l.typecheck(e);
        if (!lAbstractType.sameType(targetAbstractType))
            throw new InvalidTypeConvertion(lAbstractType.show(), targetAbstractType.show(), this.getClass().getSimpleName());


        AbstractType rAbstractType = this.r.typecheck(e);
        if (!rAbstractType.sameType(targetAbstractType))
            throw new InvalidTypeConvertion(rAbstractType.show(), targetAbstractType.show(), this.getClass().getSimpleName());

        return targetAbstractType;
    }
}
