package src.astnodes.binding;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypeConvertionException;
import src.exceptions.InvalidValueConvertionException;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TCell;
import src.misc.TypeFunctions;
import src.type.Type;
import src.value.Cell;
import src.value.Value;

public class ASTRef extends TypeHolder implements ASTNode {

    private final ASTNode node;

    public ASTRef(ASTNode node) {
        this.node = node;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value value = this.node.eval(e);
        if (!value.isCell()){
            throw new InvalidValueConvertionException(value.show());
        }

        return ((Cell) value).get();
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.node.compile(block, e);

        Type type = this.getType();
        Type nestedType = new TCell(type);

        String typename = nestedType.jvmType();
        String refTypename = type.jvmType();
        block.emit(String.format("%s %s/v %s", JVM.GETFIELD, typename, refTypename.contains("Ref_of_") ?
                "L" + refTypename + ";" :
                refTypename ));
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TCell();
        Type nodeType = this.node.typecheck(e);
        if (!TypeFunctions.sameType(nodeType, targetType))
            throw new InvalidTypeConvertionException(nodeType.show(), targetType.show(), this.getClass().getSimpleName());

        Type result = ((TCell) nodeType).getType();
        this.setType(result);
        return result;
    }
}
