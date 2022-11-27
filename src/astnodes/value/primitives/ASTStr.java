package src.astnodes.value.primitives;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TStr;
import src.type.AbstractType;
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
    public AbstractType typecheck(Environment<AbstractType> e) {
        return new TStr();
    }
}
