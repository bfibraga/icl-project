package src.astnodes.value.struct;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TBool;
import src.type.TStruct;
import src.type.Type;
import src.value.Struct;
import src.value.Value;

import java.util.HashMap;
import java.util.Map;

public class ASTStruct extends TypeHolder implements ASTNode {

    private Map<String, ASTNode> fields;
    private Struct value;

    public ASTStruct(Map<String, ASTNode> fields) {
        this.fields = fields;
        this.value = new Struct();
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

    @Override
    public Type typecheck(Environment<Type> e) {
        Map<String, Type> fields = new HashMap<>();
        for (Map.Entry<String, ASTNode> entry: this.fields.entrySet()) {
            String id = entry.getKey();
            ASTNode node = entry.getValue();

            fields.put(id, node.typecheck(e));
        }

        Type result = new TStruct(fields);
        this.setType(result);
        return result;
    }
}
