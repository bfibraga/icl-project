package src.astnodes.value.function;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.Pair;
import src.type.TClosure;
import src.misc.TypeFunctions;
import src.type.Type;
import src.value.Closure;
import src.value.Value;

import java.util.ArrayList;
import java.util.List;

public class ASTFunction implements ASTNode {

    private final List<Pair<String, Type>> params;
    private final ASTNode body;

    public ASTFunction(List<Pair<String,String>> params, ASTNode body) {
        this.params = new ArrayList<>();
        for (Pair<String,String> pair: params) {
            this.params.add(new Pair<>(pair.getKey(), TypeFunctions.getType(pair.getValue())));
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
    public Type typecheck(Environment<Type> e) {
        e = e.beginScope();
        for (Pair<String, Type> pair: this.params) {
            String paramID = pair.getKey();
            Type paramType = pair.getValue();

            e.assoc(paramID, paramType);
        }
        Type bodyType = this.body.typecheck(e);
        e = e.endScope();

        return new TClosure(params, bodyType);
    }
}
