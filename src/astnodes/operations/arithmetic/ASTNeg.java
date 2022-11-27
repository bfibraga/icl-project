package src.astnodes.operations.arithmetic;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TInt;
import src.type.AbstractType;
import src.value.Int;
import src.value.Value;

public class ASTNeg implements ASTNode {

    private ASTNode exp;

    public ASTNeg(ASTNode exp){
        this.exp = exp;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value valueExp = this.exp.eval(e);
        if (!valueExp.isNumber() || valueExp.isBoolean()){
            throw new InvalidTypes(valueExp.show());
        }

        return new Int(-1 * ((Int) valueExp).getValue());
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.exp.compile(block, e);
        block.emit(JVM.INEG.toString());
    }

    @Override
    public AbstractType typecheck(Environment<AbstractType> e) {
        AbstractType targetAbstractType = new TInt();
        AbstractType expAbstractType = this.exp.typecheck(e);
        if (!expAbstractType.sameType(targetAbstractType))
            throw new InvalidTypeConvertion(expAbstractType.show(), targetAbstractType.show(), this.getClass().getSimpleName());

        return targetAbstractType;
    }
}
