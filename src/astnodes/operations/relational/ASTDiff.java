package src.astnodes.operations.relational;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TBool;
import src.misc.TypeFunctions;
import src.type.Type;
import src.value.Bool;
import src.value.Int;
import src.value.Value;

public class ASTDiff implements ASTNode {
    private ASTNode l, r;

    public ASTDiff(ASTNode l, ASTNode r){
        this.l = l;
        this.r = r;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value valueL = this.l.eval(e);
        if (!valueL.isNumber() && !valueL.isBoolean() && !valueL.isString()){
            throw new InvalidTypes(valueL.show());
        }

        Value valueR = this.r.eval(e);
        if (!valueR.isNumber() && !valueR.isBoolean() && !valueL.isString()){
            throw new InvalidTypes(valueR.show());
        }

        if (valueL.isBoolean() && valueR.isBoolean()){
            return new Bool( ((Bool)valueL).getValue() != ((Bool)valueL).getValue() );
        }

        if (valueL.isNumber() && valueR.isNumber()){
            return new Bool(((Int)valueL).getValue() != ((Int)valueR).getValue()) ;
        }

        if (valueL.isString() && valueR.isString()){
            return new Bool(!valueL.show().equals(valueR.show())) ;
        }

        throw new InvalidTypes(valueL.getClass().getSimpleName());
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        //TODO Implement compilation code for this astnode
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type lType = this.l.typecheck(e);
        Type rType = this.r.typecheck(e);

        if (!TypeFunctions.sameType(lType, rType))
            throw new InvalidTypeConvertion(lType.show(), rType.show(), this.getClass().getSimpleName());

        return new TBool();
    }
}
