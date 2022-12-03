package src.astnodes.control;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.frame.BlockType;
import src.type.TBool;
import src.misc.TypeFunctions;
import src.type.Type;
import src.value.Bool;
import src.value.Str;
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
            throw new InvalidTypes(condValue.show());
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
        String label0 = block.gensym(BlockType.LABEL);
        block.emit(String.format("%s: ", label0));
        this.cond.compile(block, e);
        String label1 = block.gensym(BlockType.LABEL);
        block.emit(String.format("%s %s", JVM.IFEQ, label1));
        this.body.compile(block, e);
        block.emit(JVM.POP.toString());
        block.emit(String.format("%s %s", JVM.GOTO, label0));
        block.emit(String.format("%s: ", label1));
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TBool();
        Type condType = this.cond.typecheck(e);

        if (!TypeFunctions.sameType(condType, targetType))
            throw new InvalidTypeConvertion(condType.show(), targetType.show(), this.getClass().getSimpleName());

        Type result = this.body.typecheck(e);
        this.setType(result);
        return result;
    }
}
