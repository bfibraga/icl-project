package src.astnodes.control;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypeConvertionException;
import src.exceptions.InvalidValueConvertionException;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.frame.BlockType;
import src.type.TBool;
import src.misc.TypeFunctions;
import src.type.Type;
import src.value.Bool;
import src.value.Value;

public class ASTWhile extends TypeHolder implements ASTNode {

    private ASTNode cond;
    private ASTNode body;

    public ASTWhile(ASTNode cond, ASTNode body){
        this.body = body;
        this.cond = cond;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value condValue = this.cond.eval(e);

        if (!condValue.isBoolean()){
            throw new InvalidValueConvertionException(condValue.show());
        }

        if (((Bool)condValue).getValue()){
            body.eval(e);
            return this.eval(e);
        } else {
            return condValue;
        }
    }

    //TODO Testing
    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        String labelStart = block.gensym(BlockType.LABEL);
        String labelTrue = block.gensym(BlockType.LABEL);
        String labelFalse = block.gensym(BlockType.LABEL);

        System.out.println(labelStart + "\n" + labelTrue + "\n" + labelFalse);

        block.emit(String.format("%s:", labelStart));
        this.cond.compile(block, e);
        block.emit(String.format("%s %s", JVM.IFEQ, labelFalse));

        block.emit(String.format("%s:", labelTrue));
        this.body.compile(block, e);

        //TODO Not working with pop op, has a problem of 1 != 2 on stack.
        //block.emit(JVM.POP.toString());

        block.emit(String.format("%s %s", JVM.GOTO, labelStart));
        block.emit(String.format("%s: ", labelFalse));
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TBool();
        Type condType = this.cond.typecheck(e);

        if (!TypeFunctions.sameType(condType, targetType))
            throw new InvalidTypeConvertionException(condType.show(), targetType.show(), this.getClass().getSimpleName());

        Type result = this.body.typecheck(e);
        this.setType(result);
        return result;
    }
}
