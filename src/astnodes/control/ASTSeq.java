package src.astnodes.control;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.Type;
import src.value.Value;

public class ASTSeq implements ASTNode {

    private final ASTNode l, r;

    public ASTSeq(ASTNode l, ASTNode r){
        this.l = l;
        this.r = r;
    }

    @Override
    public Value eval(Environment<Value> e) {
        this.l.eval(e);
        return this.r.eval(e);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        this.l.typecheck(e);
        return this.r.typecheck(e);
    }
}
