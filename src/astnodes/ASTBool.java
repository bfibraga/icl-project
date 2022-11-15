package src.astnodes;

import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Bool;
import src.value.Int;
import src.value.Value;

public class ASTBool implements ASTNode {

    private final boolean val;

    public ASTBool(boolean val){
        this.val = val;
    }

    @Override
    public Value eval(Environment<Value> e) {
        return new Bool(this.val);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}
