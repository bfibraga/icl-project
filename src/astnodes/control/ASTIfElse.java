package src.astnodes.control;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
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
import src.value.Value;

public class ASTIfElse extends TypeHolder implements ASTNode {

    private ASTNode cond;
    private ASTNode thenBody;
    private ASTNode elseBody;

    public ASTIfElse(ASTNode cond, ASTNode thenBody, ASTNode elseBody){
        this.cond = cond;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value condValue = this.cond.eval(e);

        if (!condValue.isBoolean()){
            throw new InvalidTypes(condValue.show());
        }

        return ((Bool)condValue).getValue() ? this.thenBody.eval(e) : this.elseBody.eval(e);
    }

    //TODO Testing
    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.cond.compile(block, e);

        String label0 = block.gensym(BlockType.LABEL);
        String label1 = block.gensym(BlockType.LABEL);

        block.emit(String.format("%s %s", JVM.IFEQ, label0));
        this.thenBody.compile(block, e);
        block.emit(String.format("%s %s", JVM.GOTO, label1));
        block.emit(String.format("%s: ", label0));
        this.elseBody.compile(block, e);
        block.emit(String.format("%s: ", label1));
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TBool();
        Type condType = this.cond.typecheck(e);

        if (!TypeFunctions.sameType(condType, targetType))
            throw new InvalidTypeConvertion(condType.show(), targetType.show(), this.getClass().getSimpleName());

        Type thenType = this.thenBody.typecheck(e);
        Type elseType = this.elseBody.typecheck(e);

        if (!TypeFunctions.sameType(thenType, elseType))
            throw new InvalidTypeConvertion(thenType.show(), elseType.show(), this.getClass().getSimpleName());

        this.setType(elseType);
        return elseType; //Or thenType
    }
}
