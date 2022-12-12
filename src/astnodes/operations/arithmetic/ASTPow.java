package src.astnodes.operations.arithmetic;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypeConvertionException;
import src.exceptions.InvalidValueConvertionException;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.frame.BlockType;
import src.type.TInt;
import src.misc.TypeFunctions;
import src.type.Type;
import src.value.Int;
import src.value.Value;

public class ASTPow extends TypeHolder implements ASTNode {
    
    private ASTNode l, r;

    public ASTPow(ASTNode l, ASTNode r){
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

        return new Int((int) Math.pow(((Int)valueL).getValue(), ((Int)valueR).getValue()));
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        String powLoopLabel = block.gensym(BlockType.LABEL);
        String powZeroLabel = block.gensym(BlockType.LABEL);
        String powEndLabel = block.gensym(BlockType.LABEL);

        //If r is 0 -> goto Zero label
        //if r is 1 -> goto

        this.r.compile(block, e);
        block.emit(JVM.DUP.toString());
        block.emit(String.format("%s %s", JVM.IFEQ, powZeroLabel));
        block.emit(String.format("%s_%d",JVM.ISTORE, 1));

        this.l.compile(block, e);

        //Loop compile code
        block.emit(String.format("%s: ", powLoopLabel));
        block.emit(String.format("%s_%d",JVM.ISTORE, 2));

        //Counter subtraction and verification
        block.emit(String.format("%s_%d",JVM.ILOAD, 1));
        block.emit(String.format("%s %s", JVM.SIPUSH, 1));
        block.emit(JVM.ISUB.toString());
        block.emit(JVM.DUP.toString());
        block.emit(String.format("%s_%d",JVM.ISTORE, 1));
        block.emit(String.format("%s %s", JVM.IFEQ, powEndLabel));

        block.emit(String.format("%s_%d",JVM.ILOAD, 2));
        block.emit(JVM.DUP.toString());
        block.emit(JVM.IMUL.toString());
        block.emit(String.format("%s %s", JVM.GOTO, powLoopLabel));

        //Zero compile code
        block.emit(String.format("%s: ", powZeroLabel));
        block.emit(JVM.POP.toString());
        block.emit(String.format("%s %s", JVM.SIPUSH, 1));

        block.emit(String.format("%s: ", powEndLabel));
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
