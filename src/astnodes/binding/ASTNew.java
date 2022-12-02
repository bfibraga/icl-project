package src.astnodes.binding;

import src.astnodes.ASTNode;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.frame.BlockType;
import src.misc.frame.RefBlock;
import src.type.TCell;
import src.misc.TypeFunctions;
import src.type.Type;
import src.value.Cell;
import src.value.Value;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ASTNew implements ASTNode {

    private final ASTNode arg;

    public ASTNew(ASTNode arg){
        this.arg = arg;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value valueArg = this.arg.eval(e);
        return new Cell(valueArg);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        RefBlock refBlock = block.newRefBlock();

        String type = refBlock.getType();
        String refType = refBlock.gensym();

        block.emit(String.format("%s %s", JVM.NEW, refType));
        block.emit(JVM.DUP.toString());
        block.emit(String.format("%s %s/<init>()V", JVM.INVOKESPECIAL, refType));
        block.emit(JVM.DUP.toString());

        this.arg.compile(block, e);
        block.emit(String.format("%s %s/v %s", JVM.PUTFIELD, refType, type));
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type argType = this.arg.typecheck(e);
        return new TCell(argType);
    }
}
