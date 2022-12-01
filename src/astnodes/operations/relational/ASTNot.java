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
import src.value.Value;

public class ASTNot implements ASTNode {
    private ASTNode body;

    public ASTNot(ASTNode body){
        this.body = body;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value valueBody = this.body.eval(e);
        if (!valueBody.isBoolean() || valueBody.isNumber()){
            throw new InvalidTypes(valueBody.show());
        }

        return new Bool( !((Bool)valueBody).getValue()) ;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.body.compile(block, e);

        String label0 = block.gensym(BlockType.LABEL);
        String label1 = block.gensym(BlockType.LABEL);

        block.emit(String.format("%s %s", JVM.IFEQ, label0));
        block.emit(String.format("%s %s", JVM.SIPUSH, JVMValues.TRUE));
        block.emit(String.format("%s %s", JVM.GOTO, label1));
        block.emit(String.format("%s:", label1));
        block.emit(String.format("%s %s", JVM.SIPUSH, JVMValues.FALSE));
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TBool();
        Type bodyType = this.body.typecheck(e);

        if (!TypeFunctions.sameType(bodyType, targetType))
            throw new InvalidTypeConvertion(bodyType.show(), targetType.show(), this.getClass().getSimpleName());

        return targetType;
    }
}
