package src.astnodes.value.primitives;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TStr;
import src.type.Type;
import src.value.Str;
import src.value.Value;

public class ASTStr implements ASTNode {

    private final String value;

    public ASTStr(String value) {
        this.value = value;
    }

    @Override
    public Value eval(Environment<Value> e) {
        return new Str(this.value);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        return new TStr();
    }
}
