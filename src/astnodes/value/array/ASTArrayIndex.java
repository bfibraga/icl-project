package src.astnodes.value.array;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Value;

public class ASTArrayIndex implements ASTNode {



    @Override
    public Value eval(Environment<Value> e) {
        return null;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}
