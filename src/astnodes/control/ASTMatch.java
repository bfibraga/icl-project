package src.astnodes.control;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Value;

import java.util.Map;

public class ASTMatch implements ASTNode {

    private ASTNode cond;
    private Map<Value, ASTNode> cases;
    private ASTNode def;

    public ASTMatch(ASTNode cond, Map<Value, ASTNode> cases, ASTNode def) {
        this.cond = cond;
        this.cases = cases;
        this.def = def;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value condValue = this.cond.eval(e);

        if (!condValue.isNumber()){
            throw new InvalidTypes(condValue.show());
        }

        return this.cases.containsKey(condValue) ? this.cases.get(condValue).eval(e) : this.def.eval(e);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        //TODO Implement compilation code for this astnode
    }
}
