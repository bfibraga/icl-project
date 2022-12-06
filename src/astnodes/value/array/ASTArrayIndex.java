package src.astnodes.value.array;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypeConvertionException;
import src.exceptions.InvalidValueConvertionException;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.*;
import src.misc.TypeFunctions;
import src.value.Array;
import src.value.Int;
import src.value.Value;

public class ASTArrayIndex extends TypeHolder implements ASTNode {

    private final ASTNode node;
    private final ASTNode index;

    public ASTArrayIndex(ASTNode node, ASTNode index) {
        this.node = node;
        this.index = index;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value array = this.node.eval(e);
        if (!array.isArray()){
            throw new InvalidValueConvertionException(array.show());
        }

        Value indexValue = this.index.eval(e);
        if (!indexValue.isNumber()){
            throw new InvalidValueConvertionException(indexValue.show());
        }

        int index = ((Int)indexValue).getValue();
        return ((Array)array).get(index);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TArray();
        Type nodeType = this.node.typecheck(e);
        if (!TypeFunctions.sameType(nodeType, targetType))
            throw new InvalidTypeConvertionException(nodeType.show(), targetType.show(), this.getClass().getSimpleName());

        targetType = new TInt();
        Type indexType = this.index.typecheck(e);
        if (!TypeFunctions.sameType(indexType, targetType))
            throw new InvalidTypeConvertionException(nodeType.show(), targetType.show(), this.getClass().getSimpleName());

        this.setType(new TCell());
        return new TCell();
    }
}
