package src.astnodes.operations.relational;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypeConvertionException;
import src.exceptions.InvalidValueConvertionException;
import src.jvm.JVM;
import src.jvm.JVMValues;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.frame.BlockType;
import src.type.*;
import src.misc.TypeFunctions;
import src.value.Bool;
import src.value.Int;
import src.value.Value;

public class ASTLwt extends TypeHolder implements ASTNode {
    private ASTNode l, r;

    public ASTLwt(ASTNode l, ASTNode r){
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
            throw new InvalidValueConvertionException(valueR.show());
        }

        return new Bool(((Int)valueL).getValue() < ((Int)valueR).getValue()) ;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.l.compile(block, e);
        this.r.compile(block, e);
        block.emit(JVM.ISUB.toString());

        String label0 = block.gensym(BlockType.LABEL);
        String label1 = block.gensym(BlockType.LABEL);

        block.emit(String.format("%s %s", JVM.IFLT, label0));
        block.emit(String.format("%s %s", JVM.SIPUSH, JVMValues.FALSE));
        block.emit(String.format("%s %s", JVM.GOTO, label1));
        block.emit(String.format("%s:", label0));
        block.emit(String.format("%s %s", JVM.SIPUSH, JVMValues.TRUE));
        block.emit(String.format("%s:", label1));
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

        this.setType(new TBool());
        return new TBool();
    }
}
