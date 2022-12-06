package src.value;

import src.exceptions.InvalidTypeConvertionException;

public class Cell implements Value{

    private Value value;

    public Cell() {
        this.value = new Bool();
    }

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
    public boolean isFunc() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if (obj instanceof Cell){
            return ((Cell)obj).get().equals(this.get());
        }

        return false;
    }

    @Override
    public Value toInt() {
        throw new InvalidTypeConvertionException(this.getClass().getName(), Int.class.getName());
    }

    @Override
    public Value toBool() {
        throw new InvalidTypeConvertionException(this.getClass().getName(), Bool.class.getName());
    }
}
