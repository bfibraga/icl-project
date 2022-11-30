package src.astnodes.operations.relational;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TBool;
import src.type.TInt;
import src.misc.TypeFunctions;
import src.type.Type;
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
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TInt();
        Type lType = this.l.typecheck(e);
        if (!TypeFunctions.sameType(lType, targetType))
            throw new InvalidTypeConvertion(lType.show(), targetType.show(), this.getClass().getSimpleName());


        Type rType = this.r.typecheck(e);
        if (!TypeFunctions.sameType(rType, targetType))
            throw new InvalidTypeConvertion(rType.show(), targetType.show(), this.getClass().getSimpleName());

        return new TBool();
    }
}
