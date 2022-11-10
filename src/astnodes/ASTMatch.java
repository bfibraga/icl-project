package src.astnodes;

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
}
