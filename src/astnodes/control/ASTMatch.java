package src.astnodes.control;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Value;

import java.util.List;
import java.util.Map;

public class ASTMatch implements ASTNode {

    private ASTNode cond;
    private Map<List<Value>, ASTNode> cases;
    private ASTNode def;

    public ASTMatch(ASTNode cond, Map<List<Value>, ASTNode> cases, ASTNode def) {
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

        for (Map.Entry<List<Value>, ASTNode> entry : this.cases.entrySet()) {
            List<Value> valueList = entry.getKey();
            ASTNode caseNode = entry.getValue();

            if (valueList.contains(condValue)){
                return caseNode.eval(e);
            }
        }

        return this.def.eval(e);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        //TODO Implement compilation code for this astnode
    }
}
