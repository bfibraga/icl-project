package src.astnodes.value.array;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TArray;
import src.type.AbstractType;
import src.value.Array;
import src.value.Cell;
import src.value.Value;

public class ASTArrayIndex implements ASTNode {

    private final ASTNode node;
    private final int index;

    public ASTArrayIndex(ASTNode node, int index) {
        this.node = node;
        this.index = index;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value value = this.node.eval(e);
        if (!value.isCell()){
            throw new InvalidTypes(value.show());
        }

        //TODO Add Record value check
        Value record = ((Cell)value).get();
        return ((Array) record).get(this.index);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public AbstractType typecheck(Environment<AbstractType> e) {
        AbstractType targetAbstractType = new TArray();
        AbstractType nodeAbstractType = this.node.typecheck(e);
        if (!nodeAbstractType.sameType(targetAbstractType))
            throw new InvalidTypeConvertion(nodeAbstractType.show(), targetAbstractType.show(), this.getClass().getSimpleName());

        return nodeAbstractType;
    }
}
