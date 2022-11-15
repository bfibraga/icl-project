package src.astnodes.operations.arithmetic;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Int;
import src.value.Value;

public class ASTPow implements ASTNode {
    
    private ASTNode l, r;

    public ASTPow(ASTNode l, ASTNode r){
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
            throw new InvalidTypes(valueL.show());
        }

        return new Int((int) Math.pow(((Int)valueL).getValue(), ((Int)valueR).getValue()));
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}
