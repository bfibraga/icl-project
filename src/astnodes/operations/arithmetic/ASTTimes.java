package src.astnodes.operations.arithmetic;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypeConvertionException;
import src.exceptions.InvalidValueConvertionException;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TInt;
import src.misc.TypeFunctions;
import src.type.Type;
import src.value.Int;
import src.value.Value;

public class ASTTimes extends TypeHolder implements ASTNode {
    private ASTNode l, r;

    public ASTTimes(ASTNode l, ASTNode r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value valueL = this.l.eval(e);
        if (!valueL.isNumber() || valueL.isBoolean()){
            throw new InvalidValueConvertionException(valueL.show());
        }

        Value valueR = this.r.eval(e);
        if (!valueR.isNumber() || valueR.isBoolean()){
            throw new InvalidValueConvertionException(valueL.show());
        }

        return new Int(((Int)valueL).getValue() * ((Int)valueR).getValue()) ;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.l.compile(block, e);
        this.r.compile(block, e);
        block.emit(JVM.IMUL.toString());
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TInt();
        Type lType = this.l.typecheck(e);
        if (!TypeFunctions.sameType(lType, targetType))
            throw new InvalidTypeConvertionException(lType.show(), targetType.show(), this.getClass().getSimpleName());


        Type rType = this.r.typecheck(e);
        if (!TypeFunctions.sameType(rType, targetType))
            throw new InvalidTypeConvertionException(rType.show(), targetType.show(), this.getClass().getSimpleName());

        this.setType(targetType);
        return targetType;
    }
}
