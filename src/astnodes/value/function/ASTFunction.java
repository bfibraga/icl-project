package src.astnodes.value.function;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.Pair;
import src.type.TClosure;
import src.type.AbstractType;
import src.value.Closure;
import src.value.Value;

import java.util.ArrayList;
import java.util.List;

public class ASTFunction implements ASTNode {

    private final List<Pair<String, AbstractType>> params;
    private final ASTNode body;

    public ASTFunction(List<Pair<String,String>> params, ASTNode body) {
        this.params = new ArrayList<>();
        for (Pair<String,String> pair: params) {
            this.params.add(new Pair<>(pair.getKey(), AbstractType.getType(pair.getValue())));
        }
        this.body = body;
    }

    @Override
    public Value eval(Environment<Value> e) {
        return new Closure<>(this.params, this.body, e);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public AbstractType typecheck(Environment<AbstractType> e) {
        e = e.beginScope();
        for (Pair<String, AbstractType> pair: this.params) {
            String paramID = pair.getKey();
            AbstractType paramAbstractType = pair.getValue();

            e.assoc(paramID, paramAbstractType);
        }
        AbstractType bodyAbstractType = this.body.typecheck(e);
        e = e.endScope();

        return new TClosure(params, bodyAbstractType);
    }
}
