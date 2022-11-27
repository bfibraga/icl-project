package src.astnodes.operations.relational;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TBool;
import src.type.TInt;
import src.type.AbstractType;
import src.value.Bool;
import src.value.Int;
import src.value.Value;

public class ASTGrtEq implements ASTNode {
    private ASTNode l, r;

    public ASTGrtEq(ASTNode l, ASTNode r){
        this.l = l;
        this.r = r;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value valueL = this.l.eval(e);
        if (!valueL.isNumber() || valueL.isBoolean()){
            throw new InvalidTypes(valueL.show());
        }

        Value valueR = this.r.eval(e);
        if (!valueR.isNumber() || valueR.isBoolean()){
            throw new InvalidTypes(valueR.show());
        }

        return new Bool(((Int)valueL).getValue() >= ((Int)valueR).getValue()) ;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        //TODO Implement compilation code for this astnode
    }

    @Override
    public AbstractType typecheck(Environment<AbstractType> e) {
        AbstractType targetAbstractType = new TInt();
        AbstractType lAbstractType = this.l.typecheck(e);
        if (!lAbstractType.sameType(targetAbstractType))
            throw new InvalidTypeConvertion(lAbstractType.show(), targetAbstractType.show(), this.getClass().getSimpleName());


        AbstractType rAbstractType = this.r.typecheck(e);
        if (!rAbstractType.sameType(targetAbstractType))
            throw new InvalidTypeConvertion(rAbstractType.show(), targetAbstractType.show(), this.getClass().getSimpleName());

        return new TBool();
    }
}
