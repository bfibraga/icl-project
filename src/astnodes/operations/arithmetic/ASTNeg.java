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

public class ASTNeg extends TypeHolder implements ASTNode {

    private ASTNode exp;

    public ASTNeg(ASTNode exp){
        this.exp = exp;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value valueExp = this.exp.eval(e);
        if (!valueExp.isNumber() || valueExp.isBoolean()){
            throw new InvalidValueConvertionException(valueExp.show());
        }

        return new Int(-1 * ((Int) valueExp).getValue());
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.exp.compile(block, e);
        block.emit(JVM.INEG.toString());
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TInt();
        Type expType = this.exp.typecheck(e);
        if (!TypeFunctions.sameType(expType, targetType))
            throw new InvalidTypeConvertionException(expType.show(), targetType.show(), this.getClass().getSimpleName());

        this.setType(targetType);
        return targetType;
    }
}
