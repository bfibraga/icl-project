package src.astnodes.control;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TBool;
import src.type.AbstractType;
import src.value.Value;

import java.util.List;
import java.util.Map;

public class ASTMatch implements ASTNode {

    private ASTNode cond;
    private Map<List<ASTNode>, ASTNode> cases;
    private ASTNode def;

    public ASTMatch(ASTNode cond, Map<List<ASTNode>, ASTNode> cases, ASTNode def) {
        this.cond = cond;
        //TODO Check multiple equal cases.

        this.cases = cases;
        this.def = def;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value condValue = this.cond.eval(e);

        if (!condValue.isNumber()){
            throw new InvalidTypes(condValue.show());
        }

        for (Map.Entry<List<ASTNode>, ASTNode> entry : this.cases.entrySet()) {
            List<ASTNode> astNodeList = entry.getKey();
            ASTNode caseNode = entry.getValue();

            for (ASTNode node: astNodeList) {
                Value value = node.eval(e);

                if (value.equals(condValue)){
                    return caseNode.eval(e);
                }
            }
        }

        return this.def.eval(e);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        //TODO Implement compilation code for this astnode
    }

    @Override
    public AbstractType typecheck(Environment<AbstractType> e) {
        AbstractType targetAbstractType = new TBool();
        AbstractType condAbstractType = this.cond.typecheck(e);

        if (!condAbstractType.sameType(new TBool()))
            throw new InvalidTypeConvertion(condAbstractType.show(), targetAbstractType.show(), this.getClass().getSimpleName());

        AbstractType defAbstractType = this.def.typecheck(e);
        for (Map.Entry<List<ASTNode>, ASTNode> entry : this.cases.entrySet()) {
            List<ASTNode> astNodeList = entry.getKey();
            ASTNode caseNode = entry.getValue();


            for (ASTNode node: astNodeList) {
                AbstractType abstractType = node.typecheck(e);

                if (abstractType.sameType(defAbstractType)){
                    return abstractType;
                }
            }
        }

        return this.def.typecheck(e);
    }
}
