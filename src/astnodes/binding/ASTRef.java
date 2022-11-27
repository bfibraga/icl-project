package src.astnodes.binding;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TCell;
import src.type.AbstractType;
import src.value.Cell;
import src.value.Value;

public class ASTRef implements ASTNode {

    private final ASTNode node;

    public ASTRef(ASTNode node) {
        this.node = node;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value value = this.node.eval(e);
        if (!value.isCell()){
            throw new InvalidTypes(value.show());
        }

        return ((Cell) value).get();
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public AbstractType typecheck(Environment<AbstractType> e) {
        AbstractType targetAbstractType = new TCell();
        AbstractType nodeAbstractType = this.node.typecheck(e);
        if (!nodeAbstractType.sameType(targetAbstractType))
            throw new InvalidTypeConvertion(nodeAbstractType.show(), targetAbstractType.show(), this.getClass().getSimpleName());

        return ((TCell) nodeAbstractType).getType();
    }
}
