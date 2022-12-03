package src.astnodes.value.struct;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypeConvertion;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.TypeFunctions;
import src.type.*;
import src.value.Struct;
import src.value.Value;

public class ASTStructField extends TypeHolder implements ASTNode {

    private final ASTNode node;
    private final String field;

    public ASTStructField(ASTNode node, String field) {
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
        return ((Struct) value).get(this.field);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TCell();
        Type nodeType = this.node.typecheck(e);
        if (TypeFunctions.sameType(nodeType, targetType)){
            nodeType = ((TCell) nodeType).getType();
        }

        targetType = new TStruct();
        if (!TypeFunctions.sameType(nodeType, targetType))
            throw new InvalidTypeConvertion(nodeType.show(), targetType.show(), this.getClass().getSimpleName());

        Type result = ((TStruct) nodeType).get(this.field);
        this.setType(result);
        return result;
    }
}
