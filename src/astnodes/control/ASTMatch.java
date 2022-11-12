package src.astnodes.control;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

import java.util.Map;

public class ASTMatch implements ASTNode {

    private ASTNode cond;
    private Map<Integer, ASTNode> cases;
    private ASTNode def;

    public ASTMatch(ASTNode cond, Map<Integer, ASTNode> cases, ASTNode def) {
        this.cond = cond;
        this.cases = cases;
        this.def = def;
    }

    @Override
    public int eval(Environment<Integer> e) {
        int condValue = this.cond.eval(e);
        return this.cases.containsKey(condValue) ? this.cases.get(condValue).eval(e) : this.def.eval(e);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}
