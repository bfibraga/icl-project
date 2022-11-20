package src.astnodes.operations.relational;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Bool;
import src.value.Int;
import src.value.Str;
import src.value.Value;

public class ASTEq implements ASTNode {
    private ASTNode l, r;

    public ASTEq(ASTNode l, ASTNode r){
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
            return new Bool( ((Bool)valueL).getValue() == ((Bool)valueL).getValue() );
        }

        if (valueL.isNumber() && valueR.isNumber()){
            return new Bool(((Int)valueL).getValue() == ((Int)valueR).getValue()) ;
        }

        if (valueL.isString() && valueR.isString()){
            return new Bool(valueL.show().equals(valueR.show())) ;
        }

        throw new InvalidTypes(valueL.getClass().getSimpleName());
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        //TODO Implement compilation code for this astnode
    }
}
