package src.astnodes.control;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypeConvertionException;
import src.exceptions.InvalidValueConvertionException;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TBool;
import src.misc.TypeFunctions;
import src.type.TVoid;
import src.type.Type;
import src.value.Value;

import java.util.List;
import java.util.Map;

public class ASTMatch extends TypeHolder implements ASTNode {

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
            throw new InvalidValueConvertionException(condValue.show());
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
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TBool();
        Type condType = this.cond.typecheck(e);

        if (!TypeFunctions.sameType(condType, targetType))
            throw new InvalidTypeConvertionException(condType.show(), targetType.show(), this.getClass().getSimpleName());

        Type defType = this.def.typecheck(e);
        for (Map.Entry<List<ASTNode>, ASTNode> entry : this.cases.entrySet()) {
            List<ASTNode> astNodeList = entry.getKey();
            ASTNode caseNode = entry.getValue();


            for (ASTNode node: astNodeList) {
                Type nodeType = node.typecheck(e);

                if (TypeFunctions.sameType(nodeType, defType)){
                    return new TVoid();
                }
            }
        }

        Type result = this.def.typecheck(e);
        this.setType(result);
        return result;
    }
}
