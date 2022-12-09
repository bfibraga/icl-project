package src.astnodes.functions;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TVoid;
import src.type.Type;
import src.value.Int;
import src.value.Value;

public class ASTExit extends TypeHolder implements ASTNode {

    public ASTExit(){}

    @Override
    public Value eval(Environment<Value> e) {
        System.exit(0);
        return new Int(1);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {}

    @Override
    public Type typecheck(Environment<Type> e) {
        return this.getType();
    }
}
