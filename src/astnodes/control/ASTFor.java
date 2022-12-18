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
        if (!initValue.isCell())
            throw new InvalidValueConvertionException(initValue.show());

        Value contentValue = ((Cell)initValue).get();
        if (!contentValue.isNumber())
            throw new InvalidValueConvertionException(contentValue.show());

        Value iterationValue = this.limit.eval(e);
        if (iterationValue.isCell())
            iterationValue = ((Cell)iterationValue).get();

        if (!iterationValue.isNumber())
            throw new InvalidValueConvertionException(iterationValue.show());

        e = e.beginScope();
        e.assoc(initId, initValue);

        int initInt = ((Int)contentValue).getValue();
        int iterationInt = ((Int)iterationValue).getValue();

        Value result = new Bool(false);

        if (initInt > iterationInt){
            result = iterationStep(e, iterationInt, initInt);
        }

        if (initInt < iterationInt){
            result = iterationStep(e, initInt, iterationInt);
        }

        if (initInt == iterationInt){
            result = this.body.eval(e);
        }

        e.endScope();
        return result;
    }

    private Value iterationStep(Environment<Value> e, int min, int max) {
        for (int i = min; i <= max; i++) {
            Value previousI = e.find(init.getId());
            Cell newI = ((Cell)previousI);

            newI.set(new Int(i));

            e.alter(init.getId(), newI);
            this.body.eval(e);
        }
        e = e.endScope();
        return new Bool(true);
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
