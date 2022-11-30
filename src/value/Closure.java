package src.value;

import src.astnodes.ASTNode;
import src.misc.Environment;
import src.misc.Pair;
import src.misc.TypeFunctions;
import src.type.Type;

import java.util.List;

public class Closure<T> implements Value {

    private final List<Pair<String, Type>> paramNames;
    private final ASTNode body;
    private final Environment<T> environment;

    public Closure(List<Pair<String, Type>> paramNames, ASTNode body, Environment<T> environment) {
        this.paramNames = paramNames;
        this.body = body;
        this.environment = environment;
    }

    public List<Pair<String, Type>> getParamNames() {
        return paramNames;
    }

    public ASTNode getBody() {
        return body;
    }

    public Environment<T> getEnvironment() {
        return environment;
    }

    @Override
    public String show() {
        return String.format("[%s, %s, %s]", this.paramNames, this.body.getClass().getSimpleName(), "e" + this.environment.getDepth());
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public boolean isCell() {
        return false;
    }

    @Override
    public boolean isString() {
        return false;
    }

    @Override
    public boolean isFunc() {
        return true;
    }

    @Override
    public Value toInt() {
        return null;
    }

    @Override
    public Value toBool() {
        return null;
    }

    @Override
    public String toString() {
        return this.show();
    }
}
