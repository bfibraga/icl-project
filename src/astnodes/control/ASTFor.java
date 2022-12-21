package src.astnodes.control;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidValueConvertionException;
import src.misc.Bind;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TVoid;
import src.type.Type;
import src.value.Bool;
import src.value.Cell;
import src.value.Int;
import src.value.Value;

public class ASTFor extends TypeHolder implements ASTNode {

    private final Bind<String, ASTNode> init;
    private final ASTNode limit;
    private final ASTNode body;

    public ASTFor(Bind<String, ASTNode> init, ASTNode limit, ASTNode body) {
        this.init = init;
        this.limit = limit;
        this.body = body;
    }

    @Override
    public Value eval(Environment<Value> e) {
        String initId = this.init.getId();
        ASTNode initNode = this.init.getValue();

        Value initValue = initNode.eval(e);
        if (!initValue.isNumber())
            throw new InvalidValueConvertionException(initValue.show());

        Value iterationValue = this.limit.eval(e);
        if (!iterationValue.isNumber())
            throw new InvalidValueConvertionException(iterationValue.show());

        int initInt = ((Int)initValue).getValue();
        int iterationInt = ((Int)iterationValue).getValue();

        Value result = new Bool(false);

        if (initInt > iterationInt){
            result = iterationStep(e, initId, iterationInt, initInt);
        }

        if (initInt < iterationInt){
            result = iterationStep(e, initId, initInt, iterationInt);
        }

        if (initInt == iterationInt){
            result = this.body.eval(e);
        }

        return result;
    }

    private Value iterationStep(Environment<Value> e, String id, int min, int max) {

        Value bodyValue = this.evalBody(e, id, min);

        for (int i = min + 1; i <= max; i++) {
            bodyValue = evalBody(e, id, i);
        }

        return bodyValue;
    }

    private Value evalBody(Environment<Value> e, String id, int value) {
        e = e.beginScope();

        e.assoc(id, new Int(value));
        Value bodyValue = this.body.eval(e);

        e = e.endScope();

        return bodyValue;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        this.setType(new TVoid());
        return new TVoid();
    }
}
