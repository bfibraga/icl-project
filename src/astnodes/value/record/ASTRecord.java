package src.astnodes.value.record;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Record;
import src.value.Value;

import java.util.Map;

public class ASTRecord implements ASTNode {

    private Map<String, ASTNode> fields;
    private Record value;

    public ASTRecord(Map<String, ASTNode> fields) {
        this.fields = fields;
        this.value = new Record();
    }

    @Override
    public Value eval(Environment<Value> e) {
        for (Map.Entry<String, ASTNode> entry: this.fields.entrySet()) {
            String id = entry.getKey();
            ASTNode node = entry.getValue();

            Value nodeValue = node.eval(e);
            this.value.set(id, nodeValue);
        }
        return this.value;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}