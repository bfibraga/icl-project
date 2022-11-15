package src.astnodes;

import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Value;

public class ASTSeq implements ASTNode {

    private final ASTNode l, r;

    public ASTSeq(ASTNode l, ASTNode r){
        this.l = l;
        this.r = r;
    }

    @Override
    public Value eval(Environment<Value> e) {
        //TODO Test
        this.l.eval(e);
        return this.r.eval(e);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}
