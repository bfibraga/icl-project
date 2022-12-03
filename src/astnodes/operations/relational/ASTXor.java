package src.astnodes.operations.relational;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.TypeFunctions;
import src.type.TBool;
import src.type.Type;
import src.value.Bool;
import src.value.Value;

public class ASTXor extends TypeHolder implements ASTNode {

    private final ASTNode l, r;

    public ASTXor(ASTNode l, ASTNode r) {
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

        Bool lBool = (Bool)valueL;
        Bool rBool = (Bool)valueR;

        return new Bool(( (lBool.getValue() && !rBool.getValue()) || (!lBool.getValue() && rBool.getValue())));
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.l.compile(block, e);
        this.r.compile(block, e);
        block.emit(JVM.IXOR.toString());
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TBool();
        Type lType = this.l.typecheck(e);
        if (!TypeFunctions.sameType(lType, targetType))
            throw new InvalidTypeConvertion(lType.show(), targetType.show(), this.getClass().getSimpleName());


        Type rType = this.r.typecheck(e);
        if (!TypeFunctions.sameType(rType, targetType))
            throw new InvalidTypeConvertion(rType.show(), targetType.show(), this.getClass().getSimpleName());

        this.setType(targetType);
        return targetType;
    }
}
