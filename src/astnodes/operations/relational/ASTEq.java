package src.astnodes.operations.relational;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.jvm.JVM;
import src.jvm.JVMValues;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.frame.BlockType;
import src.type.TBool;
import src.misc.TypeFunctions;
import src.type.Type;
import src.value.Bool;
import src.value.Int;
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
            return new Bool( ((Bool)valueL).getValue() == ((Bool)valueR).getValue() );
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
        this.l.compile(block, e);
        this.r.compile(block, e);
        block.emit(JVM.ISUB.toString());

        String label0 = block.gensym(BlockType.LABEL);
        String label1 = block.gensym(BlockType.LABEL);

        block.emit(String.format("%s %s", JVM.IFEQ, label0));
        block.emit(String.format("%s %s", JVM.SIPUSH, JVMValues.FALSE));
        block.emit(String.format("%s %s", JVM.GOTO, label1));
        block.emit(String.format("%s:", label0));
        block.emit(String.format("%s %s", JVM.SIPUSH, JVMValues.TRUE));
        block.emit(String.format("%s:", label1));
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type lType = this.l.typecheck(e);
        Type rType = this.r.typecheck(e);

        if (!TypeFunctions.sameType(lType, rType))
            throw new InvalidTypeConvertion(lType.show(), rType.show(), this.getClass().getSimpleName());

        return new TBool();
    }
}
