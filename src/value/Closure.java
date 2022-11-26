package src.value;

import src.astnodes.ASTNode;
import src.misc.Environment;

public class Closure implements Value {

    private final String id;
    private final ASTNode body;
    private final Environment<Value> environment;

    public Closure(String id, ASTNode body, Environment<Value> environment) {
        this.id = id;
        this.body = body;
        this.environment = environment;
    }

    public String getId() {
        return id;
    }

    public ASTNode getBody() {
        return body;
    }

    public Environment<Value> getEnvironment() {
        return environment;
    }

    @Override
    public String show() {
        return String.format("[%s, %s, %s]", this.id, this.body, this.environment);
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
    public Value toInt() {
        return null;
    }

    @Override
    public Value toBool() {
        return null;
    }
}
