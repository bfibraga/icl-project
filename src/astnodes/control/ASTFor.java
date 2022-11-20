package src.astnodes.control;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Bool;
import src.value.Value;

public class ASTFor implements ASTNode {

    private final int loopTimes;
    private final ASTNode body;

    public ASTFor(int loopTimes, ASTNode body) {
        this.loopTimes = loopTimes;
        this.body = body;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value value = new Bool(true);
        for (int l = 0; l < this.loopTimes; l++) {
            value = this.body.eval(e);
        }
        return value;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}
