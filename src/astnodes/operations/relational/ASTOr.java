package src.astnodes.operations.relational;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
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
}
