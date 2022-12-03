package src.astnodes.value.array;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TInt;
import src.type.Type;
import src.value.Array;
import src.value.Int;
import src.value.Value;

public class ASTArrayLen extends TypeHolder implements ASTNode {

    private final String id;

    public ASTArrayLen(String id) {
        this.id = id;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value expValue = e.find(this.id);

        return new Int(((Array)expValue).length());
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type result = new TInt();
        this.setType(result);
        return result;
    }
}
