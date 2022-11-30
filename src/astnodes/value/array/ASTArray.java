package src.astnodes.value.array;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TArray;
import src.type.Type;
import src.value.Array;
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
            this.value.push(nodeValue);
        }

        return this.value;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        return new TArray();
    }
}
