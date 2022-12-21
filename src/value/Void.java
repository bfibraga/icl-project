package src.value;

import src.misc.TypeFunctions;
import src.type.Type;

public class Void implements Value {
    @Override
    public String show() {
        return null;
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
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public Value toInt() {
        return new Int();
    }

    @Override
    public Value toBool() {
        return new Bool();
    }
}
