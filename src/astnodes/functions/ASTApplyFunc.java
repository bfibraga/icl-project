package src.astnodes.functions;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.Type;
import src.value.Value;

public class ASTApplyFunc implements ASTNode {



    @Override
    public Value eval(Environment<Value> e) {
        return null;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        return null;
    }
}
