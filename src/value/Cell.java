package src.value;

import src.exceptions.InvalidTypeConvertion;

public class Cell implements Value{

    private Value value;

    public Cell(Value value) {
        this.value = value;
    }

    public Value get() {
        return value;
    }

    public void set(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.show();
    }

    @Override
    public String show() {
        return String.format("Cell(%s)", this.get().show());
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
        return true;
    }

    @Override
    public boolean isString() {
        return false;
    }

    @Override
    public Value toInt() {
        throw new InvalidTypeConvertion(this.getClass().getName(), Int.class.getName());
    }

    @Override
    public Value toBool() {
        throw new InvalidTypeConvertion(this.getClass().getName(), Bool.class.getName());
    }
}
