package src.astnodes.operations.arithmetic;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypes;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Int;
import src.value.Value;

public class ASTTimes implements ASTNode {
    private ASTNode l, r;

    public ASTTimes(ASTNode l, ASTNode r) {
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

        return new Int(((Int)valueL).getValue() * ((Int)valueR).getValue()) ;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.l.compile(block, e);
        this.r.compile(block, e);
        block.emit(JVM.IMUL.toString());
    }
}
