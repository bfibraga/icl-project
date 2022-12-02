package src.astnodes.value.array;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.misc.*;
import src.type.TArray;
import src.type.TVoid;
import src.type.Type;
import src.value.Array;
import src.value.Cell;
import src.value.Value;

import java.util.List;

public class ASTArray implements ASTNode {

    private final List<ASTNode> fields;
    private Array value;

    public ASTArray(List<ASTNode> fields) {
        this.fields = fields;
        this.value = new Array();
    }

    @Override
    public Value eval(Environment<Value> e) {
        for (ASTNode node: this.fields) {
            Value nodeValue = node.eval(e);
            this.value.push(new Cell(nodeValue));
        }

        return this.value;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type previous = null;
        for (ASTNode field: this.fields) {
            Type type = field.typecheck(e);

            if (previous != null && !TypeFunctions.sameType(previous, type))
                throw new InvalidTypeConvertion(previous.show(), type.show(), this.getClass().getSimpleName());

            previous = type;
        }

        if (previous == null)
            previous = new TVoid();

        return new TArray(previous);
    }
}
