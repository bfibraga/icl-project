package src.astnodes.binding;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TCell;
import src.type.Type;
import src.value.Cell;
import src.value.Value;

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

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type argType = this.arg.typecheck(e);
        return new TCell(argType);
    }
}
