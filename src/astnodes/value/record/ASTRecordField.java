package src.astnodes.value.record;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.*;
import src.value.Cell;
import src.value.Record;
import src.value.Value;

public class ASTRecordField implements ASTNode {

    private final ASTNode node;
    private final String field;

    public ASTRecordField(ASTNode node, String field) {
        this.node = node;
        this.field = field;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value value = this.node.eval(e);
        /*if (!value.isCell()){
            throw new InvalidTypes(value.show());
        }

        //TODO Add Record value check
        Value record = ((Cell)value).get();
        return ((Record) record).get(this.field);*/
        return ((Record) value).get(this.field);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public AbstractType typecheck(Environment<AbstractType> e) {
        AbstractType targetType = new TCell();
        AbstractType nodeType = this.node.typecheck(e);
        if (nodeType.sameType(targetType)){
            nodeType = ((TCell) nodeType).getType();
        }

        targetType = new TRecord();
        if (!nodeType.sameType(targetType))
            throw new InvalidTypeConvertion(nodeType.show(), targetType.show(), this.getClass().getSimpleName());

        return ((TRecord) nodeType).get(this.field);
    }
}
