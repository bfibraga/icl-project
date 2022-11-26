package src.astnodes.functions;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TClosure;
import src.type.Type;
import src.value.Closure;
import src.value.Value;

public class ASTFunction implements ASTNode {

    private final String id;
    private final ASTNode body;

    public ASTFunction(String id, ASTNode body) {
        this.id = id;
        this.body = body;
    }

    @Override
    public Value eval(Environment<Value> e) {
        return new Closure(this.id, this.body, e);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        return new TClosure();
    }
}
