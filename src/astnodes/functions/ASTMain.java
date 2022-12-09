package src.astnodes.functions;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.Type;
import src.value.Value;

public class ASTMain extends TypeHolder implements ASTNode {

    private final ASTNode mainNode;

    public ASTMain(ASTNode mainNode) {
        this.mainNode = mainNode;
    }

    @Override
    public Value eval(Environment<Value> e) {
        return this.mainNode.eval(e);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.mainNode.compile(block, e);
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        return this.mainNode.typecheck(e);
    }
}
